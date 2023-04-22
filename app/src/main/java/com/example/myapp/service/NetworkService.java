package com.example.myapp.service;

import com.example.myapp.interfaces.ProdamClickAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
//import com.example.myapp.interfaces.JSONOlaceHolderAPI;

public class NetworkService {
    private static NetworkService mInstance;
    private static final String BASE_URL = "https://android.prodam.click/";
    private Retrofit mRetrofit;

    private NetworkService() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static NetworkService getInstance() {
        if (mInstance == null) {
            mInstance = new NetworkService();
        }
        return mInstance;
    }

    public ProdamClickAPI getJSONApi() {
        return mRetrofit.create(ProdamClickAPI.class);
    }
}
