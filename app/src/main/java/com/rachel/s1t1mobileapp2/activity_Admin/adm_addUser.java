package com.rachel.s1t1mobileapp2.activity_Admin;

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

public class adm_addUser extends AppCompatActivity {

    EditText nama, nim, password;
    Spinner role, kelas;
    Button close, addUser;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_add_user);

        nama = findViewById(R.id.edt_add_name);
        nim = findViewById(R.id.edt_add_nim);
        password = findViewById(R.id.edt_add_password);

        role = findViewById(R.id.spn_add_role);
        kelas = findViewById(R.id.spn_pilih_kelas);

        addUser = findViewById(R.id.btn_addUser);
        close = findViewById(R.id.btn_close);

        progressDialog=new ProgressDialog(this);

        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nim.getText().toString().equals(""))
                {
                    nim.setText("required");
                }
                else if(kelas.getSelectedItem().toString().equals("pilih kelas"))
                {
                    Toast.makeText(getApplicationContext(),"Pilih Kelas", Toast.LENGTH_SHORT).show();
                }
                else if(role.getSelectedItem().toString().equals("pilih role"))
                {
                    Toast.makeText(getApplicationContext(),"pilih role", Toast.LENGTH_SHORT).show();
                }
                else if(nama.getText().toString().equals("")){
                    nama.setText("required");
                }
                else {
                    tambahUser();
                    progressDialog.setTitle("please wait...");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                }
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    void tambahUser() {
        String nama2= nama.getText().toString();
        String nim2= nim.getText().toString();
        String kelas2 = kelas.getSelectedItem().toString();
        String password2 = password.getText().toString();
        String role2 = role.getSelectedItem().toString();

        Call<ResponseFormServer> call=DbContract.getclient().create(InterfaceMethods.class).addusers(nama2, nim2, kelas2, password2, role2);
        call.enqueue(new Callback<ResponseFormServer>() {
            @Override
            public void onResponse(Call<ResponseFormServer> call, Response<ResponseFormServer> response) {
                if (response.code()==200){
                    if (response.body().getStatus().equals("ok")){
                        if (response.body().getResultCode()==1){
                            Toast.makeText(getApplicationContext(),"record added!", Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"the user has already exist is :"+response.body().getUserExist(), Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                        }
                    }
                }
                else
                {
                    //is not 200
                }
            }

            @Override
            public void onFailure(Call<ResponseFormServer> call, Throwable t) {

            }
        });
    }
}