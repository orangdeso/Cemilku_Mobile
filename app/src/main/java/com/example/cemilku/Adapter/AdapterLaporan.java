package com.example.cemilku.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cemilku.Model.LaporanData;
import com.example.cemilku.R;

import java.util.List;

public class AdapterLaporan extends RecyclerView.Adapter<AdapterLaporan.HolderData>{

    private Context ctx;
    private List<LaporanData> data;

    public AdapterLaporan(Context ctx, List<LaporanData> data) {
        this.ctx = ctx;
        this.data = data;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_orders, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        LaporanData lp = data.get(position);
 
        holder.tv1.setText(lp.getIdOrders());
        holder.tv2.setText(lp.getPlacedOn());
        holder.tv3.setText(lp.getTotalPrice());
        holder.tv4.setText(lp.getQty());
        holder.tv5.setText(lp.getNamaUser());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        TextView tv1, tv2, tv3, tv4, tv5;

        public HolderData(@NonNull View itemView) {
            super(itemView);

            tv1 = itemView.findViewById(R.id.cv_idOrders);
            tv2 = itemView.findViewById(R.id.cv_TanggalTransaksi);
            tv3 = itemView.findViewById(R.id.cv_NamaProducts);
            tv4 = itemView.findViewById(R.id.cv_pendapatan);
            tv5 = itemView.findViewById(R.id.cv_namaPembeli);
        }
    }
}
