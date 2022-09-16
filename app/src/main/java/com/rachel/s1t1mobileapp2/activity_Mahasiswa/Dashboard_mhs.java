package com.rachel.s1t1mobileapp2.activity_Mahasiswa;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.rachel.s1t1mobileapp2.R;
import com.rachel.s1t1mobileapp2.activity.Login;

public class Dashboard_mhs extends AppCompatActivity implements View.OnClickListener {

    //variable declaration for input fields
    TextView user_profile_name, user_nim;
    Button logout;
    CardView profile_mahasiswa, progress_tgs, news, kehadiran, nilai, jadwal, keuangan;

    String SHARED_PREF="myfref_xml";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_mhs);

        //now, initialize variable
        user_profile_name = (TextView) findViewById(R.id.tvUser_name);
        user_nim = (TextView) findViewById(R.id.tvUser_NIM);

        profile_mahasiswa = (CardView) findViewById(R.id.CVProfile_mhs);
        progress_tgs = (CardView) findViewById(R.id.ProgressTugas);
        news = (CardView) findViewById(R.id.CVNews);
        kehadiran = (CardView) findViewById(R.id.CVKehadiran);
        nilai = (CardView) findViewById(R.id.CVNilai);
        jadwal = (CardView) findViewById(R.id.CVJadwal);
        keuangan = (CardView) findViewById(R.id.CVKeuangan);

        logout = findViewById(R.id.nav_logout);
        NavigationView navigationView = findViewById(R.id.navigationview_mhs);
        DrawerLayout drawerLayout = findViewById(R.id.DrawerLayout);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        user_profile_name.setText(sharedPreferences.getString("nama", "user is not register"));
        user_nim.setText(sharedPreferences.getString("nim", "user is not register"));

        //Click Listener
        profile_mahasiswa.setOnClickListener(this);
        progress_tgs.setOnClickListener(this);
        kehadiran.setOnClickListener(this);
        nilai.setOnClickListener(this);
        jadwal.setOnClickListener(this);
        keuangan.setOnClickListener(this);

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

    @Override
    public void onClick(View v) {
        Intent i ;
        switch (v.getId()) {
            case R.id.CVProfile_mhs :
                i = new Intent(this, profile_mhs.class);startActivity(i);
                break;

            case R.id.ProgressTugas :
                i = new Intent(this, progress_tugas.class); startActivity(i);
                break;

            case R.id.CVKehadiran :
                i = new Intent(this, kehadiran_mhs.class); startActivity(i);
                break;

            case R.id.CVNilai :
                i = new Intent(this, nilai_mhs.class); startActivity(i);
                break;

            case R.id.CVJadwal :
                i = new Intent(this, jadwal_mhs.class); startActivity(i);
                break;

            case R.id.CVKeuangan:
                i = new Intent(this, keuangan_mhs.class); startActivity(i);
                break;
        }
    }

}