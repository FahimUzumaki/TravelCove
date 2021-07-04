package com.example.travelcove.Common;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.SearchView;

import com.example.travelcove.R;

import java.util.ArrayList;
import java.util.List;



public class SearchActivity extends AppCompatActivity {
    ListView listView;


    int images[] = {R.drawable.keokradong,R.drawable.bichanakandi,R.drawable.saint_martin2,R.drawable.sajek_valley,R.drawable.kuakata, R.drawable.kaptai_lake,R.drawable.satvaikhum, R.drawable.tanguar_haor,R.drawable.sippi_arsuang,R.drawable.shopnopuri, R.drawable.ratargul,R.drawable.lalbag, R.drawable.jaflong,R.drawable.himchori, R.drawable.floating_market, R.drawable.cox_bazar,R.drawable.botanical, R.drawable.boga_lake,R.drawable.barendra_museum,R.drawable.aat_kobor};

    String names[] = {"Keokradong", "Bichanakandi", "Saint Martin", "Sajek Valley", "Kuakata",   "Kaptai Lake",  "Satvaikhum", "Tanguar Haor", "Sippi Arsuang", "Shopnopuri", "Ratargul", "Lalbag", "Jaflong", "Himchori",    "Floating Market", "Cox's Bazar", "Botanical Garden", "Boga Lake", "Varendra Museum", "Aat Kobor"};
    String desc[] = {"Bandarban",   "Sylhet",       "Cox's Bazar",   "Bandarban",  "Patuakhali",  "Rangamati" ,  "Bandarban",  "Sunamgonj",    "Bandarban",     "Rangpur",    "Sylhet",   "Dhaka",  "Sylhet",  "Cox's Bazar", "Barishal",        "Cox'x Bazar", "Dhaka",            "Bandarban", "Rajshahi", "Chuadanga"};
    String desc2[] = { "Sylhet", "Bandarban","Bandarban", "Sylhet", "Bandarban","Khulna", "Sylhet", "Bandarban", "Sylhet", "Khulna", "Sylhet", "Bandarban", "Sylhet", "Khulna", "Sylhet", "Bandarban", "Sylhet", "Khulna", "Sylhet", "Bandarban"};

    List<ItemsModel> listItems = new ArrayList<>();
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = findViewById(R.id.listview);

        for(int i = 0; i < names.length; i++){
            ItemsModel itemsModel = new ItemsModel(names[i], desc[i], images[i], desc2[i]);

            listItems.add(itemsModel);
        }


        customAdapter = new CustomAdapter(listItems, this);
        listView.setAdapter(customAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem menuItem = menu.findItem(R.id.search_view);
        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                customAdapter.getFilter().filter(newText);
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.search_view){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class CustomAdapter extends BaseAdapter implements Filterable{

        private List<ItemsModel> itemsModelList;
        private List<ItemsModel> itemsModelListFiltered;
        private Context context;

        public CustomAdapter(List<ItemsModel> itemsModelList, Context context) {
            this.itemsModelList = itemsModelList;
            this.itemsModelListFiltered = itemsModelList;
            this.context = context;
        }

        @Override
        public int getCount() {
            return itemsModelListFiltered.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.raw_items, null);

            ImageView imageView = view.findViewById(R.id.imageView);
            TextView itemName = view.findViewById(R.id.itemName);
            TextView itemDesc = view.findViewById(R.id.itemDesc);
            TextView itemDesc2 = view.findViewById(R.id.itemDesc2);

            imageView.setImageResource(itemsModelListFiltered.get(position).getImage());
            itemName.setText(itemsModelListFiltered.get(position).getName());
            itemDesc.setText(itemsModelListFiltered.get(position).getDesc());
            itemDesc2.setText(itemsModelListFiltered.get(position).getDesc2());

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(SearchActivity.this, ItemView.class).putExtra("item", itemsModelListFiltered.get(position)));
                }
            });

            return view;
        }

        @Override
        public Filter getFilter() {
            Filter filter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    FilterResults filterResults = new FilterResults();

                    if(constraint == null || constraint.length() == 0){
                        filterResults.count = itemsModelList.size();
                        filterResults.values = itemsModelList;
                    }
                    else{
                        String searchStr = constraint.toString().toLowerCase();

                        List<ItemsModel> resultData = new ArrayList<>();

                        for(ItemsModel itemsModel:itemsModelList){
                            if(itemsModel.getName().toLowerCase().contains(searchStr) || itemsModel.getDesc().toLowerCase().contains(searchStr)){
                                resultData.add(itemsModel);
                            }

                            filterResults.count = resultData.size();
                            filterResults.values = resultData;
                        }


                    }
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {

                    itemsModelListFiltered = (List<ItemsModel>) results.values;
                    notifyDataSetChanged();

                }


            };

            return filter;
        }

    }



}