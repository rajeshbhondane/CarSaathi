package com.example.myprojectandroid;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {


    private EditText etUsername, etEmailID, etPhoneNo, etPassword;


    private Button btnSubmit, btnEditProfile, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile);


        etUsername = findViewById(R.id.etProfileUsername);
        etEmailID = findViewById(R.id.etProfileEmailID);
        etPhoneNo = findViewById(R.id.etProfilePhoneNo);
        etPassword = findViewById(R.id.etProfilePassword);


        btnSubmit = findViewById(R.id.btnSubmit);
        btnEditProfile = findViewById(R.id.btnEditProfile);
        btnLogout = findViewById(R.id.btnLogout);


        setFieldsEnabled(false);


        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProfileActivity.this, "You can now edit your profile", Toast.LENGTH_SHORT).show();
                setFieldsEnabled(true);
            }
        });


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString().trim();
                String email = etEmailID.getText().toString().trim();
                String phone = etPhoneNo.getText().toString().trim();
                String password = etPassword.getText().toString().trim();


                if (username.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
                    Toast.makeText(ProfileActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ProfileActivity.this, "Profile Saved Successfully!", Toast.LENGTH_SHORT).show();
                    setFieldsEnabled(false);
                }
            }
        });


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogoutDialog();
            }
        });
    }


    private void setFieldsEnabled(boolean enabled) {
        etUsername.setEnabled(enabled);
        etEmailID.setEnabled(enabled);
        etPhoneNo.setEnabled(enabled);
        etPassword.setEnabled(enabled);
    }


    private void showLogoutDialog() {
        AlertDialog.Builder ad = new AlertDialog.Builder(ProfileActivity.this);
        ad.setTitle("Logout");
        ad.setMessage("Are you sure you want to logout?");

        ad.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        ad.setNegativeButton("Logout", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent i = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(i);
                finishAffinity();
            }
        });

        ad.show();
    }
}