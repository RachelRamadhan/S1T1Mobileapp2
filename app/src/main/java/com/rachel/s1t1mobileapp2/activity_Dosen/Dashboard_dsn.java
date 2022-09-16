package com.rachel.s1t1mobileapp2.activity_Dosen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.rachel.s1t1mobileapp2.R;

public class Dashboard_dsn extends AppCompatActivity implements View.OnClickListener {

    TextView dsn_profile_name, dsn_nim;
    CardView cvTimetable1, cvTimetable2, cvTimetable3, profile_dosen, nilai_mhs, kehadiran_mhs, jadwal_dsn, news;

    String SHARED_PREF="myfref_xml";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_dsn);

        dsn_profile_name = (TextView) findViewById(R.id.TV_show_full_name_dosen);
        dsn_nim = (TextView) findViewById(R.id.TV_show_nidn_Dosen);

        profile_dosen = (CardView) findViewById(R.id.CVProfile_dosen);
        nilai_mhs = (CardView) findViewById(R.id.CVNilai_mhs);
        kehadiran_mhs = (CardView) findViewById(R.id.CVKehadiran_mhs);
        jadwal_dsn = (CardView) findViewById(R.id.CVJadwal_dsn);
        news = (CardView) findViewById(R.id.CVNews);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        dsn_profile_name.setText(sharedPreferences.getString("nama", "user is not register"));
        dsn_nim.setText(sharedPreferences.getString("nim", "user is not register"));

        //Click Listener
        profile_dosen.setOnClickListener(this);
        nilai_mhs.setOnClickListener(this);
        kehadiran_mhs.setOnClickListener(this);
        jadwal_dsn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.CVProfile_dosen:
                i = new Intent(this, profile_dsn.class);
                startActivity(i);
                break;

            case R.id.CVNilai_mhs:
                i = new Intent(this, nilai_dsn.class);
                startActivity(i);
                break;

            case R.id.CVKehadiran_mhs:
                i = new Intent(this, kehadiran_dsn.class);
                startActivity(i);
                break;
        }
    }
}