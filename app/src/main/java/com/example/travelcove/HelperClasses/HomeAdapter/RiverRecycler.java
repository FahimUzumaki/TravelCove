package com.example.travelcove.HelperClasses.HomeAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.travelcove.R;
import com.example.travelcove.User.Welcome_page;

import java.util.ArrayList;

public class RiverRecycler extends AppCompatActivity {

    RecyclerView riverRecycler;
    RecyclerView.Adapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_river_recycler);

        riverRecycler = findViewById(R.id.river_recycler);

        riverRecycler();
    }

    //recycle view functions
    private void riverRecycler() {
        riverRecycler.setHasFixedSize(true);
        riverRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        ArrayList<RiverHelperClass> riverLocation = new ArrayList<>();
        riverLocation.add(new RiverHelperClass(R.drawable.saint_martin2, "Saint Martin", "Cholo ghure asi..joss jaiga eita...."));
        riverLocation.add(new RiverHelperClass(R.drawable.bichanakandi, "Bichanakandi", "Cholo ghure asi..joss jaiga eita...."));
        riverLocation.add(new RiverHelperClass(R.drawable.jaflong, "Jaflong", "Cholo ghure asi..joss jaiga eita...."));
        riverLocation.add(new RiverHelperClass(R.drawable.kuakata, "Kuakata", "Cholo ghure asi..joss jaiga eita...."));
        riverLocation.add(new RiverHelperClass(R.drawable.satvaikhum, "Satvaikhum", "Cholo ghure asi..joss jaiga eita...."));
        riverLocation.add(new RiverHelperClass(R.drawable.kaptai_lake, "Kaptai Lake", "Cholo ghure asi..joss jaiga eita...."));
        riverLocation.add(new RiverHelperClass(R.drawable.cox_bazar, "Cox's Bazar", "Cholo ghure asi..joss jaiga eita...."));
        riverLocation.add(new RiverHelperClass(R.drawable.tanguar_haor, "Tanguar Haor", "Cholo ghure asi..joss jaiga eita...."));
        riverLocation.add(new RiverHelperClass(R.drawable.andharmanik, "Andharmanik", "Cholo ghure asi..joss jaiga eita...."));
        riverLocation.add(new RiverHelperClass(R.drawable.boga_lake, "Boga Lake", "Cholo ghure asi..joss jaiga eita...."));
        riverLocation.add(new RiverHelperClass(R.drawable.himchori, "Himchori", "Cholo ghure asi..joss jaiga eita...."));

        adapter = new RiverAdapter(RiverRecycler.this, riverLocation);
        riverRecycler.setAdapter(adapter);
    }
}