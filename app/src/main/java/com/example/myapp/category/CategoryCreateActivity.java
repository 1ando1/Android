package com.example.myapp.category;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;

import com.example.myapp.MainActivity;
import com.example.myapp.dto.category.CategoryCreateDTO;
import com.example.myapp.service.BaseActivity;
import com.example.myapp.service.ApplicationNetwork;
import com.example.myapp.service.ChangeImageActivity;
import com.example.myshop.R;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryCreateActivity extends BaseActivity {
    private static int SELECT_IMAGE_RESULT = 300;
    Uri uri = null;
    ImageView imgSelectImage;

    TextInputEditText txtCategoryName;
    TextInputEditText txtCategoryPriority;
    TextInputEditText txtCategoryDescription;

    //зберігаємо введені дані
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_create);
        imgSelectImage = findViewById(R.id.imgSelectImage);
        txtCategoryName = findViewById(R.id.txtCategoryName);
        txtCategoryPriority = findViewById(R.id.txtCategoryPriotity);
        txtCategoryDescription = findViewById(R.id.txtCategoryDescription);
    }

    //вибір фотографії
    public void onClickSelectImage(View view) {
        Intent intent = new Intent(this, ChangeImageActivity.class);
        startActivityForResult(intent, SELECT_IMAGE_RESULT);
    }

    //
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==SELECT_IMAGE_RESULT && data!=null) {
            uri = (Uri) data.getParcelableExtra("croppedUri");
            imgSelectImage.setImageURI(uri);
        }
    }

    //зберігаємо категорію на сервер
    public void onClickSave(View view) {
        CategoryCreateDTO model = new CategoryCreateDTO();
        model.setName(txtCategoryName.getText().toString());
        model.setPriority(Integer.parseInt(txtCategoryPriority.getText().toString()));
        model.setDescription(txtCategoryDescription.getText().toString());
        model.setImageBase64(uriGetBase64(uri));

        ApplicationNetwork.getInstance()
                .getCategoriesApi()
                .create(model)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Intent intent = new Intent(CategoryCreateActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });

    }

    private String uriGetBase64(Uri uri) {
        try {
            Bitmap bitmap=null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            } catch(IOException e) {
                e.printStackTrace();
            }
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            byte[] byteArr = bytes.toByteArray();
            return Base64.encodeToString(byteArr, Base64.DEFAULT);

        } catch(Exception ex) {
            return null;
        }
    }
}