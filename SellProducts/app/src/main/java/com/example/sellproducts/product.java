package com.example.sellproducts;

import android.net.Uri;

public class product {

    private String name;
    private int price;
    private Uri image;
    private String description;

    public product(){

    }
    public product(Uri image,String name,int price){
        this.image=image;
        this.name=name;
        this.price=price;
    }
    public product(Uri image,String name,int price,String description){
        this.image=image;
        this.name=name;
        this.price=price;
        this.description=description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Uri getImage() {
        return image;
    }

    public void setImage(Uri image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
