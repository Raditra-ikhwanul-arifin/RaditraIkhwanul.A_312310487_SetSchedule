package com.example.setschedule;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;


public class rename_activity extends AppCompatActivity {

    private static final String NAMA_PREFERENCES = "MyPrefs";
    TextView Name;
    Button button1, button2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rename_activity);

        // Inisialisasi
        Name = findViewById(R.id.textView6);
        button1 = findViewById(R.id.btnRubah);
        button2 = findViewById(R.id.buttonkembali);

        // Ambil data dari SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String savedText = sharedPreferences.getString("SAVED_username", "");


        // Tampilkan di TextView
        Name.setText(savedText);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("SAVED_username");
                editor.apply();

                Intent intent = new Intent(rename_activity.this, sign_in_activity.class);
                startActivity(intent);
                finish();

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(rename_activity.this, landingpage_activity.class);
                startActivity(intent);
                finish();

            }
        });

    }
}
