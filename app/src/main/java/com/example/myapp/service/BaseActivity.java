package com.example.myapp.service;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.MainActivity;
import com.example.myapp.account.LoginActivity;
import com.example.myapp.account.RegisterActivity;
import com.example.myapp.application.HomeApplication;
import com.example.myapp.category.CategoryCreateActivity;
import com.example.myshop.R;

public class BaseActivity extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        menu.setGroupVisible(R.id.group_anonimus, !HomeApplication.getInstance().isAuth());
        menu.setGroupVisible(R.id.group_auth, HomeApplication.getInstance().isAuth());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            //перехід на головну через меню
            case R.id.m_home:
                try {
                    intent = new Intent(BaseActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                catch (Exception ex) {
                    System.out.println("--Problem--"+ex.getMessage());
                }
                return true;
            //перехід на сторінку створення категорії через меню
            case R.id.m_create:
                try {
                    intent = new Intent(BaseActivity.this, CategoryCreateActivity.class);
                    startActivity(intent);
                    finish();
                }
                catch (Exception ex) {
                    System.out.println("--Problem--"+ex.getMessage());
                }
                return true;

            //перехід на сторінку реєстрації через меню
            case R.id.m_register:
                try {
                    intent=new Intent(BaseActivity.this, RegisterActivity.class);
                    startActivity(intent);
                    finish();
                } catch(Exception ex) {
                    System.out.println("---Problem "+ ex.getMessage());
                }
                return true;

            //перехід на сторінку логінки через меню
            case R.id.m_login:
                try {
                    intent=new Intent(BaseActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } catch(Exception ex) {
                    System.out.println("---Problem "+ ex.getMessage());
                }
                return true;

                //вихід з акаунту
            case R.id.m_logout:
                try {
                    HomeApplication.getInstance().deleteToken();
                    intent = new Intent(BaseActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
                catch(Exception ex) {
                    System.out.println("Problem "+ ex.getMessage());
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
