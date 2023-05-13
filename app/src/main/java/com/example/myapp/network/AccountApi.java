package com.example.myapp.network;

import com.example.myapp.dto.account.LoginDTO;
import com.example.myapp.dto.account.LoginResponse;
import com.example.myapp.dto.account.RegisterDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AccountApi {
    @POST("/api/account/register")
    public Call<Void> register(@Body RegisterDTO registerDTO);
    @POST("/api/account/login")
    public Call<LoginResponse> login(@Body LoginDTO loginDTO);
}
