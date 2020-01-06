package com.example.intelli_supermart_app.drawer.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.intelli_supermart_app.R;

import java.util.Objects;

public class ContactActivity extends AppCompatActivity {

    TextView addressTextView, phoneTextView, emailTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.contact_us);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addressTextView = findViewById(R.id.address);
        addressTextView.setText(R.string.address);

        phoneTextView = findViewById(R.id.phone);
        phoneTextView.setText(R.string.phone);

        emailTextView = findViewById(R.id.email);
        emailTextView.setText(R.string.email);
    }
}
