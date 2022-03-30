package com.example.sellproducts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.core.Context;

import java.util.ArrayList;

public class ImageAdapter<ArrayList> extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private Context context;
    ArrayList<product> data;

    public ImageAdapter(Context context,ArrayList<product> data){
        this.context=context;
        this.data=data;

    }

    @Override
    public ImageViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(context).inflate(R.layout.activity_image_product,parent,false);


        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder( ImageAdapter.ImageViewHolder holder, int position) {

        product p=data.get(position);
        holder.tv_name.setText(p.getName());
        holder.tv_price.setText(p.getPrice());
        holder.iv_image_product.setImageURI(p.getImage());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }



    public class ImageViewHolder extends RecyclerView.ViewHolder{

        public TextView tv_name,tv_price;
        public ImageView iv_image_product;

        public ImageViewHolder(View itemView) {
            super(itemView);


            tv_name=itemView.findViewById(R.id.image_product_tv_name_product);
            tv_price=itemView.findViewById(R.id.image_product_tv_price_product);
            iv_image_product=itemView.findViewById(R.id.image_product_iv_image);


        }
    }
}
