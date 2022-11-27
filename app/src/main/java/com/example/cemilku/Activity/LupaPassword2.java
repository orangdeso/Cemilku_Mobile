package com.example.cemilku.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageButton;

import com.example.cemilku.R;

public class LupaPassword2 extends AppCompatActivity {

    AppCompatButton btn_lupaPassword;
    ImageButton btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lupa_password2);

        btn_lupaPassword = findViewById(R.id.lupaPassword_btn);
        btn_back = findViewById(R.id.lupaPassword_back);
        btn_back.setOnClickListener(view -> onBackPressed());

        btn_lupaPassword.setOnClickListener(view -> {
            String wpurl = "https://wa.me/+6282142568403?text=Tim Developer, Saya ingin mereset password.";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(wpurl));
            startActivity(intent);
        });
    }
}