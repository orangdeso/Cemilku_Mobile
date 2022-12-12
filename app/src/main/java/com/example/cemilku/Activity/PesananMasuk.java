package com.example.cemilku.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.cemilku.API.APIRequestData;
import com.example.cemilku.API.RetroServer;
import com.example.cemilku.API.RvTransaksi;
import com.example.cemilku.Adapter.AdapterTransaksi;
import com.example.cemilku.Model.TransaksiData;
import com.example.cemilku.Model.TransaksiResponse;
import com.example.cemilku.R;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PesananMasuk extends AppCompatActivity implements RvTransaksi {

    ImageButton btn_back;
    RecyclerView rvData;
    RecyclerView.Adapter adData;
    RecyclerView.LayoutManager lmData;
    List<TransaksiData> listTransaksi = new ArrayList<>();
    SwipeRefreshLayout refresh;
    ProgressBar pb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesanan_masuk);

        btn_back = findViewById(R.id.pesananMasuk_btnBack);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        pb = findViewById(R.id.pesananMasuk_pbData);

        rvData = findViewById(R.id.pesananMasuk_rv);
        rvData.setHasFixedSize(true);
        lmData = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvData.setLayoutManager(lmData);

        refresh = findViewById(R.id.pesananMasuk_swipeRefresh);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh.setRefreshing(true);
                tampilTransaksi();
                refresh.setRefreshing(false);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        tampilTransaksi();
    }

    private void tampilTransaksi(){
        pb.setVisibility(View.VISIBLE);
        APIRequestData ai = RetroServer.getClient().create(APIRequestData.class);
        Call<TransaksiResponse> tampilData = ai.tampilTransaksi();
        tampilData.enqueue(new Callback<TransaksiResponse>() {
            @Override
            public void onResponse(Call<TransaksiResponse> call, Response<TransaksiResponse> response) {
                int kode = response.body().getKode();
                String message = response.body().getMessage();

                //Toast.makeText(PesananMasuk.this, "Kode : " +kode+" | Message : "+message, Toast.LENGTH_SHORT).show();

                listTransaksi = response.body().getData();
                adData = new AdapterTransaksi(PesananMasuk.this, listTransaksi, PesananMasuk.this);
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();

                pb.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onFailure(@NonNull Call<TransaksiResponse> call, Throwable t) {
                Toast.makeText(PesananMasuk.this, "Gagal Menghubungi Server : "+t.getMessage(), Toast.LENGTH_SHORT).show();
                pb.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(PesananMasuk.this, RincianPesananMasuk.class);

        intent.putExtra("idOrders", listTransaksi.get(position).getId_orders());
        intent.putExtra("Tanggal", listTransaksi.get(position).getPlaced_on());
        intent.putExtra("NamaUser", listTransaksi.get(position).getNama_user());
        intent.putExtra("TotalPrice", listTransaksi.get(position).getTotal_price());

        startActivity(intent);
    }
}