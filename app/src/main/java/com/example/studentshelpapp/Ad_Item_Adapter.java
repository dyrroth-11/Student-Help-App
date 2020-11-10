package com.example.studentshelpapp;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

class Ad_Item_Adapter extends RecyclerView.Adapter<Ad_Item_Adapter.ViewHolder> {
    public List<String> ItemNameList;
    public List<String> ItemPriceList;
   public List<List<String>> ItemUrlList;



    public Ad_Item_Adapter(List<String> ItemPriceList, List<String>ItemNameList, List<List<String>> itemurl)
    {
        this.ItemNameList=ItemNameList;
        this.ItemPriceList=ItemPriceList;
        this.ItemUrlList=itemurl;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_ad_item,parent,false);

        return new Ad_Item_Adapter.ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
          String itemName=ItemNameList.get(position);
          String itemPrice=ItemPriceList.get(position);
          List<String> urls=ItemUrlList.get(position);
          String AdImage=urls.get(0);

          if(AdImage!="No image")
          {Picasso.get().load(AdImage).into(holder.ItemImage);}
          else
          {
              holder.ItemImage.setImageResource(R.drawable.noimage);

          }
          holder.ItemPrice.setText(itemPrice);
          holder.ItemName.setText(itemName);
    }

    @Override
    public int getItemCount() {
        return ItemNameList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView ItemImage;
        public TextView ItemName;
        public TextView ItemPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ItemImage=itemView.findViewById(R.id.ItemImage);
            ItemName=itemView.findViewById(R.id.ItemName);
            ItemPrice=itemView.findViewById(R.id.ItemPrice);



        }
    }

}
