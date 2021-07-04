package com.example.travelcove.HelperClasses.HomeAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelcove.CategoryFinalView.HeritageFinal;
import com.example.travelcove.Common.ItemView;
import com.example.travelcove.Common.SearchActivity;
import com.example.travelcove.R;
import com.example.travelcove.User.AllCategories;
import com.example.travelcove.User.Welcome_page;

import java.util.ArrayList;

public class HeritageAdapter extends RecyclerView.Adapter<HeritageAdapter.HeritageViewHolder> {
    ArrayList<HeritageHelperClass> heritageLocation;
    Context context;


    public HeritageAdapter(Context context, ArrayList<HeritageHelperClass> heritageLocation) {
        this.heritageLocation = heritageLocation;
        this.context = context;
    }


    @NonNull
    @Override
    public HeritageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.heritage, parent, false);
        HeritageViewHolder heritageViewHolder = new HeritageViewHolder(view);

        return heritageViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull  HeritageAdapter.HeritageViewHolder holder, int position) {
        HeritageHelperClass heritageHelperClass = heritageLocation.get(position);

        holder.image.setImageResource(heritageHelperClass.getImage());
        holder.title.setText(heritageHelperClass.getTitle());
        holder.desc.setText(heritageHelperClass.getDesc());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                context.startActivity(new Intent(context, HeritageFinal.class).putExtra("heritage", heritageLocation.get(position)));

            }
        });

    }

    @Override
    public int getItemCount() {
        return heritageLocation.size();
    }

    public static class HeritageViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title, desc;
        CardView cardView;

        public HeritageViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.heritage_image);
            title = itemView.findViewById(R.id.heritage_title);
            desc = itemView.findViewById(R.id.heritage_desc);
            cardView = itemView.findViewById(R.id.heritage);



        }


    }

}
