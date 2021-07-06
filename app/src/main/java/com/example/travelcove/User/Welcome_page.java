package com.example.travelcove.User;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.travelcove.Common.Credits;
import com.example.travelcove.Common.ItemView;
import com.example.travelcove.Common.ItemsModel;
import com.example.travelcove.Common.SearchActivity;
import com.example.travelcove.HelperClasses.HomeAdapter.CategoriesAdapter;
import com.example.travelcove.HelperClasses.HomeAdapter.CategoriesHelperClass;
import com.example.travelcove.HelperClasses.HomeAdapter.TouristAdapter;
import com.example.travelcove.HelperClasses.HomeAdapter.TouristHelperClass;
import com.example.travelcove.HelperClasses.HomeAdapter.YourFavouriteAdapter;
import com.example.travelcove.HelperClasses.HomeAdapter.YourFavouriteHelperClass;
import com.example.travelcove.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.Map;

import io.grpc.Context;


public class Welcome_page extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    FirebaseFirestore fStore;
    FirebaseAuth fAuth;
    String userID;
    TextView nameView;


    static final float END_SCALE = 0.7f;


    RecyclerView tourist_favourite, categoriesRecycler;
    RecyclerView your_favourite;
    RecyclerView.Adapter adapter;
    private GradientDrawable gradient1, gradient2, gradient3, gradient4;
    LinearLayout contentView;



    ImageView menuIcon;

    //Drawer layout
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        //hooks
        tourist_favourite = findViewById(R.id.tourist_recycler);
        your_favourite = findViewById(R.id.your_favourite_recycler);
        categoriesRecycler = findViewById(R.id.categories_recycler);
        contentView = findViewById(R.id.content);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userID = fAuth.getCurrentUser().getUid();
        nameView = findViewById(R.id.name);


        DocumentReference documentReference = fStore.collection("users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                nameView.setText(value.getString("Name"));
            }
        });


        navigationDrawer();

        tourist_favourite();
        //your_favourite();
        categoriesRecycler();

    }

    @Override
    protected void onStart() {
        super.onStart();
        your_favourite();

        if(fAuth.getCurrentUser() == null){
            startActivity(new Intent(getApplicationContext() , Login.class));
            finish();
        }
    }

    private void navigationDrawer() {


        //menu hooks
        drawerLayout = findViewById(R.id.drawer_layput);
        navigationView = findViewById(R.id.navigation_view);
        menuIcon = findViewById(R.id.menu_icon);

        //navigation drawer
           navigationView.bringToFront();
          navigationView.setNavigationItemSelectedListener(this);
          navigationView.setCheckedItem(R.id.nav_home);


        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(drawerLayout.isDrawerVisible(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START);
                }

                else drawerLayout.openDrawer(GravityCompat.START);

            }
        });

        animateNavigationDrawer();
    }

    private void animateNavigationDrawer() {
        drawerLayout.setScrimColor(ContextCompat.getColor(Welcome_page.this, R.color.teal_700));
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerVisible(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.nav_home:
                startActivity(new Intent(getApplicationContext(), Welcome_page.class));
        }

        switch (item.getItemId()) {
            case R.id.nav_category:
                startActivity(new Intent(getApplicationContext(), AllCategories.class));
        }

        switch (item.getItemId()) {
            case R.id.nav_instruction:
                startActivity(new Intent(getApplicationContext(), Credits.class));
        }

        switch (item.getItemId()) {
            case R.id.nav_profile:
                startActivity(new Intent(getApplicationContext(), Profile.class));
        }
        return true;
    }


    //recycle view functions
    private void tourist_favourite() {
        tourist_favourite.setHasFixedSize(true);
        tourist_favourite.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<TouristHelperClass>touristLocation = new ArrayList<>();
        touristLocation.add(new TouristHelperClass(R.drawable.saint_martin2, "Saint Martin", "St. Martin is the only coral island..."));
        touristLocation.add(new TouristHelperClass(R.drawable.keokradong, "Keokradong", "Keokradong is located at...."));
        touristLocation.add(new TouristHelperClass(R.drawable.sajek_valley, "Sajek Valley", "Sajek Valley is a place where...."));
        touristLocation.add(new TouristHelperClass(R.drawable.bichanakandi, "Bichanakandi", "Bichanakandi is loacated at...."));
        touristLocation.add(new TouristHelperClass(R.drawable.jaflong, "Jaflong", "Clouds like fog on the mountain...."));
        touristLocation.add(new TouristHelperClass(R.drawable.kuakata, "Kuakata", "Kuakata is a place where...."));
        touristLocation.add(new TouristHelperClass(R.drawable.satvaikhum, "Satvaikhum", "Satvaikhum is loacated at...."));
        touristLocation.add(new TouristHelperClass(R.drawable.kaptai_lake, "Kaptai Lake", "Among the Chittagong Hill Tracts Kaptai...."));
        touristLocation.add(new TouristHelperClass(R.drawable.cox_bazar, "Cox's Bazar", "Cox's Bazar is a city...."));
        touristLocation.add(new TouristHelperClass(R.drawable.andharmanik, "Andharmanik", "Andharmanik is place...."));

        adapter = new TouristAdapter(Welcome_page.this, touristLocation);
        tourist_favourite.setAdapter(adapter);
    }

    /*private void your_favourite(){

        your_favourite.setHasFixedSize(true);
        your_favourite.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<YourFavouriteHelperClass>yourFavouriteLocation = new ArrayList<>();

        DocumentReference documentReference = fStore.collection("favourites").document(userID);
        documentReference.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {


                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Welcome_page.this, "Error Loading Data", Toast.LENGTH_SHORT).show();
                        Log.i("TAG" , "Error !" + e.toString());
                    }
                });
    }*/


    public void your_favourite() {
        your_favourite.setHasFixedSize(true);
        your_favourite.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<YourFavouriteHelperClass>yourFavouriteLocation = new ArrayList<>();

        DocumentReference documentReference = fStore.collection("favourites").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable  DocumentSnapshot documentSnapshot, @Nullable  FirebaseFirestoreException e) {
                if(e != null){
                    Toast.makeText(Welcome_page.this, "Error Loading Favourites", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(documentSnapshot.exists()){

                    Map<String, Object> data = documentSnapshot.getData();

                    for(Map.Entry<String, Object> entry : data.entrySet()){

                        String key = entry.getKey();
                        String value = (String) entry.getValue();
                                //Log.i("TravelCove" , "Key = " + key + "  value = " + value);

                        if(key.equals("Bichanakandi") && value.equals("true")){ yourFavouriteLocation.add(new YourFavouriteHelperClass(R.drawable.bichanakandi, "Bichanakandi", "...."));}
                        if(key.equals("Sajek Valley") && value.equals("true")){ yourFavouriteLocation.add(new YourFavouriteHelperClass(R.drawable.sajek_valley, "Sajek Valley", "...."));}
                        if(key.equals("Saint Martin") && value.equals("true")){ yourFavouriteLocation.add(new YourFavouriteHelperClass(R.drawable.saint_martin, "Saint Martin", "...."));}
                        if(key.equals("Keokradong") && value.equals("true")){   yourFavouriteLocation.add(new YourFavouriteHelperClass(R.drawable.keokradong, "Keokradong", "...."));}
                        if(key.equals("Boga Lake") && value.equals("true")) {  yourFavouriteLocation.add(new YourFavouriteHelperClass(R.drawable.boga_lake, "Boga Lake", "...."));}
                        if(key.equals("Aat Kobor") && value.equals("true"))   yourFavouriteLocation.add(new YourFavouriteHelperClass(R.drawable.aat_kobor, "Aat Kobor", "...."));
                        if(key.equals("Cox's Bazar") && value.equals("true"))   yourFavouriteLocation.add(new YourFavouriteHelperClass(R.drawable.cox_bazar, "Cox's Bazar", "...."));
                        if(key.equals("Jaflong") && value.equals("true"))   yourFavouriteLocation.add(new YourFavouriteHelperClass(R.drawable.jaflong, "Jaflong", "...."));
                        if(key.equals("Kuakata") && value.equals("true"))   yourFavouriteLocation.add(new YourFavouriteHelperClass(R.drawable.kuakata, "Kuakata", "...."));
                        if(key.equals("Satvaikhum") && value.equals("true"))   yourFavouriteLocation.add(new YourFavouriteHelperClass(R.drawable.satvaikhum, "Satvaikhum", "...."));
                        if(key.equals("Ratargul") && value.equals("true"))   yourFavouriteLocation.add(new YourFavouriteHelperClass(R.drawable.ratargul, "Ratargul", "...."));
                        if(key.equals("Varendra Museum") && value.equals("true"))   yourFavouriteLocation.add(new YourFavouriteHelperClass(R.drawable.barendra_museum, "Varendra Museum", "...."));
                        if(key.equals("Kaptai Lake") && value.equals("true"))   yourFavouriteLocation.add(new YourFavouriteHelperClass(R.drawable.kaptai_lake, "Kaptai Lake", "...."));
                        if(key.equals("Tanguar Haor") && value.equals("true"))   yourFavouriteLocation.add(new YourFavouriteHelperClass(R.drawable.tanguar_haor, "Tanguar Haor", "...."));
                        if(key.equals("Sippi Arsuang") && value.equals("true"))   yourFavouriteLocation.add(new YourFavouriteHelperClass(R.drawable.sippi_arsuang, "Sippi Arsuang", "...."));
                        if(key.equals("Shopnopuri") && value.equals("true"))   yourFavouriteLocation.add(new YourFavouriteHelperClass(R.drawable.shopnopuri, "Shopnopuri", "...."));
                        if(key.equals("Lalbag") && value.equals("true"))   yourFavouriteLocation.add(new YourFavouriteHelperClass(R.drawable.lalbag, "Lalbag", "...."));
                        if(key.equals("Himchori") && value.equals("true"))   yourFavouriteLocation.add(new YourFavouriteHelperClass(R.drawable.himchori, "Himchori", "...."));
                        if(key.equals("Floating Market") && value.equals("true"))   yourFavouriteLocation.add(new YourFavouriteHelperClass(R.drawable.floating_market, "Floating Market", "...."));
                        if(key.equals("Botanical Garden") && value.equals("true"))   yourFavouriteLocation.add(new YourFavouriteHelperClass(R.drawable.botanical, "Botanical Garden", "...."));


                        adapter = new YourFavouriteAdapter(Welcome_page.this, yourFavouriteLocation);
                        your_favourite.setAdapter(adapter);
                    }
                }

                else {
                    //Toast.makeText(Welcome_page.this, "Document does not exists", Toast.LENGTH_SHORT).show();
                    yourFavouriteLocation.add(new YourFavouriteHelperClass(R.drawable.not_added, "Not Added", "Add places"));
                    adapter = new YourFavouriteAdapter(Welcome_page.this, yourFavouriteLocation);
                    your_favourite.setAdapter(adapter);
                }

                if(yourFavouriteLocation.size() == 0){
                    yourFavouriteLocation.add(new YourFavouriteHelperClass(R.drawable.not_added, "Not Added", "Add places"));
                    adapter = new YourFavouriteAdapter(Welcome_page.this, yourFavouriteLocation);
                    your_favourite.setAdapter(adapter);
                }

            }
        });

        //adapter = new YourFavouriteAdapter(yourFavouriteLocation);
        //your_favourite.setAdapter(adapter);
    }

    private void categoriesRecycler() {

        //All Gradients
        gradient2 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffd4cbe5, 0xffd4cbe5});
        gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xff7adccf, 0xff7adccf});
        gradient3 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xfff7c59f, 0xFFf7c59f});
        gradient4 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffb8d7f5, 0xffb8d7f5});


        ArrayList<CategoriesHelperClass> categoriesHelperClasses = new ArrayList<>();
        categoriesHelperClasses.add(new CategoriesHelperClass( R.drawable.river_sea, "River and sea beaches", gradient1));
        categoriesHelperClasses.add(new CategoriesHelperClass( R.drawable.forest, "forests", gradient2));
        categoriesHelperClasses.add(new CategoriesHelperClass( R.drawable.hill_mountain, "hills and mountains", gradient3));
        categoriesHelperClasses.add(new CategoriesHelperClass( R.drawable.heritages, "cultural heritages", gradient4));


        categoriesRecycler.setHasFixedSize(true);
        adapter = new CategoriesAdapter(Welcome_page.this, categoriesHelperClasses);
        categoriesRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        categoriesRecycler.setAdapter(adapter);

    }

    public void viewCat(View view){
        startActivity(new Intent(getApplicationContext(), AllCategories.class));
    }


    public void searchIcon(View view){
        startActivity(new Intent(getApplicationContext(), SearchActivity.class));
    }

}