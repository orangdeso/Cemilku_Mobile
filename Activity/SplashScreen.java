package com.example.cemilku.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.cemilku.R;

public class SplashScreen extends AppCompatActivity {

    private int waktu_loading = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent itn = new Intent(SplashScreen.this, Home.class);
                startActivity(itn);
                finish();
            }
        }, waktu_loading);
    }

}