package com.example.myprojectandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    EditText etMobileNo;
    Button btnOTP;
    TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etMobileNo=findViewById(R.id.etLoginMobileNo);
        btnOTP=findViewById(R.id.btnLoginOTP);
        tvRegister=findViewById(R.id.tvRegister);

        Intent i=new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(i);
        finishAffinity();

    }
}