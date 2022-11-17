package com.example.cemilku.API;

import com.example.cemilku.Model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIRequestData {
    @FormUrlEncoded
    @POST("retrieve.php")
    Call<ResponseModel> loginResponse(
            @Field("email") String email,
            @Field("password") String pass
    );
//    @FormUrlEncoded
//    @POST("register.php")
//    Call<Register> registerResponse(
//            @Field("nama") String nama,
//            @Field("email") String email,
//            @Field("pass") String pass
//    );

}
