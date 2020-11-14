package com.example.studentshelpapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.studentshelpapp.ui.buynsell.BuynsellViewModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Sell extends AppCompatActivity implements UploadListAdapter.OnCrossListener {
    Button mSelectButton;
    Button Submitbtn;
    EditText name;
    EditText contact;
    EditText productName;
    EditText price;
    EditText desc;
    Spinner dropdown;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabase;

    ProgressDialog mProgress;

    RecyclerView mUploadList;
    private int RESULT_LOAD_IMAGE=1;
    private List<String> fileNameList;
    private List<String > fileDoneList;
    private List<Uri> fileUriList;
    private List<String> ImageUrlList;
    private UploadListAdapter uploadListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);
        Toolbar toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("Sell");
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        dropdown = findViewById(R.id.spinner_category);
        mStorageRef= FirebaseStorage.getInstance().getReference();
        mDatabase= FirebaseDatabase.getInstance().getReference().child("Ads");

        String[] items = new String[]{"ED Kit","Vehicle","Books/Notes","Apron","Other"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);

        dropdown.setAdapter(adapter);

        Submitbtn=findViewById(R.id.submit_btn);
        mSelectButton=findViewById(R.id.btnchoose);
        mUploadList=findViewById(R.id.uploadList);
        name=findViewById(R.id.text_name);
        contact=findViewById(R.id.text_contact);
        productName=findViewById(R.id.text_product);
        price=findViewById(R.id.text_price);
        desc=findViewById(R.id.text_desc);

        fileNameList=new ArrayList<>();
        fileDoneList=new ArrayList<>();

        fileUriList=new ArrayList<>();
        ImageUrlList=new ArrayList<>();
        uploadListAdapter=new UploadListAdapter(fileNameList,fileDoneList,this);
        //Recycler View
        mUploadList.setLayoutManager(new LinearLayoutManager(this));
        mUploadList.setHasFixedSize(true);
        mUploadList.setAdapter(uploadListAdapter);

        mSelectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Picture"),RESULT_LOAD_IMAGE);
            }
        });
        Submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPosting();


            }


        });
    }

    public void startPosting() {

        String Name=name.getText().toString();
        String Contact=contact.getText().toString();
        String ProName=productName.getText().toString();
        String Price=price.getText().toString();
        Price="₹"+Price;
        String Category=dropdown.getSelectedItem().toString();
        String Description=desc.getText().toString();
        if(TextUtils.isEmpty(Description))
        {
            Description="No description";
        }
        mProgress=new ProgressDialog(this);
        final DatabaseReference neworder=mDatabase.push();
        String key=neworder.getKey();

        if(!TextUtils.isEmpty(Name)&&!TextUtils.isEmpty(Contact)&&!TextUtils.isEmpty(ProName)&&!TextUtils.isEmpty(Price)&&!TextUtils.isEmpty(Category)&&Contact.length()==10)
        {
            final int totalImages=fileUriList.size();
            mProgress.setMessage("Posting Ad...");
            mProgress.show();


            for(int i=0;i<totalImages;i++)
            {
                final StorageReference filepath=mStorageRef.child("Images").child(key+Integer.toString(i));

                final int finalI = i;

                filepath.putFile(fileUriList.get(i)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                neworder.child("Images").child(Integer.toString(finalI)).setValue(uri.toString());
                                Log.i("image",uri.toString());
                                if(finalI==totalImages-1)
                                {mProgress.dismiss();
                                    Toast.makeText(getApplicationContext(),"Ad posted successfully",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Sell.this,MainActivity.class);
                                    startActivity(intent);

                                }
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Oops, upload failed for image " + Integer.toString(finalI),Toast.LENGTH_SHORT).show();
                    }
                });
            }
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {
                for (UserInfo profile : user.getProviderData()) {
                    // UID specific to the provider
                    String uid = profile.getUid();
                    neworder.child("Id").setValue(uid);


                }
            }
            neworder.child("Name").setValue(Name);
            neworder.child("Contact").setValue(Contact);
            neworder.child("Product").setValue(ProName);
            neworder.child("Category").setValue(Category);
            neworder.child("Price").setValue(Price);
            neworder.child("Description").setValue(Description);

            if(totalImages==0)
            {
                mProgress.dismiss();
                Toast.makeText(getApplicationContext(),"Ad posted successfully",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Sell.this,MainActivity.class);
                startActivity(intent);

            }









        }
        else
        {

            if(Contact.length()!=10){
                Toast.makeText(getApplicationContext(),"Enter valid contact number",Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(getApplicationContext(),"Incomplete or incorrect information",Toast.LENGTH_LONG).show();
            }
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RESULT_LOAD_IMAGE&&resultCode==RESULT_OK)
        {
            if(data.getClipData()!=null)
            {
                int totalItemSelected=data.getClipData().getItemCount();
                for(int i = 0; i < totalItemSelected; i++)
                {
                    Uri fileUri=data.getClipData().getItemAt(i).getUri();
                    String fileName = getFileName(fileUri);
                    fileNameList.add(fileName);
                    fileUriList.add(fileUri);
                    uploadListAdapter.notifyDataSetChanged();


                }
                //Toast.makeText(Sell.this,"Selected Multiple Images",Toast.LENGTH_LONG).show();

            }
            else if(data.getData()!=null)
            {
                Uri fileUri=data.getData();
                String fileName = getFileName(fileUri);
                fileNameList.add(fileName);
                fileUriList.add(fileUri);
                uploadListAdapter.notifyDataSetChanged();
                //Toast.makeText(Sell.this,"Selected Single Image",Toast.LENGTH_LONG).show();
            }

        }
    }
    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }



    @Override
    public void onCrossClick(int position) {
       fileNameList.remove(position);
       fileUriList.remove(position);
       uploadListAdapter.notifyDataSetChanged();
    }


}

