package com.example.cemilku.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cemilku.API.APIRequestData;
import com.example.cemilku.API.RetroServer;
import com.example.cemilku.Model.BarangResponse;
import com.example.cemilku.Model.PesananMasukResponse;
import com.example.cemilku.R;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahBarang extends AppCompatActivity {
    private Uri ur;
    private ImageButton btn_back;
    private ImageView gambar;
    private TextInputEditText et1, et2, et3, et4;
    private AppCompatButton btn_simpan;
    private TextView tv1;
    private String name_products, category, price, Deskripsi, image, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_barang);

        btn_back = findViewById(R.id.insertBarang_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        gambar = findViewById(R.id.product_image);
        gambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getImage();
            }
        });

        tv1 = findViewById(R.id.insertProduct_email);
        tv1.setText(SaveAccount.readDataPembeli(this).getEmail());

        et1 = findViewById(R.id.insertProduct_et_namaProduct);
        et2 = findViewById(R.id.insertProduct_et_Kategori);
        et3 = findViewById(R.id.insertProduct_et_price);
        et4 = findViewById(R.id.insertProduct_et_deskripsi);
        btn_simpan = findViewById(R.id.insertProduct_btnSimpan);
        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name_products = et1.getText().toString();
                category = et2.getText().toString();
                price = et3.getText().toString();
                Deskripsi = et4.getText().toString();
                email = tv1.getText().toString();

                if(name_products.trim().equals("")){
                    et1.setError("Name Products Harus Diisi");
                } else if(category.trim().equals("")){
                    et2.setError("Category Harus Diisi");
                } else if(price.trim().equals("")){
                    et3.setError("Harga Harus Diisi");
                } else if(Deskripsi.trim().equals("")){
                    et4.setError("Deskripsi Products Harus Diisi");
                } else {
                    insertProducts();
                }
            }
        });
    }

    private void insertProducts(){
        APIRequestData api = RetroServer.getClient().create(APIRequestData.class);
        String path = getRealPathFromUri(this, ur);
        File file = new File(path);

        RequestBody img = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part mp = MultipartBody.Part.createFormData("image", file.getName(), img);
        RequestBody np = RequestBody.create(MediaType.parse("text/plain"), name_products);
        RequestBody ct = RequestBody.create(MediaType.parse("text/plain"), category);
        RequestBody pr = RequestBody.create(MediaType.parse("text/plain"), price);
        RequestBody dk = RequestBody.create(MediaType.parse("text/plain"), Deskripsi);
        RequestBody em = RequestBody.create(MediaType.parse("text/plain"), email);

        Call<BarangResponse> simpanData = api.insertBarang(mp, np, ct, pr, dk, em);
        simpanData.enqueue(new Callback<BarangResponse>() {
            @Override
            public void onResponse(Call<BarangResponse> call, Response<BarangResponse> response) {
                int kode = response.body().getKode();
                String message = response.body().getMessage();

                Toast.makeText(TambahBarang.this, "Berhasil Menambahkan Barang", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<BarangResponse> call, Throwable t) {
                Toast.makeText(TambahBarang.this, "Gagal Menambahkan Barang | "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getImage(){
        final CharSequence[] opsiImage = {"Gallery", "Camera"};
        AlertDialog.Builder builder = new AlertDialog.Builder(TambahBarang.this);
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
        if (resultCode == RESULT_OK){
            ur = data.getData();
            gambar.setImageURI(ur);
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