package com.example.intelli_supermart_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Objects;

public class MyAddressesActivity extends AppCompatActivity implements View.OnClickListener {

    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_addresses);

        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.choose_address);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        button = findViewById(R.id.addBtn);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, AddAddressActivity.class);
        startActivity(intent);
    }
}
