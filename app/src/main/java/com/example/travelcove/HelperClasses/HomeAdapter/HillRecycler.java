package com.example.travelcove.HelperClasses.HomeAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.WindowManager;

import com.example.travelcove.R;

import java.util.ArrayList;

public class HillRecycler extends AppCompatActivity {

    RecyclerView hillRecycler;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hill_recycler);

        hillRecycler = findViewById(R.id.hill_recycler);

        hillRecycler();

    }

    //recycle view functions
    private void hillRecycler() {
        hillRecycler.setHasFixedSize(true);
        hillRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        ArrayList<HillHelperClass> hillLocation = new ArrayList<>();

        hillLocation.add(new HillHelperClass(R.drawable.keokradong, "Keokradong", "Cholo ghure asi..joss jaiga eita...."));
        hillLocation.add(new HillHelperClass(R.drawable.sippi_arsuang, "Sippi Arsuang", "Cholo ghure asi..joss jaiga eita...."));
        hillLocation.add(new HillHelperClass(R.drawable.nilachol, "Nilachol", "Cholo ghure asi..joss jaiga eita...."));
        hillLocation.add(new HillHelperClass(R.drawable.sajek_valley, "Sajek Valley", "Cholo ghure asi..joss jaiga eita...."));
        hillLocation.add(new HillHelperClass(R.drawable.jaflong, "Jaflong", "Cholo ghure asi..joss jaiga eita...."));
        hillLocation.add(new HillHelperClass(R.drawable.satvaikhum, "Satvaikhum", "Cholo ghure asi..joss jaiga eita...."));
        hillLocation.add(new HillHelperClass(R.drawable.andharmanik, "Andharmanik", "Cholo ghure asi..joss jaiga eita...."));
        hillLocation.add(new HillHelperClass(R.drawable.himchori, "Himchori", "Cholo ghure asi..joss jaiga eita...."));
        hillLocation.add(new HillHelperClass(R.drawable.bichanakandi, "Bichanakandi", "Cholo ghure asi..joss jaiga eita...."));

        adapter = new HillAdapter(HillRecycler.this, hillLocation);
        hillRecycler.setAdapter(adapter);
    }
}