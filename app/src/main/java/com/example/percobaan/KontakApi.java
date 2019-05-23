package com.example.percobaan;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface KontakApi {
    String BASE_URL = "http://210.210.154.65/api/";

    @GET("kontak")
    Call<List<Kontak>> getAllContact();

    @GET("kontak/{id}")
    Call<Kontak> getContact(@Path("id")int contactId);

    @POST("kontak")
    Call <Kontak>saveContact(@Body Kontak kontak);

    @FormUrlEncoded
    @POST("kontak")
    Call<Kontak>saveContact(@Field("nama")String name,
                            @Field("email")String email,
                            @Field("nohp")String phone,
                            @Field("alamat")String address);

    @PUT("kontak/{id}")
    Call<Kontak>putContact(@Path("id")int contactId,@Body Kontak kontak);

    @DELETE("kontak/{id}")
    Call<Void>deleteContact(@Path("id")int contactId);

}
