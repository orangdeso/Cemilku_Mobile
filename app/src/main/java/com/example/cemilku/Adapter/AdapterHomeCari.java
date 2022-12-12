package com.example.cemilku.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cemilku.API.RvBarangInterface;
import com.example.cemilku.Model.BarangData;
import com.example.cemilku.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterHomeCari extends RecyclerView.Adapter<AdapterHomeCari.HolderDataCari>{
    private Context ctx;
    private List<BarangData> data;
    private final RvBarangInterface rvBarangInterface;

    public AdapterHomeCari(Context ctx, List<BarangData> data, RvBarangInterface rvBarangInterface) {
        this.ctx = ctx;
        this.data = data;
        this.rvBarangInterface = rvBarangInterface;
    }

    public void setFilteredList(List<BarangData> filteredList) {
        this.data = filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HolderDataCari onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_produk, parent, false);
        AdapterHomeCari.HolderDataCari holder = new AdapterHomeCari.HolderDataCari(layout, rvBarangInterface);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderDataCari holder, int position) {
        BarangData bd = data.get(position);

        holder.tv1.setText(bd.getName_products());
        holder.tv3.setText(bd.getPrice());
        Picasso.get().load(data.get(position).getImage()).error(R.mipmap.ic_launcher).into(holder.kntl);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class HolderDataCari extends RecyclerView.ViewHolder{
        TextView tv1, tv2, tv3, tv4;
        ImageView kntl;

        public HolderDataCari(@NonNull View itemView, RvBarangInterface rvBarangInterface) {
            super(itemView);

            kntl = itemView.findViewById(R.id.cvProduk_image);
            tv1 = itemView.findViewById(R.id.cvProduk_nameProduk);
            tv2 = itemView.findViewById(R.id.cvProduk_txtRp);
            tv3 = itemView.findViewById(R.id.cvProduk_harga);
            tv4 = itemView.findViewById(R.id.cvProduk_idProducts);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (rvBarangInterface != null) {
                        int pos = getAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION) {
                            rvBarangInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
