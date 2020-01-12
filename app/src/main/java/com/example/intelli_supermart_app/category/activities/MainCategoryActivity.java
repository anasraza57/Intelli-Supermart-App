package com.example.intelli_supermart_app.category.activities;

import android.content.Intent;
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

import com.example.intelli_supermart_app.Item;
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

import java.util.ArrayList;
import java.util.List;

public class MainCategoryActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;
    Toolbar toolbar;
    NavigationView navigationView;

    ViewFlipper viewFlipper;

    RecyclerView recyclerView;
    CategoryRecyclerAdapter categoryRecyclerAdapter;
    List<Item> items;
    int[] gridImages = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};

    ImageView cartImageView;

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

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), ProductActivity.class);
//                startActivity(intent);
//            }
//        });
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.nav_drawer_open, R.string.nav_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        int[] flipImages = {R.drawable.slide1, R.drawable.slide2, R.drawable.slide3};
        viewFlipper = findViewById(R.id.viewflipper);

        for (int image : flipImages) {
            flipperImages(image);
        }

        recyclerView = findViewById(R.id.recyclerView);
        initData();
        initRecyclerView();

    }

    private void initRecyclerView() {
        categoryRecyclerAdapter = new CategoryRecyclerAdapter(items, gridImages, getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(categoryRecyclerAdapter);
    }

    private void initData() {
        items = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Item item = new Item("This is category " + (i + 1), "Description" + (i + 1));
            items.add(item);
        }
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
}
