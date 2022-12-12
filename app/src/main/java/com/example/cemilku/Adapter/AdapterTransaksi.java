package com.example.cemilku.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cemilku.API.RvTransaksi;
import com.example.cemilku.Activity.PesananMasuk;
import com.example.cemilku.Model.TransaksiData;
import com.example.cemilku.Model.TransaksiResponse;
import com.example.cemilku.R;

import java.util.List;

public class AdapterTransaksi extends RecyclerView.Adapter<AdapterTransaksi.TransaksiHolder>{
    private Context ctx;
    private List<TransaksiData> data;
    private  final RvTransaksi rvTransaksi;

    public AdapterTransaksi(Context ctx, List<TransaksiData> data, RvTransaksi rvTransaksi) {
        this.ctx = ctx;
        this.data = data;
        this.rvTransaksi = rvTransaksi;
    }

    @NonNull
    @Override
    public TransaksiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_design, parent, false);
        TransaksiHolder holder = new TransaksiHolder(layout, rvTransaksi);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TransaksiHolder holder, int position) {
        TransaksiData td = data.get(position);

        holder.tvS1.setText(td.getNama_user());
        holder.tvS3.setText(td.getId_orders());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class TransaksiHolder extends RecyclerView.ViewHolder {
        TextView tvS1, tvS2, tvS3;
        ImageView ivS1;

        public TransaksiHolder (@NonNull View itemView, RvTransaksi rvPesananMasuk) {
            super(itemView);

            tvS1 = itemView.findViewById(R.id.singleRow_txtheader);
            tvS2 = itemView.findViewById(R.id.singleRow_txtDesc);
            tvS3 = itemView.findViewById(R.id.singleRow_ID);
            ivS1 = itemView.findViewById(R.id.singleRow_img);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (rvTransaksi != null) {
                        int pos = getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION) {
                            rvTransaksi.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
