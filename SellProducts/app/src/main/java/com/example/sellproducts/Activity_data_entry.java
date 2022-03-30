package com.example.sellproducts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Activity_data_entry extends AppCompatActivity {

    EditText et_name ,et_age,et_address,et_email,et_password,et_phone;
    Button btn_signup;

    private FirebaseAuth mAuth;

    final static int RESULTCODE_Ok=1;
    final static String KEY_NAME_EMAIL="email";
    final static String KEY_NAME_PASSWORD="password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);

        et_name=findViewById(R.id.data_entry_et_user_name);
        et_age=findViewById(R.id.data_entry_et_age);
        et_address=findViewById(R.id.data_entry_et_address);
        et_email=findViewById(R.id.data_entry_et_email);
        et_password=findViewById(R.id.data_entry_et_password);
        et_phone=findViewById(R.id.data_entry_et_phone);
        btn_signup=findViewById(R.id.data_entry_btn_signUp);

        String name=et_name.getText().toString();
        String age=et_age.getText().toString();
        String address=et_address.getText().toString();
        String email=et_email.getText().toString();
        String password=et_password.getText().toString();
        String phone=et_phone.getText().toString();

        mAuth= FirebaseAuth.getInstance();

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email=et_email.getText().toString();
                String password=et_password.getText().toString();

                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            FirebaseUser user=mAuth.getCurrentUser();
                            FirebaseDatabase db=FirebaseDatabase.getInstance();
                            DatabaseReference dbref=db.getReference("users");
                            dbref.child(user.getUid()).child("name").setValue(name);
                            dbref.child(user.getUid()).child("age").setValue(age);
                            dbref.child(user.getUid()).child("address").setValue(address);
                            dbref.child(user.getUid()).child("email").setValue(email);
                            dbref.child(user.getUid()).child("password").setValue(password);
                            dbref.child(user.getUid()).child("phone").setValue(phone);

                            Intent intent = getIntent();
                            intent.putExtra(KEY_NAME_EMAIL,email);
                            intent.putExtra(KEY_NAME_PASSWORD,password);
                            setResult(RESULTCODE_Ok,intent);
                            finish();

                        }else {
                            Toast.makeText(getBaseContext(),"Error, Verify your email and password",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}