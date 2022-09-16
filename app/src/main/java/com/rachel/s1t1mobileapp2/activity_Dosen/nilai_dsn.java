package com.rachel.s1t1mobileapp2.activity_Dosen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.rachel.s1t1mobileapp2.Database.DbContract;
import com.rachel.s1t1mobileapp2.Database.InterfaceMethods;
import com.rachel.s1t1mobileapp2.Database.ResponseFormServer;
import com.rachel.s1t1mobileapp2.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class nilai_dsn extends AppCompatActivity {

    EditText nim,algoritmaPemograman, sistemDigital, bengkelPemograman, kalkulus, aljabarLinear,
            matematikaDiskrit, pengantarTeknologi, literasiTIK, konsepTeknologi, bahasaIndonesia, analisisdanPerancangan;
    Spinner semester;
    Button submit_adduser,close;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nilai_dsn);

        nim = findViewById(R.id.ET_input_nim);
        algoritmaPemograman = findViewById(R.id.ET_input_algoritmaPemograman);
        sistemDigital = findViewById(R.id.ET_input_sistemDigital);
        bengkelPemograman = findViewById(R.id.ET_input_bengkelPemograman);
        kalkulus = findViewById(R.id.ET_input_kalkulus);
        aljabarLinear = findViewById(R.id.ET_input_aljabarLinear);
        matematikaDiskrit = findViewById(R.id.ET_input_matematikaDiskrit);
        pengantarTeknologi = findViewById(R.id.ET_input_pengantarTeknologiInformasi);
        literasiTIK = findViewById(R.id.ET_input_literasiTIK);
        konsepTeknologi = findViewById(R.id.ET_input_konsepTeknologi);
        bahasaIndonesia = findViewById(R.id.ET_input_bahasaIndonesia);
        analisisdanPerancangan = findViewById(R.id.ET_input_analisisdanPerancanganSistem);

        semester = findViewById(R.id.spn_pilih_semester);

        progressDialog = new ProgressDialog(this);

        submit_adduser = findViewById(R.id.btn_addUser);
        close = findViewById(R.id.btn_close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        submit_adduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nim.getText().toString().equals("")){
                    nim.setText("required");
                }
                else {
                    progressDialog.setTitle("please wait...");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                    addResult();
                }
            }
        });
    }

    void addResult(){
        String nim2 = nim.getText().toString();
        String algoritmaPemograman2 = algoritmaPemograman.getText().toString();
        String sistemDigital2 = sistemDigital.getText().toString();
        String bengkelPemograman2 = bengkelPemograman.getText().toString();
        String kalkulus2 = kalkulus.getText().toString();
        String aljabarLinear2 = aljabarLinear.getText().toString();
        String matematikaDiskrit2 = matematikaDiskrit.getText().toString();
        String pengantarTeknologi2 = pengantarTeknologi.getText().toString();
        String literasiTIK2 = literasiTIK.getText().toString();
        String konsepTeknologi2 = konsepTeknologi.getText().toString();
        String bahasaIndonesia2 = bahasaIndonesia.getText().toString();
        String analisadanPerancangan2 = analisisdanPerancangan.getText().toString();
        String semester2 = semester.getSelectedItem().toString();

        Call<ResponseFormServer>call = DbContract.getclient().create(InterfaceMethods.class).addResults(nim2,algoritmaPemograman2,sistemDigital2,bengkelPemograman2,kalkulus2,aljabarLinear2,matematikaDiskrit2,pengantarTeknologi2,literasiTIK2,konsepTeknologi2,bahasaIndonesia2,analisadanPerancangan2,semester2);
        call.enqueue(new Callback<ResponseFormServer>() {
            @Override
            public void onResponse(Call<ResponseFormServer> call, Response<ResponseFormServer> response) {
                if (response.code()==200){
                    if(response.body().getStatus().equals("ok")){
                        if(response.body().getResultCode()==1){
                            Toast.makeText(getApplicationContext(), "you have successfull add results", Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                            submit_adduser.setText("successfull add record");
                        }
                        else if(response.body().getResultCode()==0){
                            Toast.makeText(getApplicationContext(), "the student does not exist", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            submit_adduser.setText("retry...");
                        }
                        else if(response.body().getResultCode()==0){
                            Toast.makeText(getApplicationContext(), "the semester you have tried to upload has already uploaded", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            submit_adduser.setText("retry...");
                        }
                    }
                }
                else{
                    //not 200
                }
            }

            @Override
            public void onFailure(Call<ResponseFormServer> call, Throwable t) {

            }
        });
    }
}