package com.example.sellproducts;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class activity_image_product extends AppCompatActivity {

    ImageView im_image;
    TextView tv_name, tv_pirce;

    static final int REGUESTCODE_product=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_product);

        im_image = findViewById(R.id.image_product_iv_image);
        tv_name = findViewById(R.id.image_product_tv_name_product);
        tv_pirce = findViewById(R.id.image_product_tv_price_product);
    }
}