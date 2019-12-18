package com.example.intelli_supermart_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    ViewFlipper viewFlipper;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    RecyclerView recyclerView;
    CategoryRecyclerAdapter categoryRecyclerAdapter;
    List<Item> items;
    int[] gridImages = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawerlayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_drawer_open, R.string.nav_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        int[] flipImages = {R.drawable.slide1, R.drawable.slide2, R.drawable.slide3};
        viewFlipper = findViewById(R.id.viewflipper);

        for (int image : flipImages) {
            flipperimages(image);
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
            Item item = new Item("This is item " + (i + 1), "This is a child item" + (i + 1));
            items.add(item);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void flipperimages(int image) {
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
        switch (menuItem.getItemId()){
            case R.id.nav_message:
                Toast.makeText(this,"Message",Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_chat:
                Toast.makeText(this,"Chat",Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_profile:
                Toast.makeText(this,"Profile",Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_send:
                Toast.makeText(this,"Send",Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_share:
                Toast.makeText(this,"Share",Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_view:
                Toast.makeText(this,"View",Toast.LENGTH_LONG).show();
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
}
