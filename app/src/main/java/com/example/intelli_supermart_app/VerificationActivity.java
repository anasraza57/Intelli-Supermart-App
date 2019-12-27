package com.example.intelli_supermart_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class VerificationActivity extends AppCompatActivity {

    private EditText Code;
    private Button Verify;
    private ImageView Cross;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        Code=(EditText)findViewById(R.id.txt_code);
        Verify=(Button)findViewById(R.id.btn_verify);
        Cross=(ImageView)findViewById(R.id.login_back);

        String code=Code.getText().toString();
        if(Code.length()==4){
            Verify.setBackgroundColor(getColor(R.color.colorAccent));
        }

        Verify.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String code=Code.getText().toString();
                if((code.isEmpty()) || (code.length()!= 4))
                {
                    if(code.isEmpty())
                        Code.setError("Enter Code");
                    else if(code.length()!=4)
                        Code.setError("Invalid Code");
                }
                else
                {
                    Verify.setBackgroundColor(getColor(R.color.colorAccent));
                    String RandomNo = getIntent().getStringExtra("RandomNo");
                    if(RandomNo.equals(Code)) {
                        Intent intent = new Intent(VerificationActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), " Invalid code", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        Cross.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent=new Intent(VerificationActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
