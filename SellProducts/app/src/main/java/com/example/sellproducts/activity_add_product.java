package com.example.sellproducts;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Picture;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class activity_add_product extends AppCompatActivity {

    ImageView iv_product,iv_arrow;
    EditText et_name,et_price,et_description;
    Button btn_save,btn_show;

    Uri imageUri;
    FirebaseAuth Auth;
    FirebaseDatabase dp;
    DatabaseReference dbRef;
    FirebaseUser admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        iv_product=findViewById(R.id.activity_add_product_iv_image_product);
        iv_arrow=findViewById(R.id.activity_add_product_iv_image_arrow);
        et_name=findViewById(R.id.activity_add_product_et_name_product);
        et_price=findViewById(R.id.activity_add_product_et_price_product);
        et_description=findViewById(R.id.activity_add_product_et_description_producta);
        btn_save=findViewById(R.id.activity_add_product_btn_save_product);
        btn_show=findViewById(R.id.activity_add_product_btn_show_product);


        String name=et_name.getText().toString();
        String price=et_price.getText().toString();
        String description=et_description.getText().toString();

        iv_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                 if(intent.resolveActivity(getPackageManager())!=null){
                     startActivityForResult(intent,1);
                 }else{
                     Toast.makeText(activity_add_product.this, "Sorry, there is no app to open the camera \n", Toast.LENGTH_LONG).show();
                 }

            }
        });
        iv_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();

            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {


                Auth=FirebaseAuth.getInstance();
                dp=FirebaseDatabase.getInstance();
                dbRef=dp.getReference("item");

                 admin=Auth.getCurrentUser();
                dbRef.child(admin.getUid()).child("name").setValue(name);
                dbRef.child(admin.getUid()).child("price").setValue(price);
                dbRef.child(admin.getUid()).child("description").setValue(description);

                    Toast.makeText(activity_add_product.this, "successfully", Toast.LENGTH_LONG).show();

            }catch (Exception e){
                    e.getMessage();
                }
            }
        });
        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getBaseContext(),Activity_products.class);
                startActivity(i);
                finish();
                
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode ==1 && resultCode ==RESULT_OK) {
            imageUri =data.getData();
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageRef = storage.getReference();
            StorageReference imagesRef = storageRef.child("image_product");
            Uri file = imageUri;
            StorageReference riversRef = storageRef.child("image_product/"+file.getLastPathSegment());
            UploadTask uploadTask = riversRef.putFile(file);

            uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }

                    // Continue with the task to get the download URL
                    return riversRef.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        Uri downloadUri = task.getResult();
                        iv_product.setImageURI(downloadUri);
                        dbRef.child(admin.getUid()).child("image").setValue(downloadUri.toString());

                    } else {
                        // Handle failures
                        // ...
                    }
                }
            });

        }

    }
}