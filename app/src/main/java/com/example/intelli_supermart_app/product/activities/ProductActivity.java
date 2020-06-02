package com.example.intelli_supermart_app.product.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.intelli_supermart_app.R;
import com.example.intelli_supermart_app.cart.activities.CartActivity;
import com.example.intelli_supermart_app.category.activities.MainCategoryActivity;
import com.example.intelli_supermart_app.ui.main.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class ProductActivity extends AppCompatActivity implements View.OnClickListener {

    public ImageView cart;
    public ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String temp = bundle.getString("slug");
            Toast.makeText(this, temp, Toast.LENGTH_LONG).show();
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
}