package com.example.myprojectandroid;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menuHomeMyProfile) {
            Intent i = new Intent(HomeActivity.this, ProfileActivity.class);
            startActivity(i);
        } else if (id == R.id.menuHomeSettings) {
            Toast.makeText(this, "Settings click", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.menuHomeContactUs) {
            Toast.makeText(this, "Contact Us click", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.menuHomeAboutUs) {
            Toast.makeText(this, "About Us click", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.menuHomeLogout) {
            logout();
        }

        return true;
    }

    private void logout() {
        AlertDialog.Builder ad = new AlertDialog.Builder(HomeActivity.this);
        ad.setTitle("Logout");
        ad.setMessage("Are you sure you want to logout?");

        ad.setPositiveButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        ad.setNegativeButton("logout", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(i);
                finishAffinity();
            }
        });

        ad.show().create();
    }
}