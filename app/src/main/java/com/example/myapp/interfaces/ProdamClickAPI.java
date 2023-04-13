package com.example.myapp.interfaces;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProdamClickAPI {
    @GET("/api/Categories/list")
    public Call<List<Post>> getCategories();

    /*@POST("/posts")
    public Call<Post> postData(@Body Post data);*/
}
