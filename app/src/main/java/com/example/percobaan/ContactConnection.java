package com.example.percobaan;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContactConnection {

    private static Retrofit instance;

    public static synchronized Retrofit getInstance(){
        if(instance == null){
            instance = new Retrofit.Builder()
                    .baseUrl(KontakApi.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return instance;
    }
}
