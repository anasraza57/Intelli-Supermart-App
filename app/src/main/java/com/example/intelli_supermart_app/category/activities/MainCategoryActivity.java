package com.example.intelli_supermart_app.category.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.intelli_supermart_app.PictureModel;
import com.example.intelli_supermart_app.R;
import com.example.intelli_supermart_app.cart.activities.CartActivity;
import com.example.intelli_supermart_app.drawer.activities.AboutActivity;
import com.example.intelli_supermart_app.drawer.activities.ContactActivity;
import com.example.intelli_supermart_app.drawer.activities.HelpActivity;
import com.example.intelli_supermart_app.drawer.activities.MyAddressesActivity;
import com.example.intelli_supermart_app.drawer.activities.PolicyActivity;
import com.example.intelli_supermart_app.drawer.activities.TermsActivity;
import com.example.intelli_supermart_app.login.activities.LoginActivity;
import com.google.android.material.navigation.NavigationView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainCategoryActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, ResponseListener {
    private static final String TAG = "Hello";
    DrawerLayout drawer;
    Toolbar toolbar;
    NavigationView navigationView;

    ViewFlipper viewFlipper;

    RecyclerView recyclerView;
    CategoryRecyclerAdapter categoryRecyclerAdapter;

    ImageView cartImageView;

    String strURL = "https://intelli-supermart.herokuapp.com/mobileMainCategory";
    List<CategoryModel> categoryModelList;
    List<PictureModel> pictureModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainCategoryActivity.class));
                finish();
            }
        });

        cartImageView = findViewById(R.id.cart);
        cartImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(intent);
            }
        });

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.nav_drawer_open, R.string.nav_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        int[] flipImages = {R.drawable.slide4, R.drawable.slide6};
        viewFlipper = findViewById(R.id.viewflipper);

        for (int image : flipImages) {
            flipperImages(image);
        }

        recyclerView = findViewById(R.id.recyclerView);
        categoryModelList = new ArrayList<>();
        pictureModelList = new ArrayList<>();
        initRecyclerView();
        new getAsyncTask(MainCategoryActivity.this).execute(strURL);
    }

    private void initRecyclerView() {
        categoryRecyclerAdapter = new CategoryRecyclerAdapter(categoryModelList, pictureModelList,
                getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(categoryRecyclerAdapter);
    }

    public void flipperImages(int image) {
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);

        viewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Intent intent = null;
        switch (menuItem.getItemId()) {
            case R.id.nav_login:
                intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_my_addresses:
                intent = new Intent(this, MyAddressesActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_my_orders:
                Toast.makeText(this, "My Order", Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_my_cart:
                intent = new Intent(this, CartActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_help:
                intent = new Intent(this, HelpActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_terms:
                intent = new Intent(this, TermsActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_policy:
                intent = new Intent(this, PolicyActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_contact:
                intent = new Intent(this, ContactActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_about:
                intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                break;
        }

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            super.onBackPressed();
        }
    }

    @Override
    public void UpdateUI(String Response) {
        if (Response != null) {
            categoryModelList = CategoryModel.ParseJson(Response);
            pictureModelList = PictureModel.ParseJson(Response);
            categoryRecyclerAdapter.category = categoryModelList;
            categoryRecyclerAdapter.categoryPictures = pictureModelList;
            categoryRecyclerAdapter.notifyDataSetChanged(); // this funciton will automatically changes the recyclerview
        }
    }

    public static class getAsyncTask extends AsyncTask<String, Void, String> {
        ResponseListener c;
        String data = "";

        getAsyncTask(ResponseListener c) {
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
                URL url = new URL(strings[0]);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.connect();

                BufferedReader bf = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String line = "";
                while (line != null) {
                    line = bf.readLine();
                    if (!line.equals("null")) {
                        data = data + line;
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return data;
        }
    }
}
