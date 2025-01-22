package com.example.setschedule;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class landingpage_activity extends AppCompatActivity {

    TextView textViewResult;
    ImageView buatjadwal, lihatjadwal, pengaturan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landingpage_activity);

        // Inisialisasi
        buatjadwal = findViewById(R.id.imageBuatJadwal);
        lihatjadwal = findViewById(R.id.imageLihatJadwal);
        textViewResult = findViewById(R.id.appTitle1);
        pengaturan = findViewById(R.id.buttonsetting1);

        // Ambil data dari SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String savedText = sharedPreferences.getString("SAVED_username", "Tidak Ada Data Tersimpan");
        String savedValue = sharedPreferences.getString("User_tanggaltugas", null); // Ganti "EVENT_DATE" dengan key yang Anda gunakan

        // Tampilkan di TextView
        textViewResult.setText(savedText);

        // Tambahkan klik listener untuk "Buat Jadwal"
        buatjadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (savedValue != null && !savedValue.isEmpty()) {
                    Toast.makeText(landingpage_activity.this, "selesaikan jadwal sebelumnya, sebelum buat jadwal baru", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(landingpage_activity.this, buatjadwal_activity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        // Tambahkan klik listener untuk "lihat jadwal"
        lihatjadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (savedValue != null && !savedValue.isEmpty()) {
                    Intent intent = new Intent(landingpage_activity.this, lihatjadwal_activity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(landingpage_activity.this, "belum ada jadwal dengan nama akunmu", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Tambahkan klik listener untuk "lihat jadwal"
        pengaturan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (savedText != null && !savedText.isEmpty()) {
                    Intent intent = new Intent(landingpage_activity.this, rename_activity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(landingpage_activity.this, "belum ada jadwal dengan nama akunmu", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}