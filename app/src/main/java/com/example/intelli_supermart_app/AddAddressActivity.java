package com.example.intelli_supermart_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class AddAddressActivity extends AppCompatActivity implements View.OnClickListener {

    Button homeButton, officeButton, otherButton, continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);

        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.add_address);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        homeButton = findViewById(R.id.homeBtn);
        officeButton = findViewById(R.id.officeBtn);
        otherButton = findViewById(R.id.otherBtn);
        continueButton = findViewById(R.id.continueBtn);

        homeButton.setOnClickListener(this);
        officeButton.setOnClickListener(this);
        otherButton.setOnClickListener(this);
        continueButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.homeBtn:
                if (homeButton.getTag() == "ON") {
                    homeButton.setTag("OFF");
                    homeButton.setBackgroundColor(getResources().getColor(R.color.colorGrey));
                } else {
                    homeButton.setTag("ON");
                    homeButton.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                }
                break;
            case R.id.officeBtn:
                if (officeButton.getTag() == "ON") {
                    officeButton.setTag("OFF");
                    officeButton.setBackgroundColor(getResources().getColor(R.color.colorGrey));
                } else {
                    officeButton.setTag("ON");
                    officeButton.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                }
                break;
            case R.id.otherBtn:
                if (otherButton.getTag() == "ON") {
                    otherButton.setTag("OFF");
                    otherButton.setBackgroundColor(getResources().getColor(R.color.colorGrey));
                } else {
                    otherButton.setTag("ON");
                    otherButton.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                }
                break;
            case R.id.continueBtn:
                Toast.makeText(this, "Continue", Toast.LENGTH_LONG).show();
                break;
        }

    }
}
