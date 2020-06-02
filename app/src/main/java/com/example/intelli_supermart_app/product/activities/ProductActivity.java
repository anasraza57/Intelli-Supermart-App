package com.example.intelli_supermart_app.product.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.intelli_supermart_app.R;
import com.example.intelli_supermart_app.cart.activities.CartActivity;
import com.example.intelli_supermart_app.category.activities.MainCategoryActivity;
import com.example.intelli_supermart_app.category.activities.ResponseListener;
import com.example.intelli_supermart_app.ui.main.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity implements View.OnClickListener, ResponseListener {

    public ImageView cart;
    public ImageView back;
    String strURL = "https://intelli-supermart.herokuapp.com/mobileProduct/";
    List<ProductModel> productsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        productsList = new ArrayList<>();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String slug = bundle.getString("slug");
            Toast.makeText(this, slug, Toast.LENGTH_LONG).show();
            strURL = strURL + slug;
            new getProductsAsynctask(this).execute(strURL);
        }
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        cart = findViewById(R.id.product_cart);
        back = findViewById(R.id.product_back);
        cart.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.product_cart:
                intent = new Intent(this, CartActivity.class);
                startActivity(intent);
                break;
            case R.id.product_back:
                intent = new Intent(this, MainCategoryActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void UpdateUI(String Response) {
        if(Response!=null){
            productsList = ProductModel.ParseJson(Response);
        }
    }

    public static class getProductsAsynctask extends AsyncTask<String, Void, String>{
        ResponseListener c;
        String data = "";

        public getProductsAsynctask(ResponseListener c) {
            this.c = c;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            c.UpdateUI(s);
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.connect();

                BufferedReader bf = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String line = "";
                while (line != null) {
                    line = bf.readLine();
                    if (!line.equals("null")) {
                        data = data + line;
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return data;
        }
    }
}