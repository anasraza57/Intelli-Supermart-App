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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.intelli_supermart_app.R;
import com.example.intelli_supermart_app.category.activities.MainCategoryActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText PhoneNo;
    private Button Next;
    private ImageView Cross;
    private EditText Code;
    private Button Verify;
    private ConstraintLayout VerifyArea;

    FirebaseAuth auth;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBack;
    String verificationCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        PhoneNo = findViewById(R.id.txt_phoneNo);
        Next = findViewById(R.id.btn_next);
        Cross = findViewById(R.id.login_back);
        Code = findViewById(R.id.txt_code);
        Verify = findViewById(R.id.btn_verify);
        VerifyArea = findViewById(R.id.verify_area);
        VerifyArea.setVisibility(View.INVISIBLE);

        auth = FirebaseAuth.getInstance();

        mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                Toast.makeText(getApplicationContext(), "onVerificationCompleted", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(getApplicationContext(), "onVerificationFailed", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verificationCode = s;
                Toast.makeText(getApplicationContext(), "Code send successfully", Toast.LENGTH_SHORT).show();
            }
        };

        Next.setOnClickListener(this);
        Cross.setOnClickListener(this);
        Verify.setOnClickListener(this);
        PhoneNo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable ph) {
                if (ph.toString().length() == 14) {
                    Next.setBackgroundColor(getColor(R.color.colorAccent));
                } else if (ph.toString().length() != 14) {
                    Next.setBackgroundColor(Color.GRAY);
                }
            }
        });
        Code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable ph) {
                if (ph.toString().length() == 6) {
                    Verify.setBackgroundColor(getColor(R.color.colorAccent));
                } else if (ph.toString().length() != 6) {
                    Verify.setBackgroundColor(Color.GRAY);
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
                if (phoneNo.length() != 11) {
                    if (phoneNo.isEmpty())
                        PhoneNo.setError("Enter Phone Number");
                    else
                        PhoneNo.setError("Invalid Phone No");
                } else {
                    PhoneNo.setEnabled(false);
                    Next.setEnabled(false);
                    VerifyArea.setVisibility(View.VISIBLE);
                    PhoneAuthProvider.getInstance().verifyPhoneNumber(phoneNo, 60, TimeUnit.SECONDS, this, mCallBack);
                }
                break;
            case R.id.btn_verify:
                String inputCode = Code.getText().toString();
                if (inputCode.length() != 6) {
                    if (inputCode.isEmpty())
                        Code.setError("Enter Code");
                    else
                        Code.setError("Invalid Code");
                } else {
                    Verify.setBackgroundColor(getColor(R.color.colorAccent));
                    if (verificationCode != null) {
                        verifyPhoneNo(verificationCode, inputCode);
                    }

                }
                break;
        }
    }

    public void verifyPhoneNo(String verifyCode, String inputCode) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verifyCode, inputCode);
        signIn(credential);
    }

    public void signIn(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "signIn", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainCategoryActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid Code", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
