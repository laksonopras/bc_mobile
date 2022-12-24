package com.example.bc_mobile.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIConfig {
    public static APIService getApiService() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();
        Retrofit retrofit = new retrofit2.Retrofit.Builder().baseUrl("http://192.168.100.232:5000/").addConverterFactory(GsonConverterFactory.create()).client(client).build();
        return retrofit.create(APIService.class);
    }
}