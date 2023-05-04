package com.example.myapp.network;

import com.example.myapp.dto.category.CategoryCreateDTO;
import com.example.myapp.dto.category.CategoryItemDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CategoriesApi {
    @GET("/api/categories/list")
    public Call<List<CategoryItemDTO>> list();
    //постимо задану нами категорію на сервер
    @POST("/api/categories/create")
    public Call<Void> create(@Body CategoryCreateDTO model);
}
