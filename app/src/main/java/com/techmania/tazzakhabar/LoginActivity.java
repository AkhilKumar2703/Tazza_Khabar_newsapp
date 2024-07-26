package com.techmania.tazzakhabar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText username , useremail, userpassword;
    AppCompatButton loginbtn,skipbtn;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        useremail = findViewById(R.id.edtemail);
        username = findViewById(R.id.edtname);
        userpassword = findViewById(R.id.edtpassword);
        loginbtn = findViewById(R.id.btnlogin);
        skipbtn = findViewById(R.id.skipbtn);

        Intent istart = new Intent(LoginActivity.this, MainActivity.class);

        skipbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(istart);
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!username.getText().toString().equals("") && !useremail.getText().toString().equals("")&& !userpassword.getText().toString().equals("")){
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity(istart);

                }else{
                    Toast.makeText(LoginActivity.this, "Enter Valid Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}