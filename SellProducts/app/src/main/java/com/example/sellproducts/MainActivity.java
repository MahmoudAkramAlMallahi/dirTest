package com.example.sellproducts;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    EditText et_email,et_password;
    TextView tv_signUp;
    ImageView iv_facebook ,iv_whatsapp;
    Button btn_signin;

    private FirebaseAuth mAuth;

    static final int REQUEST_CODE_SIGNUP =1;
    static  int flog=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_email=findViewById(R.id.main_et_email);
        et_password=findViewById(R.id.main_et_password);
        tv_signUp=findViewById(R.id.main_tv_signUp);
        iv_facebook=findViewById(R.id.main_iv_facebook);
        iv_whatsapp=findViewById(R.id.main_iv_whatsapp);
        btn_signin=findViewById(R.id.main_btn_signin);

        mAuth=FirebaseAuth.getInstance();



        tv_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity.this,Activity_data_entry.class);
                startActivityForResult(intent,REQUEST_CODE_SIGNUP);
                finish();

            }
        });
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email=et_email.getText().toString();
                String password=et_password.getText().toString();

                if (email.equals("admin@hotmail.com")){
                    mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                FirebaseUser user=mAuth.getCurrentUser();
                                Intent intent=new Intent(MainActivity.this,Activity_products.class);
                                startActivity(intent);
                                finish();
                                flog=1;

                            }
                        }
                    });
                }
                else {
                    mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                FirebaseUser user=mAuth.getCurrentUser();
                                Intent intent=new Intent(MainActivity.this,Activity_products.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
                }


            }
        });

        iv_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                Uri uri=Uri.parse("fb://page/<Facebook"+"");
                intent.setData(uri);
                startActivity(intent);
                finish();

            }
        });
        iv_whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                Uri uri=Uri.parse("https://api.whatsapp.com/send?phone="+"+970597513152");
                intent.setData(uri);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==REQUEST_CODE_SIGNUP &&resultCode == Activity_data_entry.RESULTCODE_Ok){
            String email=data.getStringExtra(Activity_data_entry.KEY_NAME_EMAIL);
            String password=data.getStringExtra(Activity_data_entry.KEY_NAME_PASSWORD);

            et_email.setText(email+"");
            et_password.setText(password+"");

        }
    }
}