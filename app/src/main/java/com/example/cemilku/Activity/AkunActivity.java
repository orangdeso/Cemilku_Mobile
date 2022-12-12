package com.example.cemilku.Activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.cemilku.API.APIRequestData;
import com.example.cemilku.API.RetroServer;
import com.example.cemilku.Fragment.Saya;
import com.example.cemilku.Model.DataItem;
import com.example.cemilku.Model.ResponseModel;
import com.example.cemilku.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AkunActivity extends AppCompatActivity {

    ImageButton btn_back, gambar;
    AppCompatButton btn_edit, btn_kembali, btn_save;

    TextView tv1, tv2, tv3, tv4, tvA1, tvA2, tvA3, tvA4, tvA5, et2;
    EditText et1, et3, et4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akun);

        tv1 = findViewById(R.id.akun_tvNama);
        tv2 = findViewById(R.id.akun_tvEmail);
        tv3 = findViewById(R.id.akun_tvTelpon);
        tv4 = findViewById(R.id.akun_tvAlamat);

        //Pemanggilan
        tv1.setText(SaveAccount.readDataPembeli(AkunActivity.this).getNama_user());
        tv2.setText(SaveAccount.readDataPembeli(AkunActivity.this).getEmail());
        tv3.setText(SaveAccount.readDataPembeli(AkunActivity.this).getTelpon());
        tv4.setText(SaveAccount.readDataPembeli(AkunActivity.this).getAlamat());

        btn_back = findViewById(R.id.akun_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btn_edit = findViewById(R.id.akun_btnEdit);
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"ResourceType", "MissingInflatedId"})
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AkunActivity.this);
                View v = getLayoutInflater().inflate(R.layout.activity_alert_simpan_akun, null);
                alertDialogBuilder.setView(v);
                AlertDialog alert = alertDialogBuilder.create();
                alert.show();
                gambar = v.findViewById(R.drawable.gambar);
                tvA1 = v.findViewById(R.id.alert_txt);
                tvA2 = v.findViewById(R.id.alert_txtNama);
                tvA3 = v.findViewById(R.id.alert_txtEmail);
                tvA4 = v.findViewById(R.id.alert_txtTelpon);
                tvA5 = v.findViewById(R.id.alert_txtAlamat);

                et1 = v.findViewById(R.id.alert_nama);
                et1.setText(SaveAccount.readDataPembeli(AkunActivity.this).getNama_user());

                et2 = v.findViewById(R.id.alert_email);
                et2.setText(SaveAccount.readDataPembeli(AkunActivity.this).getEmail());

                et3 = v.findViewById(R.id.alert_telpon);
                et3.setText(SaveAccount.readDataPembeli(AkunActivity.this).getTelpon());

                et4 = v.findViewById(R.id.alert_alamat);
                et4.setText(SaveAccount.readDataPembeli(AkunActivity.this).getAlamat());

                btn_save = v.findViewById(R.id.alert_btnSimpan);
                btn_save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        APIRequestData service = RetroServer.getClient().create(APIRequestData.class);
                        DataItem data = SaveAccount.readDataPembeli(AkunActivity.this);
                        Call<ResponseModel> simpan = service.setUbahAkun(data.getEmail(), et1.getText().toString(), et3.getText().toString(), et4.getText().toString());
                        simpan.enqueue(new Callback<ResponseModel>() {
                            @Override
                            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                                if (response.body().getKode()==1) {
                                    Toast.makeText(AkunActivity.this, "Berhasil Update Akun", Toast.LENGTH_SHORT).show();
                                    DataItem data1 = SaveAccount.readDataPembeli(AkunActivity.this);
                                    data1.setNama_user(et1.getText().toString());
                                    data1.setTelpon(et3.getText().toString());
                                    data1.setAlamat(et4.getText().toString());
                                    SaveAccount.writeDataPembeli(AkunActivity.this, data1);
                                    }
                                }

                            @Override
                            public void onFailure(Call<ResponseModel> call, Throwable t) {
                                    Toast.makeText(AkunActivity.this, "Server Error " + t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                        onBackPressed();
                    }
                });
                btn_kembali = v.findViewById(R.id.alert_btnBack);
                btn_kembali.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alert.cancel();
                    }
                });
            }
        });

    }
}
