package com.example.studentshelpapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Purchase extends AppCompatActivity {
    private List<String> ItemNameList;
    private List<String> ItemPriceList;
    private List<Integer> ItemImageList;
    private RecyclerView AdsView;
    private Ad_Item_Adapter ad_item_adapter;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        Toolbar toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("Shop");
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        database=FirebaseDatabase.getInstance();
        myRef=database.getReference().child("Ads");

       myRef.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               Log.i("data","data changed");
           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });
        ItemNameList=new ArrayList<>();
        ItemPriceList=new ArrayList<>();
        ItemImageList=new ArrayList<>();
        AdsView=findViewById(R.id.ads_view);
        ad_item_adapter=new Ad_Item_Adapter(ItemPriceList,ItemNameList,ItemImageList);
        //Recycler View
        AdsView.setLayoutManager(new LinearLayoutManager(this));
        AdsView.setHasFixedSize(true);
        AdsView.setAdapter(ad_item_adapter);
        ItemNameList.add("Example Item 1");
        ItemNameList.add("Example Item 2");
        ItemPriceList.add("Example price 1");
        ItemPriceList.add("Example price 2");
        ItemImageList.add(R.drawable.aagman);
        ItemImageList.add(R.drawable.technex);
        ad_item_adapter.notifyDataSetChanged();

    }
}