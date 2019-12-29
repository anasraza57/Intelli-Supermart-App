package com.example.intelli_supermart_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class LoginActivity extends AppCompatActivity {

    private EditText PhoneNo;
    private Button Next;
    private ImageView Cross;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        PhoneNo = findViewById(R.id.txt_phoneNo);
        Next = findViewById(R.id.btn_next);
        Cross = findViewById(R.id.login_back);

        String phoneNo = PhoneNo.getText().toString();
        if (phoneNo.length() == 11) {
            Next.setBackgroundColor(getColor(R.color.colorPrimaryDark));
        }

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String phoneNo = PhoneNo.getText().toString();
                if ((phoneNo.isEmpty()) || (phoneNo.length() != 11)) {
                    if (phoneNo.isEmpty())
                        PhoneNo.setError("Enter Phone Number");
                    else if (phoneNo.length() != 11)
                        PhoneNo.setError("Invalid Phone No");
                } else {
                    Next.setBackgroundColor(getColor(R.color.colorPrimaryDark));
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
                        Intent intent = new Intent(LoginActivity.this, VerificationActivity.class);
                        intent.putExtra("RandomNo", randomNo);
                        startActivity(intent);
                        //return stringBuffer.toString();
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), " Error in SMS sending" + e, Toast.LENGTH_LONG).show();
                        //System.out.println("Error SMS "+e);
                        //return "Error "+e;
                    }

                }
            }
        });

        Cross.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, VerificationActivity.class);
                startActivity(intent);
            }
        });

    }

}
