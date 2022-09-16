package com.rachel.s1t1mobileapp2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rachel.s1t1mobileapp2.Database.DbContract;
import com.rachel.s1t1mobileapp2.Database.InterfaceMethods;
import com.rachel.s1t1mobileapp2.Database.ResponseFormServer;
import com.rachel.s1t1mobileapp2.R;
import com.rachel.s1t1mobileapp2.activity_Admin.dashboard_adm;
import com.rachel.s1t1mobileapp2.activity_Dosen.Dashboard_dsn;
import com.rachel.s1t1mobileapp2.activity_Mahasiswa.Dashboard_mhs;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    //variable declaration for input fields
    Button btn_login;
    EditText username,password;
    TextView aboutITTJ, Igracias;
    ProgressDialog progressDialog;

    //when user login, save his/her details in Shared Preference
    String SHARED_PREF="myfref_xml";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //now, initialize variable
        username = findViewById(R.id.edtusername);
        password = findViewById(R.id.edtpassword);
        btn_login = findViewById(R.id.btn_Login);
        progressDialog = new ProgressDialog(Login.this);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        String nim = sharedPreferences.getString("nim","null");
        String role = sharedPreferences.getString("role","null");

        if(nim!=null) {
            if(role.equals("mahasiswa")){
                Intent intent=new Intent(getApplicationContext(), Dashboard_mhs.class);
                startActivity(intent);
                finish();
            }
            else if(role.equals("dosen")){
                Intent intent=new Intent(getApplicationContext(), Dashboard_dsn.class);
                startActivity(intent);
                finish();
            }
            else if(role.equals("admin")){
                Intent intent=new Intent(getApplicationContext(), dashboard_adm.class);
                startActivity(intent);
                finish();
            }
        }


        //here , when login button is clicked  validate the form
        //if all fields are filled then we call 'login' method
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(username.getText().toString().equals("")) {
                 username.setError("username is required");
                } else if (password.getText().toString().equals("")) {
                    password.setError("password is required");
                } else {
                        progressDialog.setTitle("please wait...");
                        progressDialog.setCanceledOnTouchOutside(false);
                        //progressDialog.setCancelable(false);
                        progressDialog.show();
                }
            }
        });
    }

    //login method
    void login() {
        String nim2=username.getText().toString();
        String password2=password.getText().toString();

        Call<ResponseFormServer>call = DbContract.getclient().create(InterfaceMethods.class).loginMethod(nim2,password2);
        call.enqueue(new Callback<ResponseFormServer>() {
            @Override
            public void onResponse(Call<ResponseFormServer> call, Response<ResponseFormServer> response) {

                if(response.code()==200)
                {
                    if(response.body().getStatus().equals("ok"))
                    {
                        if(response.body().getResultCode() == 1)
                        {
                            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            editor.putString("nim", response.body().getNim());
                            editor.putString("role", response.body().getRole());
                            editor.putString("nama", response.body().getNama());
                            editor.putString("password", response.body().getPassword());
                            editor.putString("date", response.body().getDate());
                            editor.apply();

                            Toast.makeText(getApplicationContext(),"Welcome", Toast.LENGTH_LONG).show();
                            String role  = response.body().getRole();

                            //here we use switch case to direct user to system resources
                            switch (role){
                                case "mahasiswa":
                                    Intent intent=new Intent(getApplicationContext(), Dashboard_mhs.class);
                                    startActivity(intent);
                                    finish();
                                    progressDialog.dismiss();
                                    break;

                                case "dosen":
                                    Intent intent2=new Intent(getApplicationContext(), Dashboard_dsn.class);
                                    startActivity(intent2);
                                    finish();
                                    progressDialog.dismiss();
                                    break;

                                case "admin":
                                    Intent intent3=new Intent(getApplicationContext(), dashboard_adm.class);
                                    startActivity(intent3);
                                    finish();
                                    progressDialog.dismiss();
                                    break;
                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Invalid username or password", Toast.LENGTH_LONG).show();
                        }
                    }
                }
                else
                {
                    //no connection
                }
            }

            @Override
            public void onFailure(Call<ResponseFormServer> call, Throwable t) {

            }
        });

    }
}