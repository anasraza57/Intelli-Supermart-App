package com.example.intelli_supermart_app.product.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.intelli_supermart_app.R;
import com.example.intelli_supermart_app.cart.activities.CartActivity;

public class ProductDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    public ImageView cart;
    public ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        cart=findViewById(R.id.product_cart);
        back=findViewById(R.id.product_back);
        cart.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.product_cart:
                Intent intent=new Intent(this, CartActivity.class);
                startActivity(intent);
                break;
            case R.id.product_back:
                intent=new Intent(this, ProductActivity.class);
                startActivity(intent);
                break;
        }
    }
}