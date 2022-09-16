package com.rachel.s1t1mobileapp2.Database;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DbContract {
    private static final String BASE_URL="http://localhost/s1ti/login.php/";
    private static Retrofit retrofit=null;
    public static Retrofit getclient()
    {
        if(retrofit == null)
        {
            retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
            return retrofit;
    }
}
