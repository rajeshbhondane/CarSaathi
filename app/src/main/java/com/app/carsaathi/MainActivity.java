package com.app.carsaathi;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    boolean doubleTab = false;

    FrameLayout mainFrameLayout;
    BottomNavigationView mainBottomNavView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mainFrameLayout = findViewById(R.id.main_frameLayout);
        mainBottomNavView = findViewById(R.id.main_bottomNavigationMenu);

        mainBottomNavView.setOnNavigationItemSelectedListener(this);
        mainBottomNavView.setSelectedItemId(R.id.home);

    }


    @SuppressLint("GestureBackNavigation")
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (doubleTab) {
            finishAffinity();
        } else {
            Toast.makeText(this, "Press Again to Exit", Toast.LENGTH_SHORT).show();
            doubleTab = true;
            Handler h = new Handler();
            h.postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleTab = false;
                }
            }, 2000);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.home) {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frameLayout, new HomeFragment()).commit();
        } else if (menuItem.getItemId() == R.id.shop) {
            //getSupportFragmentManager().beginTransaction().replace(R.id.main_frameLayout, new EntertainmentFragment()).commit();
        }else {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frameLayout, new ProfileFragment()).commit();
        }
        return true;
    }

}