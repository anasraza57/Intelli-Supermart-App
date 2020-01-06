package com.example.intelli_supermart_app.login.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.intelli_supermart_app.category.activities.MainCategoryActivity;
import com.example.intelli_supermart_app.R;

public class VerificationActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText Code;
    private Button Verify;
    private ImageView Cross;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        Code = (EditText) findViewById(R.id.txt_code);
        Verify = (Button) findViewById(R.id.btn_verify);
        Cross = (ImageView) findViewById(R.id.login_back);
        Cross.setOnClickListener(this);
        Verify.setOnClickListener(this);
        Code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable ph) {
                if (ph.toString().length() == 4) {
                    Verify.setBackgroundColor(getColor(R.color.colorAccent));
                } else if (ph.toString().length() != 4) {
                    Verify.setBackgroundColor(Color.GRAY);
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_back:
                Intent intent = new Intent(VerificationActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_verify:
                String code = Code.getText().toString();
                if ((code.isEmpty()) || (code.length() != 4)) {
                    if (code.isEmpty())
                        Code.setError("Enter Code");
                    else if (code.length() != 4)
                        Code.setError("Invalid Code");
                } else {
                    Verify.setBackgroundColor(getColor(R.color.colorAccent));
                    String RandomNo = getIntent().getStringExtra("RandomNo");
                    if (RandomNo.equals(Code)) {
                        intent = new Intent(VerificationActivity.this, MainCategoryActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), " Invalid code", Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }
}
