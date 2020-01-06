package com.example.intelli_supermart_app.drawer.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.intelli_supermart_app.R;

import java.util.Objects;

public class PolicyActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policy);

        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.privacy_policy);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textView = findViewById(R.id.textView);
        textView.setText(R.string.policy);
    }
}
