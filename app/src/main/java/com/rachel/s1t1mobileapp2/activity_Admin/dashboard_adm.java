package com.rachel.s1t1mobileapp2.activity_Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.rachel.s1t1mobileapp2.R;
import com.rachel.s1t1mobileapp2.activity_Dosen.kehadiran_dsn;
import com.rachel.s1t1mobileapp2.activity_Dosen.nilai_dsn;
import com.rachel.s1t1mobileapp2.activity_Dosen.profile_dsn;

public class dashboard_adm extends AppCompatActivity implements View.OnClickListener{

    CardView CVAddUser, CVAddArtikel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_adm);

        CVAddUser = (CardView) findViewById(R.id.CVadd_user);
        CVAddArtikel = (CardView) findViewById(R.id.CVadd_artikel);

        //Click Listener
        CVAddUser.setOnClickListener(this);
        CVAddArtikel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.CVadd_user:
                i = new Intent(this, adm_addUser.class);
                startActivity(i);
                break;
        }
        switch (v.getId()) {
            case R.id.CVadd_artikel:
                i = new Intent(this, adm_add_artikel.class);
                startActivity(i);
                break;
        }
    }
}