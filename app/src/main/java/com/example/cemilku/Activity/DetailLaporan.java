package com.example.cemilku.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cemilku.API.APIRequestData;
import com.example.cemilku.API.RetroServer;
import com.example.cemilku.Adapter.AdapterLaporan;
import com.example.cemilku.Fragment.Laporan;
import com.example.cemilku.Model.LaporanData;
import com.example.cemilku.Model.LaporanResponse;
import com.example.cemilku.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

public class DetailLaporan extends AppCompatActivity {
    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<LaporanData> listData = new ArrayList<>();
    TextView nama,tgl1,tgl2;



    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_laporan);

        retrieve();

        nama = findViewById(R.id.detailLaporan_txt_nama);
        nama.setText(SaveAccount.readDataPembeli(getApplicationContext()).getNama_user());
        tgl1 = findViewById(R.id.laporan_et_dariTanggal);
        tgl2 = findViewById(R.id.laporan_et_sampaiTanggal);


        rvData = findViewById(R.id.rvlaporan);
        rvData.setHasFixedSize(true);
        lmData = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvData.setLayoutManager(lmData);
    }
    public void retrieve(){
        APIRequestData ai = RetroServer.getClient().create(APIRequestData.class);
        Call<LaporanResponse> tampilData = ai.laporan(String.valueOf(tgl1)); ai.laporan(String.valueOf(tgl2));
        tampilData.enqueue(new Callback<LaporanResponse>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<LaporanResponse> call, Response<LaporanResponse> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getMessage();

                Toast.makeText(DetailLaporan.this, "Kode : " +kode+" | Pesan : "+pesan, Toast.LENGTH_SHORT).show();

                listData = response.body().getData();
                adData = new AdapterLaporan(DetailLaporan.this, listData);
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();

            }

            @Override
            public void onFailure(@NonNull Call<LaporanResponse> call, Throwable t) {
                Toast.makeText(DetailLaporan.this, "Gagal Menghubungi Server : "+t.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });
    }


}
