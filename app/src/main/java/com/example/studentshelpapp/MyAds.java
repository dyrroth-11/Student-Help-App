package com.example.studentshelpapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MyAds extends AppCompatActivity implements  Ad_Item_Adapter.OnAdListener{
    static public List<String> ItemNameList;
    static public List<String> ItemPriceList;
    static public ArrayList<String> UserNameList;
    static public ArrayList<String> Category;
    static public ArrayList<String> Contact;
    static public ArrayList<String> Description;
    static public ArrayList<List<String>> itemurl;
    static public ArrayList<String> Keylist;
    private RecyclerView AdsView;
    private Ad_Item_Adapter ad_item_adapter;
    ProgressDialog mprogress;

    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ads);
        Toolbar toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle(" Your ads");
        setSupportActionBar(toolbar);
        AdsView=findViewById(R.id.MyAdsView);
        database=FirebaseDatabase.getInstance();
        myRef=database.getReference().child("Ads");
        ItemNameList=new ArrayList<>();
        ItemPriceList=new ArrayList<>();
        UserNameList=new ArrayList<>();
        Category=new ArrayList<>();
        Contact=new ArrayList<>();
        Description=new ArrayList<>();
        itemurl=new ArrayList<>();
        Keylist=new ArrayList<>();
        mprogress=new ProgressDialog(this);
        ad_item_adapter=new Ad_Item_Adapter(ItemPriceList,ItemNameList,itemurl,this);
        //Recycler View
        AdsView.setLayoutManager(new LinearLayoutManager(this));
        AdsView.setHasFixedSize(true);
        AdsView.setAdapter(ad_item_adapter);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            for (UserInfo profile : user.getProviderData()) {
                // UID specific to the provider
                mprogress.setMessage("Loading..");
                mprogress.show();
                String uid = profile.getUid();
                Query q=myRef.orderByChild("Id").equalTo(uid);
                q.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        ItemNameList.clear();
                        ItemPriceList.clear();
                        UserNameList.clear();
                        Category.clear();
                        Contact.clear();
                        Description.clear();
                        itemurl.clear();
                        Keylist.clear();
                        ad_item_adapter.notifyDataSetChanged();
                        long counter=snapshot.getChildrenCount();
                        if(counter==0)mprogress.dismiss();
                        //Log.i("counter",Long.toString(counter));
                        long i=1;
                        for(DataSnapshot data:snapshot.getChildren())
                        {
                            Map<String, Object> values = (HashMap<String, Object>) data.getValue();
                            Keylist.add(data.getKey());
                            UserNameList.add(values.get("Name").toString());

                            Contact.add(values.get("Contact").toString());
                            ItemNameList.add(values.get("Product").toString());
                            ItemPriceList.add(values.get("Price").toString());

                            if(values.containsKey("Description"))
                            {
                                Description.add(values.get("Description").toString());
                            }
                            else
                            {
                                Description.add("No description");
                            }
                            ArrayList<String> temp = new ArrayList<>();
                            if(values.containsKey("Images")) {


                                for (DataSnapshot image : data.child("Images").getChildren()) {
                                    temp.add(image.getValue().toString());


                                }
                                //Log.i("images",temp.get(0));

                            }
                            else
                            {temp.add("No image");

                            }
                            itemurl.add(temp);
                            i++;
                            //Log.i("i",Long.toString(i));
                            if(i>counter)
                            {//Log.i("nmaes",ItemNameList.toString());
                                mprogress.dismiss();

                                ad_item_adapter.notifyDataSetChanged();

                            }

                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        }



    }

    @Override
    public void onAdClick(int position) {
        Intent intent=new Intent(this,MyDetails.class);
        intent.putExtra("position",position);
        startActivity(intent);
    }
}