package com.example.intelli_supermart_app;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CartActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private CartRecyclerAdapter adapter;
    private List<CartRecycler> listProducts;
    public ImageView proceed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Objects.requireNonNull(getSupportActionBar()).setTitle("My Cart");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView=findViewById(R.id.recycler_cart);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listProducts=new ArrayList<>();
        for (int i=0; i<4; i++){
            listProducts.add(new CartRecycler("Product "+(i+1),"Rs 35","200ml", R.drawable.pineapple_200ml,R.drawable.ic_cancel));
        }
        adapter=new CartRecyclerAdapter(listProducts,this);
        recyclerView.setAdapter(adapter);

        proceed=(ImageView)findViewById(R.id.btn_proceed);
        proceed.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_proceed:
                Toast.makeText(this,"Checkout", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
