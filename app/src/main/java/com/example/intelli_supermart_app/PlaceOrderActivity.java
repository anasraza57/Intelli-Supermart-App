package com.example.intelli_supermart_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class PlaceOrderActivity extends AppCompatActivity implements View.OnClickListener {

    private Button placeOrderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Place Order");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        placeOrderButton = findViewById(R.id.place);

        placeOrderButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, OrderConfirmedActivity.class);
        startActivity(intent);
    }
}
