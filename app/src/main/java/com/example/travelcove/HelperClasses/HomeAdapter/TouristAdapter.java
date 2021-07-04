package com.example.travelcove.HelperClasses.HomeAdapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelcove.Common.ItemView;
import com.example.travelcove.Common.SearchActivity;
import com.example.travelcove.R;
import com.example.travelcove.User.Welcome_page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class TouristAdapter extends RecyclerView.Adapter<TouristAdapter.TouristViewHolder> {
    ArrayList<TouristHelperClass> touristLocation;
    Context context;

  //  public static Welcome_page welcome_page;

    Map<String, Integer> bilaiMap = new HashMap<>();

    public TouristAdapter(Context context, ArrayList<TouristHelperClass> touristLocation) {
        this.touristLocation = touristLocation;
        this.context = context;
    }


    @NonNull
    @Override
    public TouristViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tourists_favourite, parent, false);
        TouristViewHolder touristViewHolder = new TouristViewHolder(view);

        return touristViewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull  TouristAdapter.TouristViewHolder holder, int position) {
        TouristHelperClass touristHelperClass = touristLocation.get(position);

        holder.image.setImageResource(touristHelperClass.getImage());
        holder.title.setText(touristHelperClass.getTitle());
        holder.desc.setText(touristHelperClass.getDesc());


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent intent = new Intent(context, TouristView.class);
                intent.putExtra("tourist", touristHelperClass);


                context.startActivity(intent);
            }
        });


    }



    @Override
    public int getItemCount() {
        return touristLocation.size();
    }

    public static class TouristViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title, desc;
        CardView cardView;

        public TouristViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.saint);
            title = itemView.findViewById(R.id.saint_text);
            desc = itemView.findViewById(R.id.saint_desc);
            cardView = itemView.findViewById(R.id.card_view);



        }


    }

}
