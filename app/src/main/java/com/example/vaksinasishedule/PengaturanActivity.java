package com.example.vaksinasishedule;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class PengaturanActivity extends AppCompatActivity {

    Button tentang_kami;
    Button ulas_kami;
    Button pertanyaan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaturan);
        getSupportActionBar().hide();

        tentang_kami = findViewById(R.id.tentang_kami_button);
        tentang_kami.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                BottomSheetDialog tentang_kami_teks = new BottomSheetDialog(PengaturanActivity.this);
                tentang_kami_teks.setContentView(R.layout.pengaturan_tentang_kami);
                tentang_kami_teks.show();
            }
        });

        ulas_kami = findViewById(R.id.ulas_kami_button);
        ulas_kami.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                BottomSheetDialog ulas_kami_teks = new BottomSheetDialog(PengaturanActivity.this);
                ulas_kami_teks.setContentView(R.layout.pengaturan_ulas_kami);
                ulas_kami_teks.show();
            }
        });

        pertanyaan = findViewById(R.id.ajukan_pertanyaan_button);
        pertanyaan.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                BottomSheetDialog pertanyaan_teks = new BottomSheetDialog(PengaturanActivity.this);
                pertanyaan_teks.setContentView(R.layout.pengaturan_ajukan_pertanyaan);
                pertanyaan_teks.show();
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navbar);

        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.akun:
                        startActivity(new Intent(getApplicationContext(), AkunActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.jadwal:
                        startActivity(new Intent(getApplicationContext(), Jadwal.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.pengaturan:
                        return true;
                }
                return false;
            }
        });
    }
}