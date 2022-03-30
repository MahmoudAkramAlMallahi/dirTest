package com.example.sellproducts;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class activity_product extends AppCompatActivity {
    ImageView iv_imag,iv_Return;
    TextView tv_name,tv_price,tv_description,tv_quantity;
    Button btn_sum,btn_subtract,btn_add_to_cart,btn_favorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        iv_imag=findViewById(R.id.activity_product_iv_image);
        iv_Return=findViewById(R.id.activity_product_iv_image_arrow);
        tv_name=findViewById(R.id.activity_product_tv_name_product);
        tv_price=findViewById(R.id.activity_product_tv_price_product);
        tv_description=findViewById(R.id.activity_product_tv_description_product);
        tv_quantity=findViewById(R.id.activity_product_tv_quantity);
        btn_sum=findViewById(R.id.activity_product_btn_sum);
        btn_subtract=findViewById(R.id.activity_product_btn_subtract);
        btn_add_to_cart=findViewById(R.id.activity_product_btn_add_to_cart);

        btn_sum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity_old=Integer.parseInt(tv_quantity.getText().toString());
                int sum=quantity_old + 1;
                tv_quantity.setText(sum+"");
            }
        });
        btn_subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity_old=Integer.parseInt(tv_quantity.getText().toString());
                int subtract=quantity_old - 1;
                tv_quantity.setText(subtract+"");
            }
        });
        iv_Return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();

            }
        });
        btn_add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int image=iv_imag.getImageAlpha();
                String name=tv_name.getText().toString();
                int price=Integer.parseInt(tv_price.getText().toString());

            }
        });
    }
}