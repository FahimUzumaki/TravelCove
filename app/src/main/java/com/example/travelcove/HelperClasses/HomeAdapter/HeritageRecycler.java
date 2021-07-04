package com.example.travelcove.HelperClasses.HomeAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.WindowManager;

import com.example.travelcove.R;

import java.util.ArrayList;

public class HeritageRecycler extends AppCompatActivity {

    RecyclerView heritageRecycler;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heritage_recycler);

        heritageRecycler = findViewById(R.id.heritage_recycler);

        heritageRecycler();
    }

    //recycle view functions
    private void heritageRecycler() {
        heritageRecycler.setHasFixedSize(true);
        heritageRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        ArrayList<HeritageHelperClass> heritageLocation = new ArrayList<>();
        heritageLocation.add(new HeritageHelperClass(R.drawable.lalbag, "Lalbag", "Cholo ghure asi..joss jaiga eita...."));
        heritageLocation.add(new HeritageHelperClass(R.drawable.aat_kobor, "Aat Kobor", "Cholo ghure asi..joss jaiga eita...."));
        heritageLocation.add(new HeritageHelperClass(R.drawable.barendra_museum, "Varendra Museum", "Cholo ghure asi..joss jaiga eita...."));
        heritageLocation.add(new HeritageHelperClass(R.drawable.botanical, "Botanical Garden", "Cholo ghure asi..joss jaiga eita...."));
        heritageLocation.add(new HeritageHelperClass(R.drawable.collectorate_park, "Collectorate Park", "Cholo ghure asi..joss jaiga eita...."));
        heritageLocation.add(new HeritageHelperClass(R.drawable.biggan_jadughor, "Science Museum", "Cholo ghure asi..joss jaiga eita...."));
        heritageLocation.add(new HeritageHelperClass(R.drawable.vinno_jogot, "Vinno Jogot", "Cholo ghure asi..joss jaiga eita...."));
        heritageLocation.add(new HeritageHelperClass(R.drawable.shopnopuri, "Shopnopuri", "Cholo ghure asi..joss jaiga eita...."));


        adapter = new HeritageAdapter(HeritageRecycler.this, heritageLocation);
        heritageRecycler.setAdapter(adapter);
    }
}