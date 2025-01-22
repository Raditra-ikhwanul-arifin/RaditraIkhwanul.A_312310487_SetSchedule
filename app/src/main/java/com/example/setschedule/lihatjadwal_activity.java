package com.example.setschedule;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class lihatjadwal_activity extends AppCompatActivity {

    private static final String NAMA_PREFERENCES = "MyPrefs";
    private TextView username, tanggal_activity, keterangan_activity, txtDeskripsi, jam;
    private CalendarView calenderView;
    private CheckBox checkboxClearData;
    private Button btnKembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lihatjadwal);

        // Inisialisasi tampilan
        inisialisasiTampilan();

        // Tampilkan data
        tampilkanDataJadwal();

        // Setup listeners
        setupListeners();
    }

    private void inisialisasiTampilan() {
        // Inisialisasi TextView yang ada
        username = findViewById(R.id.username1);
        tanggal_activity = findViewById(R.id.textView4);
        txtDeskripsi = findViewById(R.id.textView5);
        keterangan_activity = findViewById(R.id.textView1);
        calenderView = findViewById(R.id.calendarView1);
        jam = findViewById(R.id.textView6);
        checkboxClearData = findViewById(R.id.checkBoxOption1);
        btnKembali = findViewById(R.id.button1);
    }

    private void tampilkanDataJadwal() {
        // Ambil data dari SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences(NAMA_PREFERENCES, MODE_PRIVATE);
        String name = sharedPreferences.getString("SAVED_username", "Tidak Ada Nama");
        String jadwaltugas = sharedPreferences.getString("User_judultugas", "Tidak Ada Jadwal");
        String tanggal = sharedPreferences.getString("User_tanggaltugas", "Tidak Ada Tanggal");
        String jamtugas= sharedPreferences.getString("User_jamtugas", "Tidak Ada Jam");
        String keterangan = sharedPreferences.getString("User_keterangantugas", "");

        // Tampilkan data di TextView
        username.setText(name);
        txtDeskripsi.setText(jadwaltugas);
        tanggal_activity.setText(tanggal);
        jam.setText("(" + jamtugas + ")");
        keterangan_activity.setText(keterangan);

        // Atur tanggal di CalendarView
        aturTanggalCalendar(tanggal);
    }

    private void aturTanggalCalendar(String tanggal) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            if (!tanggal.isEmpty()) {
                Date date = dateFormat.parse(tanggal);
                if (date != null) {
                    calenderView.setDate(date.getTime());
                }
            } else {
                tanggal_activity.setText("Tanggal tidak tersedia");
            }
        } catch (ParseException e) {
            e.printStackTrace();
            tanggal_activity.setText("Format tanggal salah");
        }
    }

    private void setupListeners() {
        // Listener untuk checkbox
        checkboxClearData.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    hapusDataDanKembaliKeSignIn();
                }
            }
        });

        // Listener untuk tombol Kembali
        btnKembali.setOnClickListener(v -> kembaliKeLandingPage());
    }

    private void hapusDataDanKembaliKeSignIn() {
        SharedPreferences sharedPreferences = getSharedPreferences(NAMA_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("User_judultugas");
        editor.remove("User_tanggaltugas");
        editor.remove("User_jamtugas");
        editor.remove("User_keterangantugas");
        editor.apply();

        Toast.makeText(this, "Jadwal dihapus, silakan atur ulang!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, landingpage_activity.class);
        startActivity(intent);
        finish();
    }

    private void kembaliKeLandingPage() {
        Intent intent = new Intent(this, landingpage_activity.class);
        startActivity(intent);
        finish();
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        kembaliKeLandingPage();
    }
}