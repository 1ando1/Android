package com.example.myapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.interfaces.Post;
import com.example.myapp.services.NetworkService;
import com.example.myshop.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextView categoryName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        categoryName = findViewById(R.id.categoryName);
        NetworkService.getInstance()
                .getJSONApi()
                .getCategories()
                .enqueue(new Callback<List<Post>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Post>> call, @NonNull Response<List<Post>> response) {
                        List<Post> posts = response.body();
                        for (Post item:posts)
                        {
                            categoryName.append(item.getName() + "\n");
                            categoryName.append(item.getImage() + "\n");
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Post>> call, @NonNull Throwable t) {

                        //textView.append("Error occurred while getting request!");
                        t.printStackTrace();
                    }
                });
    }
}