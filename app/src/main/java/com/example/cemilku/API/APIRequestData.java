package com.example.cemilku.API;

import com.example.cemilku.Model.JumlahBarangData;
import com.example.cemilku.Model.JumlahBarangResponse;
import com.example.cemilku.Model.ProdukReadyResponse;
import com.example.cemilku.Model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIRequestData {
    @FormUrlEncoded
    @POST("retrieve.php")
    Call<ResponseModel> loginResponse(
            @Field("email") String email,
            @Field("password") String pass
    );

    @FormUrlEncoded
    @GET("jumlah_barang.php")
    Call<JumlahBarangResponse> jumlah_produk(
    );

//    @FormUrlEncoded
//    @POST("update_akun.php")
//    Call<ResponseModel> setUbahAkun(
//            @Field("nama_user") String user,
//            @Field("telpon") String telp,
//            @Field("alamat") String almt
//    );

    @FormUrlEncoded
    @POST("update_akun.php")
    Call<ResponseModel> setUbahAkun(
            @Field("email") String email,
            @Field("nama_user") String user,
            @Field("telpon") String telp,
            @Field("alamat") String almt
    );
}
