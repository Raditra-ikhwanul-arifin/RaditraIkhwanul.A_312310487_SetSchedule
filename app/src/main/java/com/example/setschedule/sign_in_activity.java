package com.example.setschedule;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class sign_in_activity extends AppCompatActivity {

    EditText textViewData;
    Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        // Inisialisasi komponen
        textViewData = findViewById(R.id.nameInput1);
        buttonSave = findViewById(R.id.button1);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Ambil teks dari TextView
                String value = textViewData.getText().toString();

                if (!value.isEmpty()) {

                    // Simpan ke SharedPreferences
                    SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("SAVED_username", value);
                    editor.apply(); // Simpan data

                    Intent intent = new Intent(sign_in_activity.this, landingpage_activity.class);
                    startActivity(intent);
                    finish();

                }else{
                    Toast.makeText(sign_in_activity.this, "Nama Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
