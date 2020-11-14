package com.example.studentshelpapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MyDetails extends AppCompatActivity {

    Intent intent;
    TextView Name;
    TextView Contact;
    TextView Product;
    TextView Price;
    TextView Description;
    Button remove;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_details);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        intent=getIntent();
        Name=findViewById(R.id.text_name);
        Contact=findViewById(R.id.text_contact);
        Product=findViewById(R.id.text_product);
        Price=findViewById(R.id.text_price);
        remove=findViewById(R.id.btnremove);
        Description=findViewById(R.id.text_desc);

        int position=intent.getIntExtra("position",0);
        final String key= MyAds.Keylist.get(position);
        ImageSlider imageSlider=findViewById(R.id.slider);
        List<SlideModel> slideModels=new ArrayList<>();
        for(int i=0;i<MyAds.itemurl.get(position).size();i++)
        {
            slideModels.add(new SlideModel(MyAds.itemurl.get(position).get(i), ScaleTypes.FIT));
        }
        imageSlider.setImageList(slideModels);
        Name.setText(MyAds.UserNameList.get(position));
        Contact.setText(MyAds.Contact.get(position));
        Product.setText(MyAds.ItemNameList.get(position));
        Price.setText(MyAds.ItemPriceList.get(position));
        Description.setText(MyAds.Description.get(position));
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MyDetails.this).
                        setIcon(android.R.drawable.ic_delete)
                        .setTitle("Are you sure you want to delete this ad?")
                        .setMessage("Deleted ads cannot be recovered")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                DatabaseReference mdatabase= FirebaseDatabase.getInstance().getReference().child("Ads");

                                mdatabase.child(key).removeValue();
                                Toast.makeText(MyDetails.this,"Post removed successfully",Toast.LENGTH_SHORT).show();


                            }
                        }).setNegativeButton("No",null)
                        .show();
            }
        });

    }
}