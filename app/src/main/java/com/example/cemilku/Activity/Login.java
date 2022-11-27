package com.example.cemilku.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.example.cemilku.API.APIRequestData;
import com.example.cemilku.API.RetroServer;
import com.example.cemilku.Model.DataItem;
import com.example.cemilku.Model.ResponseModel;
import com.example.cemilku.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity implements OnClickListener{
    AppCompatButton btnLogin;
    TextView lupaPassword;
    String email, pass;
    TextInputEditText txt_username, txt_password;
    APIRequestData api;
    ImageButton ic_1;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //notif(Login.this);

        txt_username = findViewById(R.id.txt_username);
        txt_password = findViewById(R.id.txt_password);

        btnLogin = findViewById(R.id.btn_1);
        btnLogin.setOnClickListener(this);

        lupaPassword = findViewById(R.id.lupaPassword);
        lupaPassword.setOnClickListener(this);

        ic_1 = findViewById(R.id.ic_1);
        ic_1.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_1:
                email = txt_username.getText().toString();
                pass = txt_password.getText().toString();
                login(email,pass);
                break;
            case R.id.lupaPassword:
                Intent intent = new Intent(this, LupaPassword2.class);
                startActivity(intent);
                break;
            case R.id.ic_1:
                onBackPressed();
                break;
        }
    }

//    public void notif(Activity activity){
//        //change color notif bar
//        Window window = this.getWindow();
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        window.setStatusBarColor(this.getResources().getColor(R.color.abang));
//        //set icons notifbar
//        View decor = activity.getWindow().getDecorView();
//        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//    }

    private void login(String email, String pass) {
        if(email.equals("")||pass.equals("")){
            //Toast.makeText(Login.this, "Harap Lengkapi Data", Toast.LENGTH_SHORT).show();
            if(email.equals("")){
                Toast.makeText(Login.this, "Email Kosong", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(Login.this, "Password Kosong", Toast.LENGTH_SHORT).show();
            }
        }else{
            APIRequestData api = RetroServer.getClient().create(APIRequestData.class);
            Call<ResponseModel> loginCall = api.loginResponse(email, pass);
            loginCall.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    if (response.body().getKode() == 1){
                        DataItem dataItem = response.body().getData().get(0);
                        SaveAccount.writeDataPembeli(Login.this, dataItem);
                        Toast.makeText(Login.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this, SplashScreen.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(Login.this, "Username atau Password Salah", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    Toast.makeText(Login.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        }
        }


//    private void login(String email, String pass) {
//
//        if (email.equals("") || pass.equals("")) {
//            Toast.makeText(Login.this, "Mohon isi semua data", Toast.LENGTH_LONG).show();
//        } else {
//            RetroServer ApiClient;
//            api = RetroServer.getClient().create(APIRequestData.class);
//            Call<Login> loginCall = api.loginResponse(email, pass);
//            loginCall.enqueue(new Callback<Login>() {
//                @Override
//                public void onResponse(Call<Login> call, Response<Login> response) {
//                    if (response.body() != null && response.isSuccessful() && response.body().isStatus()) {
//
//
//                        //Ini untuk pindah
//                        Toast.makeText(Login.this, response.body().getData().getNama(), Toast.LENGTH_SHORT).show();
//                        System.out.println("nama saya adalah" + response.body().getData().getNama());
//                        Intent intent = new Intent(login.this, home.class);
//                        intent.putExtra("namaUser", response.body().getData().getNama());
//                        startActivity(intent);
//                        finish();
//
//                    } else {
//                        Toast.makeText(login.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
//
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<Login> call, Throwable t) {
//                    Toast.makeText(login.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//                }
//            });
        }






