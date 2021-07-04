package com.example.travelcove.HelperClasses.HomeAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.WindowManager;

import com.example.travelcove.Common.Credits;
import com.example.travelcove.R;

import java.util.ArrayList;

public class ForestRecycler extends AppCompatActivity {

    RecyclerView forestRecycler;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forest_recycler);

        forestRecycler = findViewById(R.id.forest_recycler);

        forestRecycler();
    }

    //recycle view functions
    private void forestRecycler() {
        forestRecycler.setHasFixedSize(true);
        forestRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        ArrayList<ForestHelperClass> forestLocation = new ArrayList<>();
        forestLocation.add(new ForestHelperClass(R.drawable.sundarban, "Sundarban", "Cholo ghure asi..joss jaiga eita...."));
        forestLocation.add(new ForestHelperClass(R.drawable.ratargul, "Ratargul", "Cholo ghure asi..joss jaiga eita...."));
        forestLocation.add(new ForestHelperClass(R.drawable.boga_lake, "Boga Lake", "Cholo ghure asi..joss jaiga eita...."));
        forestLocation.add(new ForestHelperClass(R.drawable.green_valley_park, "Green Valley Park", "Cholo ghure asi..joss jaiga eita...."));
        forestLocation.add(new ForestHelperClass(R.drawable.botanical, "Botanical Garden", "Cholo ghure asi..joss jaiga eita...."));
        forestLocation.add(new ForestHelperClass(R.drawable.nilachol, "Nilachol", "Cholo ghure asi..joss jaiga eita...."));
        forestLocation.add(new ForestHelperClass(R.drawable.floating_market, "Floating Market", "Cholo ghure asi..joss jaiga eita...."));
        forestLocation.add(new ForestHelperClass(R.drawable.satvaikhum, "Satvaikhum", "Cholo ghure asi..joss jaiga eita...."));
        forestLocation.add(new ForestHelperClass(R.drawable.cha_bagan, "Cha Bagan", "Cholo ghure asi..joss jaiga eita...."));
        forestLocation.add(new ForestHelperClass(R.drawable.ramna_park, "Ramna Park", "Cholo ghure asi..joss jaiga eita...."));


        adapter = new ForestAdapter(ForestRecycler.this, forestLocation);
        forestRecycler.setAdapter(adapter);
    }
}