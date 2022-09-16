package com.rachel.s1t1mobileapp2.Database;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface InterfaceMethods {
    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseFormServer>loginMethod(@Field("username")String username, @Field("password")String password);

    @FormUrlEncoded
    @POST("addusers.php")
    Call<ResponseFormServer>addusers(@Field("nama")String nama, @Field("nim")String nim, @Field("kelas")String kelas, @Field("password")String password, @Field("role")String role);

    @FormUrlEncoded
    @POST("Results.php")
    Call<ResponseFormServer>addResults(@Field("nim")String nim,
                                     @Field("algoritmaPemograman")String algoritmaPemograman,
                                     @Field("sistemDigital")String sistemDigital,
                                     @Field("bengkelPemograman")String bengkelPemograman,
                                     @Field("kalkulus")String kalkulus,
                                     @Field("aljabarLinear")String aljabarLinear,
                                     @Field("matematikaDiskrit")String matematikaDiskrit,
                                     @Field("pengantarTeknologi")String pengantarTeknologi,
                                     @Field("literasiTIK")String literasiTIK,
                                     @Field("konsepTeknologi")String konsepTeknologi,
                                     @Field("bahasaIndonesia")String bahasaIndonesia,
                                     @Field("analisisdanPerancangan")String analisisdanPerancangan,
                                       @Field("semester")String semester);
}
