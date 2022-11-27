package com.example.cemilku.Fragment;

//import static com.example.cemilku.R.id.home_rvProdukReady;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cemilku.API.APIRequestData;
import com.example.cemilku.API.RetroServer;
import com.example.cemilku.Activity.SaveAccount;
import com.example.cemilku.Model.JumlahBarangData;
import com.example.cemilku.Model.JumlahBarangResponse;
import com.example.cemilku.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentHome#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentHome extends Fragment {

    TextView nama1, nama2, tv_1, tv_2, tv_3, tv_4;
    List<JumlahBarangData> data;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentHome() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static FragmentHome newInstance(String param1, String param2) {
        FragmentHome fragment = new FragmentHome();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

//    private RecyclerView rvData;
//    private RecyclerView.Adapter adData;
//    private RecyclerView.LayoutManager lmData;
//    private List<ProdukReadyData> listData = new ArrayList<>();

//    @SuppressLint("MissingInflatedId")
//    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //Menampilkan nama_user
        nama1 = view.findViewById(R.id.home_username);
        nama2 = view.findViewById(R.id.home_alamat);

        //Pemanggilan nama_user dan alamat
        nama1.setText("Selamat Datang, "+ SaveAccount.readDataPembeli(getActivity()).getNama_user());
        nama2.setText(SaveAccount.readDataPembeli(getActivity()).getAlamat());

        tv_1 = view.findViewById(R.id.home_jumlahBarang);
        tv_2 = view.findViewById(R.id.home_pesananMasuk);
        tv_3 = view.findViewById(R.id.home_barangKeluar);
        tv_4 = view.findViewById(R.id.home_pendapatan);


//        jumlah_produk();

//        JumlahBarangData jbd = data.get(position);


//        rvData = view.findViewById(home_rvProdukReady);
//        lmData = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        TextView tv_1, tv_2, tv_3, tv_4;
//
//        tv_1 = (TextView) getView().findViewById(R.id.home_jumlahBarang);
//        String nama = getActivity().getIntent().getStringExtra("TOTAL");
//        tv_1.setText(nama);
//        intent.putExtra("TOTAL", response.body().getData().getTOTAL));
//
////        rvData = getView().findViewById(R.id.rv_data);
////        lmData = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false);
////        rvData.setLayoutManager(lmData);
////        retrieveData();
////
////
////        rvData = view.findViewById(R.id.rv_data);
////        rvData.setHasFixedSize(true);
////        rvData.setLayoutManager(new GridLayoutManager(getActivity(),2));
//    }

    public void jumlah_produk() {

        APIRequestData api = RetroServer.getClient().create(APIRequestData.class);
        Call<JumlahBarangResponse> tampilData = api.jumlah_produk();
        tampilData.enqueue(new Callback<JumlahBarangResponse>() {
            @Override
            public void onResponse(Call<JumlahBarangResponse> call, Response<JumlahBarangResponse> response) {
                int kode = response.body().getKode();
                String message = response.body().getMessage();

                Toast.makeText(getActivity(), "Kode : "+kode+"| message : "+message, Toast.LENGTH_SHORT).show();

                data  = response.body().getData();
            }

            @Override
            public void onFailure(Call<JumlahBarangResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
            }
        });
    }

//    public void produk_ready(){
//        APIRequestData ardData = RetroServer.getClient().create(APIRequestData.class);
//        Call<ProdukReadyResponse> tampilData = ardData.produk_ready();
//
//        tampilData.enqueue(new Callback<ProdukReadyResponse>() {
//            @Override
//            public void onResponse(Call<ProdukReadyResponse> call, Response<ProdukReadyResponse> response) {
//                int kode = response.body().getKode();
//                String message = response.body().getMessage();
//
//                Toast.makeText(getActivity(), "Kode : "+kode+" | Message : "+message, Toast.LENGTH_SHORT).show();
//
//                listData = response.body().getData();
//
//                adData = new AdapterProdukReady(getActivity(), listData);
//                rvData.setAdapter(adData);
//                adData.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onFailure(Call<ProdukReadyResponse> call, Throwable t) {
//                Toast.makeText(getActivity(), "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}