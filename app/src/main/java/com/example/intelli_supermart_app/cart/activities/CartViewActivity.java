package com.example.intelli_supermart_app.cart.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.intelli_supermart_app.R;
import com.example.intelli_supermart_app.product.activities.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CartViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CartViewRecyclerAdapter adapter;
    private List<Product> listProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_view);

        Objects.requireNonNull(getSupportActionBar()).setTitle("My Cart");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recycler_cart);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listProducts = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            listProducts.add(new Product("Product " + (i + 1), "Rs 35", "200ml", R.drawable.pineapple_200ml));
        }

        adapter = new CartViewRecyclerAdapter(listProducts, this);
        recyclerView.setAdapter(adapter);
    }
}
