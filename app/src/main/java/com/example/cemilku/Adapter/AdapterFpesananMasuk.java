package com.example.cemilku.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cemilku.API.RvPesananMasuk;
import com.example.cemilku.Model.FpesananMasukData;
import com.example.cemilku.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterFpesananMasuk extends RecyclerView.Adapter<AdapterFpesananMasuk.PesananMasukHolder> {
    private Context ctx;
    private List<FpesananMasukData> data;
    //private  final RvPesananMasuk rvPesananMasuk;

    public AdapterFpesananMasuk(Context ctx, List<FpesananMasukData> data) {
        this.ctx = ctx;
        this.data = data;
        //this.rvPesananMasuk = rvPesananMasuk;
    }

    @NonNull
    @Override
    public PesananMasukHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_pesanan_masuk, parent, false);
        PesananMasukHolder holder = new PesananMasukHolder(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PesananMasukHolder holder, int position) {
        FpesananMasukData pm = data.get(position);

        holder.tvP1.setText(pm.getName_products());
        holder.tvP2.setText(pm.getPrice());
        holder.tvP3.setText(pm.getQty());
//        holder.tv1.setText(pm.getId_orders());
//        holder.tv2.setText(pm.getPlace_on());
//        holder.tv2.setText(pm.getNama_user());
        Picasso.get().load(data.get(position).getImage()).error(R.mipmap.ic_launcher).into(holder.ivP1);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class PesananMasukHolder extends RecyclerView.ViewHolder {
        TextView tvP1, tvP2, tvP3, tv1, tv2, tv3;
        ImageView ivP1;

        public PesananMasukHolder(@NonNull View itemView) {
            super(itemView);

            tvP1 = itemView.findViewById(R.id.cvPesanan_namaProduk);
            tvP2 = itemView.findViewById(R.id.cvPesanan_harga);
            tvP3 = itemView.findViewById(R.id.cvPesanan_qty);
//            tv1 = itemView.findViewById(R.id.rincianPesananMasuk_kodeTransaksi);
//            tv2 = itemView.findViewById(R.id.rincianPesananMasuk_tanggal);
//            tv3 = itemView.findViewById(R.id.rincianPesananMasuk_namaPembeli);
            ivP1 = itemView.findViewById(R.id.cvPesanan_image);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (rvPesananMasuk != null) {
//                        int pos = getAdapterPosition();
//
//                        if (pos != RecyclerView.NO_POSITION) {
//                            rvPesananMasuk.onItemClick(pos);
//                        }
//                    }
//                }
//            });
        }
    }
}
