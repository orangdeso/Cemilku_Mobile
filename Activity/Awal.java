package com.example.cemilku.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.cemilku.R;

public class Awal extends AppCompatActivity {

    private int waktu_loading = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awal);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent itn = new Intent(Awal.this, MainActivity.class);
                startActivity(itn);
                finish();
            }
        }, waktu_loading);

    }
}