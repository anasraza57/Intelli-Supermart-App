package com.example.intelli_supermart_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.intelli_supermart_app.cart.activities.CartActivity;

import java.util.Objects;

public class AddressAndTimeActivity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout paymentButton;
    private TextView viewItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_and_time);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Address and Date & Time");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        paymentButton = findViewById(R.id.layout_payment);
        paymentButton.setOnClickListener(this);

        viewItem = findViewById(R.id.view_item);
        viewItem.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.layout_payment:
                intent = new Intent(this, PlaceOrderActivity.class);
                startActivity(intent);
                break;
            case R.id.view_item:
                intent = new Intent(this, CartActivity.class);
                startActivity(intent);
                break;
        }
    }
}
