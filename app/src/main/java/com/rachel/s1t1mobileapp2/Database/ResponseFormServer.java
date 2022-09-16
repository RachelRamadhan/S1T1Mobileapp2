package com.rachel.s1t1mobileapp2.Database;

import com.google.gson.annotations.SerializedName;

public class ResponseFormServer {
    @SerializedName("status")
    String status;

    @SerializedName("resultcode")
    int resultCode;

    @SerializedName("role")
    String role;

    @SerializedName("nim")
    String nim;

    @SerializedName("nama")
    String nama;

    @SerializedName("password")
    String password;

    @SerializedName("date")
    String date;

    @SerializedName("userExist")
    String userExist;

    public String getDate() {
        return date;
    }

    public String getPassword() {
        return password;
    }

    public String getNim() {
        return nim;
    }

    public String getUserExist() {
        return userExist;
    }

    public String getNama() {
        return nama;
    }

    public String getStatus() {
        return status;
    }

    public int getResultCode() {
        return resultCode;
    }

    public String getRole() {
        return role;
    }
}
