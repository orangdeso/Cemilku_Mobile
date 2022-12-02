package com.example.cemilku.Activity;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.cemilku.R;

public class About extends AppCompatActivity {

    ImageButton back;
    AppCompatButton btn1, btn2, btn3, btn4;
//    private static final String TAG = "About";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        back = findViewById(R.id.about_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btn2 = findViewById(R.id.about_btn_instagram);
        btn2.setOnClickListener(view -> {
            String wpurl = "https://www.instagram.com/bung_choi/";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(wpurl));
            startActivity(intent);
        });

        btn3 = findViewById(R.id.about_btn_email);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:ahmadzaqi98mmm@gmail.com")));
            }
        });

        btn4 = findViewById(R.id.about_btn_whatsaap);
        btn4.setOnClickListener(view -> {
            String wpurl = "https://wa.me/+6282142568403?text=Tim Developer,";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(wpurl));
            startActivity(intent);
        });
    }
}