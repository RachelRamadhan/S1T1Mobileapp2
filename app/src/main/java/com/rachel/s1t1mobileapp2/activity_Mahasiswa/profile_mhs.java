package com.rachel.s1t1mobileapp2.activity_Mahasiswa;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.rachel.s1t1mobileapp2.R;
import com.rachel.s1t1mobileapp2.activity.Login;

public class profile_mhs extends AppCompatActivity {

    TextView TV_NIM, TV_Kelas, TV_KelasPeminatan, TV_DosenWali, TV_ProgramStudi, TV_TahunAngkatan,
            TV_NamaLengkap, TV_TempatTglLahir, TV_Alamat, TV_KodePos, TV_JenisKelamin, TV_Agama, TV_Noktp;
    String NIM, Kelas, kelaspeminatan, dosenwali, programstudi, tahunangkatan, namalengkap,
            tempattgllahir, alamat, kodepos, jeniskelamin, agama, noktp;
    ProgressBar progressBar;
    ImageView imageView;
    Button logout, close;

    String SHARED_PREF="myfref_xml";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_mhs);

        TV_NIM = findViewById(R.id.TV_show_NIM);
        TV_Kelas = findViewById(R.id.TV_show_kelas);
        TV_KelasPeminatan = findViewById(R.id.TV_show_kelas_peminatan);
        TV_DosenWali = findViewById(R.id.TV_show_Dosen_Wali);
        TV_ProgramStudi = findViewById(R.id.TV_show_ProgramStudi);
        TV_TahunAngkatan = findViewById(R.id.TV_show_Tahun_Angkatan);

        TV_NamaLengkap = findViewById(R.id.TV_show_full_name);
        TV_TempatTglLahir = findViewById(R.id.TV_show_full_name);
        TV_Alamat = findViewById(R.id.TV_show_alamat);
        TV_KodePos = findViewById(R.id.TV_show_kode_pos);
        TV_JenisKelamin = findViewById(R.id.TV_show_jnsKelamin);
        TV_Agama = findViewById(R.id.TV_show_agama);
        TV_Noktp = findViewById(R.id.TV_show_noKTP);

        close = findViewById(R.id.btn_close);
        logout = findViewById(R.id.btn_Logout);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.clear();
                editor.commit();

                Intent intent=new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });
    }
}