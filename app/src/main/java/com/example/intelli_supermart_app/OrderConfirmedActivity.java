package com.example.intelli_supermart_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.intelli_supermart_app.category.activities.MainCategoryActivity;

import java.util.Objects;

public class OrderConfirmedActivity extends AppCompatActivity {

    Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmed);

        Objects.requireNonNull(getSupportActionBar()).setTitle("");

        continueButton = findViewById(R.id.continue_shopping);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderConfirmedActivity.this, MainCategoryActivity.class);
                startActivity(intent);
            }
        });

    }

}
