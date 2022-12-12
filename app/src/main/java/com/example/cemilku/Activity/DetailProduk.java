package com.example.cemilku.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cemilku.API.APIRequestData;
import com.example.cemilku.API.RetroServer;
import com.example.cemilku.Model.BarangData;
import com.example.cemilku.Model.BarangResponse;
import com.example.cemilku.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.lang.reflect.Type;
import java.util.Stack;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailProduk extends AppCompatActivity {
    private Uri ur;
    TextView tv1, tv2, tv3, tv4, tv5; //DetailProduk
    ImageView img;
    AppCompatButton btn_edit, btn_delete;
    ImageButton btn_back;
    AlertDialog.Builder builder;

    //AlertEdit
    TextView tvA1;
    TextView tvA2;
    TextView tvA3;
    TextView tvA4;
    TextView tvA5;
    TextView tvA6;
    ImageView imageAlert;
    AppCompatButton btnSimpanAlert;

    //String id_products, name_products, category, price, Deskripsi, image;
    String yName, yCategory, yPrice, yDeskripsi, yId, xId;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_produk);

        String id_products = getIntent().getStringExtra("idProducts");  //setOnClick
        String name_products = getIntent().getStringExtra("NamaProducts");
        String category = getIntent().getStringExtra("Category");
        String price = getIntent().getStringExtra("Harga");
        String Deskripsi = getIntent().getStringExtra("Deskripsi");
        String image = getIntent().getStringExtra("Image");

        img = findViewById(R.id.detailProduct_image);
        tv1 = findViewById(R.id.detailProduk_tvNP);
        tv2 = findViewById(R.id.detailProduk_tvCategory);
        tv3 = findViewById(R.id.detailProduk_tvHarga);
        tv4 = findViewById(R.id.detailProduk_tvDeskripsi);
        tv5 = findViewById(R.id.detailProduct_idProducts);

        tv1.setText(name_products);
        tv2.setText(category);
        tv3.setText(price);
        tv4.setText(Deskripsi);
        tv5.setText(id_products);
        Picasso.get().load(image).error(R.mipmap.ic_launcher).into(img);

        btn_back = findViewById(R.id.detailProduk_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btn_edit = findViewById(R.id.detailProduk_btnEdit);
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alerDialogBuilder = new AlertDialog.Builder(DetailProduk.this);
                View v = getLayoutInflater().inflate(R.layout.alert_edit_produk, null);
                alerDialogBuilder.setView(v);
                AlertDialog alert = alerDialogBuilder.create();
                alert.show();

                tvA1 = v.findViewById(R.id.alert_editProduk_tvTitle);
                tvA2 = v.findViewById(R.id.alert_editProduk_namaProduct);
                tvA3 = v.findViewById(R.id.alert_editProduk_Kategori);
                tvA4 = v.findViewById(R.id.alert_editProduk_price);
                tvA5 = v.findViewById(R.id.alert_editProduk_deskripsi);
                tvA6 = v.findViewById(R.id.alert_editProduk_idProducts);

                imageAlert = v.findViewById(R.id.alert_editProduk_image);
                imageAlert.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getImage();
                    }
                });

                tvA2.setText(name_products);
                tvA3.setText(category);
                tvA4.setText(price);
                tvA5.setText(Deskripsi);
                tvA6.setText(id_products);
                Picasso.get().load(image).error(R.mipmap.ic_launcher).into(imageAlert);

                btnSimpanAlert = v.findViewById(R.id.alert_editProduk_btnSimpan);
                btnSimpanAlert.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String idPr = tvA6.getText().toString();
                        String namePr = tvA2.getText().toString();
                        String Cate = tvA3.getText().toString();
                        String Price = tvA4.getText().toString();
                        String Desk = tvA5.getText().toString();

                        updateProduk(idPr, namePr, Cate, Price, Desk, image);

                        //Toast.makeText(DetailProduk.this, "Berhasil Update Produk", Toast.LENGTH_SHORT).show();
                        //finish();
                    }
                });
            }
        });

        btn_delete = findViewById(R.id.detailProduk_btnDelete);
        builder = new AlertDialog.Builder(DetailProduk.this);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.setTitle("Perhatian!!")
                        .setMessage("Yakin Menghapus Produk")
                        .setIcon(R.mipmap.ic_launcher_round)
                        .setCancelable(true)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                deleteProduk();
                                finish();
                            }
                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .show();
            }
        });
    }

    private void updateProduk(String eId, String eNameProduk, String eCategory, String ePrice, String eDeskripsi, String eGambarOld){
        APIRequestData api = RetroServer.getClient().create(APIRequestData.class);
        Log.d("berhasil", "updateProduk: " + eId);
        Log.d("berhasil", "updateProduk: " + eNameProduk);
        Log.d("berhasil", "updateProduk: " + eCategory);
        Log.d("berhasil", "updateProduk: " + ePrice);
        Log.d("berhasil", "updateProduk: " + eDeskripsi);
        Log.d("berhasil", "updateProduk: " + eGambarOld);

        String path = getRealPathFromUri(DetailProduk.this, ur);
        File file = new File(path);

        RequestBody imgAlert = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), imgAlert);
        RequestBody idPr = RequestBody.create(MediaType.parse("text/plain"), eId);
        RequestBody namePr = RequestBody.create(MediaType.parse("text/plain"), eNameProduk);
        RequestBody Cate = RequestBody.create(MediaType.parse("text/plain"), eCategory);
        RequestBody Price = RequestBody.create(MediaType.parse("text/plain"), ePrice);
        RequestBody Desk = RequestBody.create(MediaType.parse("text/plain"), eDeskripsi);
        RequestBody gambarOld = RequestBody.create(MediaType.parse("text/plain"), eGambarOld);

        Call<BarangResponse> updateProduk = api.updateBarang(body, idPr, namePr, Cate, Price, Desk, gambarOld);
        updateProduk.enqueue(new Callback<BarangResponse>() {
            @Override
            public void onResponse(Call<BarangResponse> call, Response<BarangResponse> response) {
                int kode = response.body().getKode();
                String message = response.body().getMessage();

                Toast.makeText(DetailProduk.this, "Berhasil Update Produk", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<BarangResponse> call, Throwable t) {
                Toast.makeText(DetailProduk.this, "Gagal Menghubungi Server"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteProduk(){
        APIRequestData api = RetroServer.getClient().create(APIRequestData.class);
        xId = tv5.getText().toString();
        Call<BarangResponse> hapusData = api.deleteProduk(xId);
        hapusData.enqueue(new Callback<BarangResponse>() {
            @Override
            public void onResponse(Call<BarangResponse> call, Response<BarangResponse> response) {
                int kode = response.body().getKode();
                String message = response.body().getMessage();

                Toast.makeText(DetailProduk.this, "Kode : "+kode+" | Pesan : "+message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<BarangResponse> call, Throwable t) {
                Toast.makeText(DetailProduk.this, "Gagal Mengubungi Server :"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getImage(){
        final CharSequence[] opsiImage = {"Gallery", "Camera"};
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(DetailProduk.this);
        builder.setTitle("Pilih Gambar Dari");
        builder.setItems(opsiImage, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i) {
                    case 0:
                        Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(pickPhoto, 1);
                        break;
                    case 1:
                        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(takePicture, 1);
                        break;
                }
            }
        });
        builder.create().show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            ur = data.getData();
            imageAlert.setImageURI(ur);
        }
    }

    public static String getRealPathFromUri(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if(cursor != null) {
                cursor.close();
            }
        }
    }
}