package com.example.cemilku.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.cemilku.API.APIRequestData;
import com.example.cemilku.API.RetroServer;
import com.example.cemilku.API.RvBarangInterface;
import com.example.cemilku.Adapter.AdapterBarang;
import com.example.cemilku.Adapter.AdapterHomeCari;
import com.example.cemilku.Fragment.Produk;
import com.example.cemilku.Model.BarangData;
import com.example.cemilku.Model.BarangResponse;
import com.example.cemilku.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class home_cari extends AppCompatActivity implements RvBarangInterface {

    ImageButton btn_back;
    RecyclerView rvData;
    RecyclerView.Adapter adData;
    RecyclerView.LayoutManager lmData;
    List<BarangData> listBarang = new ArrayList<>();
    AdapterHomeCari homeCari;
    SwipeRefreshLayout swipe;
    ProgressBar munyer;
    SearchView cariAsu;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_cari);

        munyer = findViewById(R.id.homeCari_pb);

        rvData = findViewById(R.id.homeCari_rv);
        rvData.setHasFixedSize(true);
        lmData = new LinearLayoutManager(home_cari.this, LinearLayoutManager.VERTICAL, false);
        rvData.setLayoutManager(lmData);

        btn_back = findViewById(R.id.homeCari_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        swipe = findViewById(R.id.homeCari_swipeRefresh);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe.setRefreshing(true);
                cariProduk();
                swipe.setRefreshing(false);
            }
        });

        cariAsu = findViewById(R.id.homeCari_searching);
        cariAsu.clearFocus();
        cariAsu.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return false;
            }
        });
    }

    private void filterList(String newText) {
        List<BarangData> filteredList = new ArrayList<>();
        for (BarangData item : listBarang) {
            if(item.getName_products().toLowerCase(Locale.ROOT).contains(newText.toLowerCase())) {
                filteredList.add(item);
            }
        }
        if (filteredList.isEmpty()) {
        } else {
            homeCari.setFilteredList(filteredList);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        cariProduk();
    }

    public void cariProduk() {
        munyer.setVisibility(View.VISIBLE);
        APIRequestData ai = RetroServer.getClient().create(APIRequestData.class);
        Call<BarangResponse> tampilData = ai.tampil_barang();
        tampilData.enqueue(new Callback<BarangResponse>() {
            @Override
            public void onResponse(Call<BarangResponse> call, Response<BarangResponse> response) {
                int kode = response.body().getKode();
                String message = response.body().getMessage();
                listBarang = response.body().getListBarang();

                //Toast.makeText(home_cari.this, "Kode : " +kode+" | Message : "+message, Toast.LENGTH_SHORT).show();

                homeCari = new AdapterHomeCari(home_cari.this, listBarang, home_cari.this);
                rvData.setAdapter(homeCari);
                homeCari.notifyDataSetChanged();

                munyer.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onFailure(@NonNull Call<BarangResponse> call, Throwable t) {
                Toast.makeText(home_cari.this, "Gagal Menghubungi Server : "+t.getMessage(), Toast.LENGTH_SHORT).show();
                System.out.println(t.getMessage() + " error ");
                munyer.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(home_cari.this, DetailProduk.class);

        intent.putExtra("idProducts", listBarang.get(position).getId_products());
        intent.putExtra("NamaProducts", listBarang.get(position).getName_products());
        intent.putExtra("Category", listBarang.get(position).getCategory());
        intent.putExtra("Harga", listBarang.get(position).getPrice());
        intent.putExtra("Deskripsi", listBarang.get(position).getDeskripsi());
        intent.putExtra("Image", listBarang.get(position).getImage());

        startActivity(intent);
    }
}