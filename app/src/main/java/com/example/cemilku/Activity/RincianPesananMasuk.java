package com.example.cemilku.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cemilku.API.APIRequestData;
import com.example.cemilku.API.RetroServer;
import com.example.cemilku.Adapter.AdapterFpesananMasuk;
import com.example.cemilku.Adapter.AdapterTransaksi;
import com.example.cemilku.Model.BarangResponse;
import com.example.cemilku.Model.FpesananMasukData;
import com.example.cemilku.Model.FpesananMasukResponse;
import com.example.cemilku.Model.TransaksiResponse;
import com.example.cemilku.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RincianPesananMasuk extends AppCompatActivity {
    TextView tvT1, tvT2, tvT3, tvT4;
    ImageButton btn_back;
    AppCompatButton btn_submit;
    RecyclerView rvData;
    RecyclerView.Adapter adData;
    RecyclerView.LayoutManager lmData;
    List<FpesananMasukData> listPesanan = new ArrayList<>();
    SwipeRefreshLayout segar;
    ProgressBar anjing;
    String id_orders, tanggal, namaUser, totalPrice;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rincian_pesanan_masuk);

        id_orders = getIntent().getStringExtra("idOrders");
        tanggal = getIntent().getStringExtra("Tanggal");
        namaUser = getIntent().getStringExtra("NamaUser");
        totalPrice = getIntent().getStringExtra("TotalPrice");

        tvT1 = findViewById(R.id.rincianPesananMasuk_kodeTransaksi);
        tvT2 = findViewById(R.id.rincianPesananMasuk_tanggal);
        tvT3 = findViewById(R.id.rincianPesananMasuk_namaPembeli);
        tvT4 = findViewById(R.id.rincianPesananMasuk_totalPesanan);

        tvT1.setText(id_orders);
        tvT2.setText(tanggal);
        tvT3.setText(namaUser);
        tvT4.setText(totalPrice);

        btn_back = findViewById(R.id.rincianPesananMasuk_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        anjing = findViewById(R.id.rincianPesananMasuk_pbData);

        rvData = findViewById(R.id.rincianPesananMasuk_rv);
        rvData.setHasFixedSize(true);
        lmData = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvData.setLayoutManager(lmData);

        tampilRincian();

        btn_submit = findViewById(R.id.rincianPesananMasuk_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProses();
            }
        });


//        segar = findViewById(R.id.rincianPesananMasuk_refresh);
//        segar.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                segar.setRefreshing(true);
//                tampilRincian();
//                segar.setRefreshing(false);
//            }
//        });
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        tampilRincian();
//    }

    private void tampilRincian(){
        //segar.setVisibility(View.VISIBLE);
        APIRequestData ai = RetroServer.getClient().create(APIRequestData.class);
        Call<FpesananMasukResponse> tampilData = ai.rincianPesanan(id_orders);
        tampilData.enqueue(new Callback<FpesananMasukResponse>() {
            @Override
            public void onResponse(Call<FpesananMasukResponse> call, Response<FpesananMasukResponse> response) {
                int kode = response.body().getKode();
                String message = response.body().getMessage();

                //Toast.makeText(RincianPesananMasuk.this, "Kode : " +kode+" | Message : "+message, Toast.LENGTH_SHORT).show();

                listPesanan = response.body().getData();
                adData = new AdapterFpesananMasuk(RincianPesananMasuk.this, listPesanan);
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();

                //segar.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onFailure(@NonNull Call<FpesananMasukResponse> call, Throwable t) {
                Toast.makeText(RincianPesananMasuk.this, "Gagal Menghubungi Server : "+t.getMessage(), Toast.LENGTH_SHORT).show();
                //segar.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void updateProses(){
        APIRequestData api = RetroServer.getClient().create(APIRequestData.class);
        Call<TransaksiResponse> updateProduk = api.updateProses(tvT1.getText().toString());
        updateProduk.enqueue(new Callback<TransaksiResponse>() {
            @Override
            public void onResponse(Call<TransaksiResponse> call, Response<TransaksiResponse> response) {
                int kode = response.body().getKode();
                String message = response.body().getMessage();

                Toast.makeText(RincianPesananMasuk.this, "Berhasil Update Pesanan ke Proses", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RincianPesananMasuk.this, PesananMasuk.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<TransaksiResponse> call, Throwable t) {
                Toast.makeText(RincianPesananMasuk.this, "Gagal Menghubungi Server"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}