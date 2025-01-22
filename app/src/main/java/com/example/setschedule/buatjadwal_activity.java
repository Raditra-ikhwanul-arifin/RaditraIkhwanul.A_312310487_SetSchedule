package com.example.setschedule;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;


public class buatjadwal_activity extends AppCompatActivity {

    private static final String NAMA_PREFERENCES = "MyPrefs";
    private static final String KUNCI_JUDUL_TUGAS = "User_judultugas";
    private static final String KUNCI_TANGGAL_TUGAS = "User_tanggaltugas";
    private static final String KUNCI_KETERANGAN_TUGAS = "User_keterangantugas";
    private static final String KUNCI_JAM_TUGAS = "User_jamtugas";

    ImageView imageViewCalendar;
    ImageView backButton, inputjam;
    TextView textViewDate, nilaijam;
    EditText judultugasInput1;
    EditText judultugasInput3;
    Button button2;
    private SharedPreferences sharedPreferences;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buatjadwal);

        // Inisialisasi SharedPreferences
        sharedPreferences = getSharedPreferences(NAMA_PREFERENCES, MODE_PRIVATE);

        // Inisialisasi tampilan
        inisialisasiTampilan();
        // Pengaturan listener
        aturListener();
    }

    private void inisialisasiTampilan() {
        imageViewCalendar = findViewById(R.id.imageView9);
        backButton = findViewById(R.id.buttonback1);
        judultugasInput1 = findViewById(R.id.judultugasInput1);
        textViewDate = findViewById(R.id.judultugasInput2);
        judultugasInput3 = findViewById(R.id.judultugasInput3);
        button2 = findViewById(R.id.button2);
        inputjam = findViewById(R.id.imageView15);
        nilaijam = findViewById(R.id.textView16);

    }

    private void aturListener() {
        // Listener untuk ikon kalender
        imageViewCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tampilkanPemilihTanggal();
            }
        });

        // Listener untuk tombol kembali
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kembaliKeHalamanUtama();
            }
        });

        // Listener untuk tombol simpan
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpanDataJadwal();
            }
        });

        inputjam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });
    }

    private void tampilkanPemilihTanggal() {
        final Calendar calendar = Calendar.getInstance();
        int tahun = calendar.get(Calendar.YEAR);
        int bulan = calendar.get(Calendar.MONTH);
        int hari = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int tahun, int bulan, int hariDalamBulan) {
                        String tanggalDipilih = hariDalamBulan + "/" + (bulan + 1) + "/" + tahun;
                        textViewDate.setText(tanggalDipilih);
                    }
                }, tahun, bulan, hari);
        datePickerDialog.show();
    }

    private void showTimePickerDialog() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                (view, hourOfDay, minute) -> {
                    String time = String.format("%02d:%02d", hourOfDay, minute);
                    nilaijam.setText(time);
                },
                // Set initial time (optional)
                0, 0,
                true // Set to true for 24-hour format
        );

        timePickerDialog.show();
    }

    private void simpanDataJadwal() {
        String judulTugas = judultugasInput1.getText().toString();
        String tanggalTugas = textViewDate.getText().toString();
        String jamtugas = nilaijam.getText().toString();
        String keterangan = judultugasInput3.getText().toString();

        // Validasi input
        if (validasiInput(judulTugas, tanggalTugas, keterangan)) {
            // Simpan ke SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(KUNCI_JUDUL_TUGAS, judulTugas);
            editor.putString(KUNCI_TANGGAL_TUGAS, tanggalTugas);
            editor.putString(KUNCI_JAM_TUGAS, jamtugas);
            editor.putString(KUNCI_KETERANGAN_TUGAS, keterangan);
            editor.apply();

            // Pindah ke halaman lihat jadwal
            pindahKeLihatJadwal();
        }
    }

    private boolean validasiInput(String judul, String tanggal, String keterangan) {
        if (judul.isEmpty() || tanggal.isEmpty() || keterangan.isEmpty()) {

            Toast.makeText(buatjadwal_activity.this, "Jadwal belum di isi", Toast.LENGTH_SHORT).show();
            return false;
        }
        Toast.makeText(buatjadwal_activity.this, "Jadwal Berhasil Dibuat", Toast.LENGTH_SHORT).show();
        return true;
    }

    private void kembaliKeHalamanUtama() {
        Intent intent = new Intent(this, landingpage_activity.class);
        startActivity(intent);
        finish();
    }

    private void pindahKeLihatJadwal() {
        Intent intent = new Intent(this, lihatjadwal_activity.class);
        startActivity(intent);
    }
}