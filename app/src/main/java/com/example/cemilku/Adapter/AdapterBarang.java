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

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

public class AdapterBarang extends  RecyclerView.Adapter<AdapterBarang.BarangHolder> {
    private Context ctx;
    private List<BarangData> data;
    private final RvBarangInterface rvBarangInterface;

//    public interface adapterBarangListener{
//        void selectedItemListener(int positionOfItemClicked);
//    }

    public AdapterBarang(Context ctx, List<BarangData> data, RvBarangInterface rvBarangInterface) {
        this.ctx = ctx;
        this.data = data;
        this.rvBarangInterface = rvBarangInterface;
    }

    @NonNull
    @Override
    public BarangHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produk, parent, false);
        BarangHolder holder = new BarangHolder(layout, rvBarangInterface);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BarangHolder holder, int position) {
        BarangData bd = data.get(position);

        holder.tv_1.setText(bd.getName_products());
        holder.tv_2.setText(bd.getPrice());
//        String hrg = bd.getPrice();
//        int rp = Integer.parseInt(hrg);
//        String torp = toRupiah(rp);
        Picasso.get().load(data.get(position).getImage()).error(R.mipmap.ic_launcher).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class BarangHolder extends RecyclerView.ViewHolder {
        TextView tv_1, tv_2, tv_3, tv_4;
        ImageView img;

        public BarangHolder(@NonNull View itemView, RvBarangInterface rvBarangInterface) {
            super(itemView);

            tv_1 = itemView.findViewById(R.id.np1);
            tv_2 = itemView.findViewById(R.id.hp1);
            tv_4 = itemView.findViewById(R.id.id_products);
            img = itemView.findViewById(R.id.produk1);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (rvBarangInterface != null) {
                        int pos = getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION) {
                            rvBarangInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }

    public static String toRupiah(int rupiah){
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator('.');
        formatRp.setGroupingSeparator('.');
        kursIndonesia.setDecimalFormatSymbols(formatRp);
        return kursIndonesia.format(rupiah);
    }
}
