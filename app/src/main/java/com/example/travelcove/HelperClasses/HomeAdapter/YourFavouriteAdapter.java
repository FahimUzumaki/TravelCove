package com.example.travelcove.HelperClasses.HomeAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelcove.CategoryFinalView.HeritageFinal;
import com.example.travelcove.CategoryFinalView.YouFavouriteFinal;
import com.example.travelcove.R;

import java.util.ArrayList;

public class YourFavouriteAdapter extends RecyclerView.Adapter<YourFavouriteAdapter.YourFavouriteViewHolder> {
    ArrayList<YourFavouriteHelperClass>yourFavouriteLocation;
    Context context;

    public YourFavouriteAdapter(Context context, ArrayList<YourFavouriteHelperClass> yourFavouriteLocation) {
        this.context = context;
        this.yourFavouriteLocation = yourFavouriteLocation;
    }

    @NonNull
    @Override
    public YourFavouriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.your_favourites, parent, false);
        YourFavouriteViewHolder yourFavouriteViewHolder = new YourFavouriteViewHolder(view);


        return yourFavouriteViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull YourFavouriteViewHolder holder, int position) {
        YourFavouriteHelperClass yourFavouriteHelperClass = yourFavouriteLocation.get(position);

        holder.image2.setImageResource(yourFavouriteHelperClass.getImage2());
        holder.title2.setText(yourFavouriteHelperClass.getTitle2());
        holder.desc2.setText(yourFavouriteHelperClass.getDesc2());

       // if(position == 0) yourFavouriteLocation.remove(yourFavouriteHelperClass);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                context.startActivity(new Intent(context, YouFavouriteFinal.class).putExtra("favourite", yourFavouriteLocation.get(position)));

            }
        });
    }

    @Override
    public int getItemCount() {
        return yourFavouriteLocation.size();
    }

    public static class YourFavouriteViewHolder extends RecyclerView.ViewHolder {

        ImageView image2;
        TextView title2, desc2;
        CardView cardView;

        public YourFavouriteViewHolder(@NonNull View itemView) {
            super(itemView);

            image2 = itemView.findViewById(R.id.saint2);
            title2 = itemView.findViewById(R.id.saint_text2);
            desc2 = itemView.findViewById(R.id.saint_desc2);
            cardView = itemView.findViewById(R.id.your_favourite_recycler);
        }
    }
}
