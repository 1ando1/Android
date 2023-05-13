package com.example.myapp.service;

import com.example.myapp.constants.Urls;
import com.example.myapp.interceptors.JWTInterceptor;
import com.example.myapp.network.AccountApi;
import com.example.myapp.network.getCategoriesApi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApplicationNetwork {
    private static ApplicationNetwork instance;
    private Retrofit retrofit;

    public ApplicationNetwork() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(new JWTInterceptor())
                .build();
        retrofit = new Retrofit.Builder()
                .client(httpClient)
                .baseUrl(Urls.BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static ApplicationNetwork getInstance() {
        if(instance==null)
            instance=new ApplicationNetwork();
        return instance;
    }

    public getCategoriesApi getCategoriesApi() {
        return retrofit.create(getCategoriesApi.class);
    }
    public AccountApi getAccountApi() {
        return retrofit.create(AccountApi.class);
    }
}