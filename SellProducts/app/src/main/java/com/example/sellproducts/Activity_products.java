package com.example.sellproducts;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.JetPlayer;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Activity_products extends AppCompatActivity {

    ImageView iv_purchases;
    EditText et_search;
    RecyclerView rv;
    static Button btn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        iv_purchases=findViewById(R.id.activity_products_iv_purchases);
        et_search=findViewById(R.id.activity_products_et_search);
        rv=findViewById(R.id.activity_products_rv_products);
        btn_add=findViewById(R.id.activity_products_btn_add);

        if(MainActivity.flog==1){
            btn_add.setVisibility(View.VISIBLE);
        }
        MainActivity.flog=0;

        ArrayList<product> p=new ArrayList<product>();
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference("item").push();

        mDatabase.orderByKey().addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot,  String previousChildName) {
                for(DataSnapshot s: snapshot.getChildren()){
                    Log.e("product ",s.getValue()+"");
                }
                ImageAdapter a=new ImageAdapter(getBaseContext(),p);
                rv.setAdapter(a);
            }

            @Override
            public void onChildChanged( DataSnapshot snapshot, String previousChildName) {
                for(DataSnapshot s: snapshot.getChildren()){
                    Log.e("product ",s.getValue()+"");

                }


            }

            @Override
            public void onChildRemoved( DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved( DataSnapshot snapshot, String previousChildName) {

            }

            @Override
            public void onCancelled( DatabaseError error) {

            }
        });


                rv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(getBaseContext(),activity_product.class);
                        startActivity(intent);
                        finish();
                    }
                });
                iv_purchases.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getBaseContext(), activity_item_lv_buy.class);
                        startActivity(intent);
                        finish();
                    }
                });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Activity_products.this,activity_add_product.class);
                startActivity(i);
                finish();
            }
        });


    }
}