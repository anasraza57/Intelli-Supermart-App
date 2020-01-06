package com.example.intelli_supermart_app.drawer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.intelli_supermart_app.Customer;
import com.example.intelli_supermart_app.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class AddAddressActivity extends AppCompatActivity implements View.OnClickListener,
        RadioGroup.OnCheckedChangeListener {

    private static final String TAG = "AddAddressActivity";

    Customer customer;
    String[] address;
    Intent intent;
    String title, name, email, home, area, city, addressNickname;

    RadioGroup nicknameGroup, titleGroup;
    RadioButton homeRadioButton, officeRadioButton, otherRadioButton;
    Button continueButton;
    TextInputLayout nameText, emailText, homeText, areaText, cityText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);

        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.add_address);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        homeRadioButton = findViewById(R.id.homeRadio);
        officeRadioButton = findViewById(R.id.officeRadio);
        otherRadioButton = findViewById(R.id.otherRadio);

        nameText = findViewById(R.id.nameText);
        emailText = findViewById(R.id.emailText);
        homeText = findViewById(R.id.houseText);
        areaText = findViewById(R.id.areaText);
        cityText = findViewById(R.id.cityText);

        titleGroup = findViewById(R.id.radio);
        titleGroup.clearCheck();

        nicknameGroup = findViewById(R.id.radio2);
        nicknameGroup.clearCheck();

        continueButton = findViewById(R.id.continueBtn);

        intent = getIntent();
        if (intent.hasExtra("address")) {
            address = intent.getStringArrayExtra("address");

            Log.d(TAG, "onCreate: address:" + address);
            Toast.makeText(this, address[0], Toast.LENGTH_SHORT).show();
            init();
        }

        titleGroup.setOnCheckedChangeListener(this);
        titleGroup.check(R.id.mrRadio);

        nicknameGroup.setOnCheckedChangeListener(this);
        nicknameGroup.check(R.id.homeRadio);

        continueButton.setOnClickListener(this);
    }

    private void init() {

        homeText.getEditText().setText(address[0]);
        areaText.getEditText().setText(address[1]);
        cityText.getEditText().setText(address[2]);

    }

    @Override
    public void onClick(View v) {

        if (!validateName() | !validateEmail() | !validateHome() | !validateArea() | !validateCity()) {
            return;
        }
        RadioButton radioButton = findViewById(titleGroup.getCheckedRadioButtonId());
        title = radioButton.getText().toString();

        radioButton = findViewById(nicknameGroup.getCheckedRadioButtonId());
        addressNickname = radioButton.getText().toString();

        customer = new Customer(title, name, email, home, area, city, addressNickname);
        Log.d(TAG, "onClick: customer: " + customer.diplayCutomerData());
        Toast.makeText(this, customer.diplayCutomerData(), Toast.LENGTH_LONG).show();
    }

    private boolean validateName() {
        name = nameText.getEditText().getText().toString().trim();
        if (name.isEmpty()) {
            nameText.getEditText().setError("Field can't be empty");
            return false;
        } else {
            return true;
        }
    }

    private boolean validateEmail() {
        email = emailText.getEditText().getText().toString().trim();
        return true;
    }

    private boolean validateHome() {
        home = homeText.getEditText().getText().toString().trim();

        if (home.isEmpty()) {
            homeText.getEditText().setError("Field can't be empty");
            return false;
        } else {
            return true;
        }
    }

    private boolean validateArea() {
        area = areaText.getEditText().getText().toString().trim();

        if (area.isEmpty()) {
            areaText.getEditText().setError("Field can't be empty");
            return false;
        } else {
            return true;
        }
    }

    private boolean validateCity() {
        city = cityText.getEditText().getText().toString().trim();

        if (city.isEmpty()) {
            cityText.getEditText().setError("Field can't be empty");
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        switch (checkedId) {
            case R.id.homeRadio:
                homeRadioButton.setTextColor(getResources().getColor(R.color.white));
                officeRadioButton.setTextColor(getResources().getColor(R.color.black));
                otherRadioButton.setTextColor(getResources().getColor(R.color.black));
                break;
            case R.id.officeRadio:
                officeRadioButton.setTextColor(getResources().getColor(R.color.white));
                homeRadioButton.setTextColor(getResources().getColor(R.color.black));
                otherRadioButton.setTextColor(getResources().getColor(R.color.black));
                break;
            case R.id.otherRadio:
                otherRadioButton.setTextColor(getResources().getColor(R.color.white));
                homeRadioButton.setTextColor(getResources().getColor(R.color.black));
                officeRadioButton.setTextColor(getResources().getColor(R.color.black));
                break;
        }
    }
}