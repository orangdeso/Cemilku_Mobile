package com.example.cemilku.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroServer {
    public static final String imgURL = "http://192.168.1.13/Php Vs Code/Cemilku/save_image/";
    private static final String baseURL = "http://192.168.1.13/Php Vs Code/Cemilku/";
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
