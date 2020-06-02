package com.example.intelli_supermart_app.cart.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.intelli_supermart_app.AddressAndTimeActivity;
import com.example.intelli_supermart_app.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CartActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private CartRecyclerAdapter adapter;
    private List<Cart> listProducts;
    public RelativeLayout proceedButton;
//    int[] prodImages = {R.drawable.apple, R.drawable.guava, R.drawable.red_grapes, R.drawable.pomegranate,
//            R.drawable.peach, R.drawable.pineapple};
//    String[] prodTitles = {"Apple Juice", "Guava Juice", "Red Grapes Juice", "Pomegranate Juice",
//            "Peach Juice", "Pineapple Juice"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Objects.requireNonNull(getSupportActionBar()).setTitle("My Cart");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recycler_cart);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listProducts = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
//            listProducts.add(new Cart(prodTitles[i], "Rs 35", "200ml", prodImages[i],
//                    R.drawable.ic_cancel));
        }
        adapter = new CartRecyclerAdapter(listProducts, this);
        recyclerView.setAdapter(adapter);

        proceedButton = findViewById(R.id.layout_checkout);
        proceedButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, AddressAndTimeActivity.class);
        startActivity(intent);
    }
}
