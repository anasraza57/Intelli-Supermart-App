package com.example.intelli_supermart_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ResponseListener{

    List<StudentModel> list;
    String strURL = "";
    String data= "";
    EditText nameText, rollnumText;
    Button viewbutton, updatButton, insertButton, deleteButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();
        nameText = (EditText) findViewById(R.id.name);
        rollnumText = (EditText) findViewById(R.id.rollno);
        viewbutton = (Button) findViewById(R.id.get);
        insertButton = (Button) findViewById(R.id.post);
        updatButton = (Button) findViewById(R.id.put);
        deleteButton = (Button) findViewById(R.id.delete);


        viewbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strURL = "https://intelli-supermart.herokuapp.com/get";
                new ViewAsync(MainActivity.this).execute();
            }
        });

        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strURL = "https://intelli-supermart.herokuapp.com/add/"+ nameText.getText().toString()+"/"+rollnumText.getText().toString();
                new AddAsync(MainActivity.this).execute();
            }
        });

        updatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strURL = "https://intelli-supermart.herokuapp.com/update/"+ nameText.getText().toString()+"/"+rollnumText.getText().toString();
                new UpdateAsync(MainActivity.this).execute();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strURL = "https://intelli-supermart.herokuapp.com/delete/"+ nameText.getText().toString()+"/"+rollnumText.getText().toString();
                new DeleteAsync(MainActivity.this).execute();
            }
        });
    }

    @Override
    public void UpdateUI(String Response) {
        list = StudentModel.ParseJson(Response);
        nameText.setText(list.get(0).name);
        rollnumText.setText(list.get(0).rollnum);
    }


    public class ViewAsync extends AsyncTask<String, String, String> {
        ResponseListener c;

        public ViewAsync(ResponseListener c) {
            this.c = c;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            c.UpdateUI(s);
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(strURL);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.connect();

                BufferedReader bf = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String line = "";
                while(line != null) {
                    line = bf.readLine();
                    if(!line.equals("null")) {
                        data = data + line;
                    }
                    Log.wtf("FUCK", line);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return data;
        }
    }

    public class AddAsync extends AsyncTask<String, String, String>{
        ResponseListener c;

        public AddAsync(ResponseListener c) {
            this.c = c;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(getApplicationContext(),"Data Added", Toast.LENGTH_LONG);
            nameText.setText("");
            rollnumText.setText("");
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(strURL);
                Log.wtf("FUCK", strURL);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.connect();

                BufferedReader bf = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String line = "";
                while(line != null) {
                    line = bf.readLine();
                    if(!line.equals("null")) {
                        data = data + line;
                    }
                    Log.wtf("FUCK", line);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return data;
        }
    }

    public class UpdateAsync extends AsyncTask<String, String, String>{
        ResponseListener c;

        public UpdateAsync(ResponseListener c) {
            this.c = c;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(getApplicationContext(),"Data Updated", Toast.LENGTH_LONG);
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(strURL);
                Log.wtf("FUCK", strURL);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("PUT");
                con.connect();

                BufferedReader bf = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String line = "";
                while(line != null) {
                    line = bf.readLine();
                    if(!line.equals("null")) {
                        data = data + line;
                    }
                    Log.wtf("FUCK", line);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return data;
        }
    }

    public class DeleteAsync extends AsyncTask<String, String, String>{
        ResponseListener c;

        public DeleteAsync(ResponseListener c) {
            this.c = c;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(getApplicationContext(),"Data Deleted", Toast.LENGTH_LONG);
            nameText.setText("");
            rollnumText.setText("");
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(strURL);
                Log.wtf("FUCK", strURL);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("DELETE");
                con.connect();

                BufferedReader bf = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String line = "";
                while(line != null) {
                    line = bf.readLine();
                    if(!line.equals("null")) {
                        data = data + line;
                    }
                    Log.wtf("FUCK", line);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return data;
        }
    }
}
