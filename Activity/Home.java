package com.example.cemilku.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.cemilku.FragmentHome;
import com.example.cemilku.Laporan;
import com.example.cemilku.Pesanan;
import com.example.cemilku.Produk;
import com.example.cemilku.R;
import com.example.cemilku.Saya;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView btNav = (BottomNavigationView) findViewById(R.id.bottomNavigationView);

        btNav = findViewById(R.id.bottomNavigationView);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,new FragmentHome()).commit();

        btNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selected = null;
                switch (item.getItemId()) {
                    case R.id.fragmentHome:
                        selected = new FragmentHome();
                        break;
                    case R.id.fragment_produk:
                        selected = new Produk();
                        break;
                    case R.id.fragment_pesanan:
                        selected = new Pesanan();
                        break;
                    case R.id.fragment_laporan:
                        selected = new Laporan();
                        break;
                    case R.id.fragment_saya:
                        selected = new Saya();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, selected).commit();
                return true;
            }
        });
    }

}