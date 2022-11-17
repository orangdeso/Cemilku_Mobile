package com.example.cemilku.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroServer {
    private static final String baseURL = "http://192.168.137.57/Php Vs Code/Cemilku/";
    private static Retrofit retro;

    public static Retrofit getClient(){
        if(retro == null) {
            retro = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retro;
    }
}
