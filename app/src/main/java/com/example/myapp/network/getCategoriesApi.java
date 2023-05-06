package com.example.myapp.network;

import com.example.myapp.dto.category.CategoryCreateDTO;
import com.example.myapp.dto.category.CategoryItemDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface getCategoriesApi {
    @GET("/api/categories/list")
    public Call<List<CategoryItemDTO>> list();
    @GET("/api/categories/{id}")
    public Call<CategoryItemDTO> get(@Path("id") int id);
    @DELETE("/api/categories/{id}")
    public Call<Void> delete(@Path("id") int id);
    //постимо задану нами категорію на сервер
    @POST("/api/categories/create")
    public Call<Void> create(@Body CategoryCreateDTO model);
}
