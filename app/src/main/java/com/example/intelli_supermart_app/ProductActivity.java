package com.example.intelli_supermart_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ProductActivity extends AppCompatActivity implements View.OnClickListener {

    public ImageView cart;
    public ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        cart=(ImageView)findViewById(R.id.cart);
        back=(ImageView)findViewById(R.id.back);
        cart.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cart:
                Intent intent=new Intent(this,CartActivity.class);
                startActivity(intent);
                break;
            case R.id.back:
                intent=new Intent(this,CategoryActivity.class);
                startActivity(intent);
                break;
        }
    }
}