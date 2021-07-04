package com.example.travelcove.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.travelcove.HelperClasses.HomeAdapter.ForestRecycler;
import com.example.travelcove.HelperClasses.HomeAdapter.HeritageRecycler;
import com.example.travelcove.HelperClasses.HomeAdapter.HillRecycler;
import com.example.travelcove.HelperClasses.HomeAdapter.RiverRecycler;
import com.example.travelcove.R;

public class AllCategories extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_categories);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


    }

    public void river(View view){
        startActivity(new Intent(getApplicationContext(), RiverRecycler.class));
    }

    public void hill(View view){
        startActivity(new Intent(getApplicationContext(), HillRecycler.class));
    }

    public void heritage(View view){
        startActivity(new Intent(getApplicationContext(), HeritageRecycler.class));
    }

    public void forest(View view){
        startActivity(new Intent(getApplicationContext(), ForestRecycler.class));
    }
}