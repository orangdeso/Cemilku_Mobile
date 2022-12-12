package com.example.cemilku.API;

import com.example.cemilku.Model.BarangKaluarResponse;
import com.example.cemilku.Model.BarangResponse;
import com.example.cemilku.Model.FpesananMasukResponse;
import com.example.cemilku.Model.JumlahBarangResponse;
import com.example.cemilku.Model.LaporanResponse;
import com.example.cemilku.Model.PendapatanResponse;
import com.example.cemilku.Model.PesananMasukResponse;
import com.example.cemilku.Model.ResponseModel;
import com.example.cemilku.Model.TransaksiResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface APIRequestData {

    @FormUrlEncoded
    @POST("retrieve.php")
    Call<ResponseModel> loginResponse(
            @Field("email") String email,
            @Field("password") String pass
    );


    @GET("jumlah_barang.php")
    Call<JumlahBarangResponse> jumlah_produk(
    );

    @GET("pesanan_masuk.php")
    Call<PesananMasukResponse> pesanan_masuk(
    );

    @GET("pendapatan.php")
    Call<PendapatanResponse> pendapatan(
    );

    @GET("barang_keluar.php")
    Call<BarangKaluarResponse> barang_keluar(
    );

    @GET("laporan.php")
    Call<LaporanResponse> tampil_laporan(
    );

    @FormUrlEncoded
    @POST("update_akun.php")
    Call<ResponseModel> setUbahAkun(
            @Field("email") String email,
            @Field("nama_user") String user,
            @Field("telpon") String telp,
            @Field("alamat") String almt
    );

    @GET("tampil_barang.php")
    Call<BarangResponse> tampil_barang(
    );

    @Multipart
    @POST("insert_barang.php")
    Call<BarangResponse> insertBarang(
            @Part MultipartBody.Part image,
            @Part("name_products") RequestBody name_products,
            @Part("category") RequestBody category,
            @Part("price") RequestBody price,
            @Part("Deskripsi") RequestBody Deskripsi,
            @Part("email") RequestBody email
    );

    @FormUrlEncoded
    @POST("getProduk.php")
    Call<BarangResponse> getProduk(
            @Field("id_products") String id_products
    );

    @FormUrlEncoded
    @POST("delete_barang.php")
    Call<BarangResponse> deleteProduk(
            @Field("id_products") String id_products
    );

    @Multipart
    @POST("update_barang.php")
    Call<BarangResponse> updateBarang(
            @Part MultipartBody.Part image,
            @Part ("image") RequestBody gambarOld,
            @Part("id_products") RequestBody id_products,
            @Part("name_products") RequestBody name_products,
            @Part("category") RequestBody category,
            @Part("price") RequestBody price,
            @Part("Deskripsi") RequestBody Deskripsi
    );

    @FormUrlEncoded
    @POST("tampil_pesanan_masuk.php")
    Call<FpesananMasukResponse> rincianPesanan(
            @Field("id_order") String id_order
    );

    @GET("tampil_transaksi.php")
    Call<TransaksiResponse> tampilTransaksi(
    );

    @FormUrlEncoded
    @POST("update_proses.php")
    Call<TransaksiResponse> updateProses(
            @Field("id_orders") String id_orders
    );
}
