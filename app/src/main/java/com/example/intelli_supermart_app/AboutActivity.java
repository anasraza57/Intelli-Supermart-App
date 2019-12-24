package com.example.intelli_supermart_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Objects;

public class AboutActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.about_us);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textView = findViewById(R.id.textView);
        textView.setText(R.string.about);
    }
}
