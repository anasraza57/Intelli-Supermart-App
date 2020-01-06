package com.example.intelli_supermart_app.login.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText PhoneNo;
    private Button Next;
    private ImageView Cross;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        PhoneNo = (EditText) findViewById(R.id.txt_phoneNo);
        Next = (Button) findViewById(R.id.btn_next);
        Cross = (ImageView) findViewById(R.id.login_back);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Next.setOnClickListener(this);
        Cross.setOnClickListener(this);
        PhoneNo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable ph) {
                if (ph.toString().length() == 11) {
                    Next.setBackgroundColor(getColor(R.color.colorAccent));
                } else if (ph.toString().length() != 11) {
                    Next.setBackgroundColor(Color.GRAY);
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_back:
                Intent intent = new Intent(LoginActivity.this, MainCategoryActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_next:
                String phoneNo = PhoneNo.getText().toString();
                if ((phoneNo.isEmpty()) || (phoneNo.length() != 11)) {
                    if (phoneNo.isEmpty())
                        PhoneNo.setError("Enter Phone Number");
                    else if (phoneNo.length() != 11)
                        PhoneNo.setError("Invalid Phone No");
                } else {
                    Random random = new Random();
                    int randomNo = random.nextInt(9999);
                    try {
                        // Construct data
                        String apiKey = "apikey=" + "io0s/wZ2kOY-SlJUGoVRCN1NwKlj2Y6w7Exr5mt0BT";

                        String message = "&message=" + "Hey User ! Your OTP code is " + randomNo;
                        String sender = "&sender=" + "Intelli Supermart";
                        String numbers = "&numbers=" + PhoneNo.getText().toString();

                        // Send data
                        HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
                        String data = apiKey + numbers + message + sender;
                        conn.setDoOutput(true);
                        conn.setRequestMethod("POST");
                        conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
                        conn.getOutputStream().write(data.getBytes("UTF-8"));
                        final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        final StringBuffer stringBuffer = new StringBuffer();
                        String line;
                        while ((line = rd.readLine()) != null) {
                            stringBuffer.append(line);
                        }
                        rd.close();
                        Toast.makeText(getApplicationContext(), "OTP send successfully.", Toast.LENGTH_LONG).show();
                        intent = new Intent(LoginActivity.this, VerificationActivity.class);
                        intent.putExtra("RandomNo", randomNo);
                        startActivity(intent);
                        //return stringBuffer.toString();
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), " Error in SMS sending" + e, Toast.LENGTH_LONG).show();
                        //System.out.println("Error SMS "+e);
                        //return "Error "+e;
                    }

                }
                break;
        }
    }

}
