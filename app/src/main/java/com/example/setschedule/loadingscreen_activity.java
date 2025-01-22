package com.example.setschedule;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;


public class loadingscreen_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loadingscreen_activity);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String nama = sharedPreferences.getString("SAVED_username", "");

        // Referensi ke ImageView
        ImageView splashGif = findViewById(R.id.logoImageView);


        if (!nama.isEmpty()) {

            // Load GIF menggunakan Glide
            Glide.with(this)
                    .asGif()
                    .load(R.drawable.ssgif_logo)
                    .into(splashGif);

            // Simulasi proses loading (ganti dengan tugas sebenarnya)
            new Handler().postDelayed(() -> {
                Intent intent = new Intent(loadingscreen_activity.this, landingpage_activity.class);
                startActivity(intent);
                finish();
            }, 3000); // Tunggu selama 3 detik
        }else{

            // Load GIF menggunakan Glide
            Glide.with(this)
                    .asGif()
                    .load(R.drawable.ssgif_logo)
                    .into(splashGif);

            new Handler().postDelayed(() -> {
                Intent intent = new Intent(loadingscreen_activity.this, sign_in_activity.class);
                startActivity(intent);
                finish();
            }, 3000); // Tunggu selama 3 detik
        }
    }
}


