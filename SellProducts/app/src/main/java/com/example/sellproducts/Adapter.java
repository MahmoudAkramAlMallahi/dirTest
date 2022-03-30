package com.example.sellproducts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {
    Context context;
    int resource;
    ArrayList<product> data;


    public Adapter(Context c,int resource,ArrayList<product> data){
        this.context=c;
        this.resource=resource;
        this.data=data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v=convertView;

        if(v ==null){
            v= LayoutInflater.from(context).inflate(resource,null,false);
        }

        ImageView image_item =v.findViewById(R.id.item_image);
        TextView tv_name_item=v.findViewById(R.id.item_tv_name_product);
        TextView tv_price_item=v.findViewById(R.id.item_tv_price_product);


        image_item.setImageURI(data.get(position).getImage());
        tv_name_item.setText(data.get(position).getName());
        tv_price_item.setText(data.get(position).getPrice()+"");

        return v;
    }
}
