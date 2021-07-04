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

import com.example.travelcove.CategoryFinalView.ForestFinal;
import com.example.travelcove.Common.ItemView;
import com.example.travelcove.Common.SearchActivity;
import com.example.travelcove.R;
import com.example.travelcove.User.AllCategories;
import com.example.travelcove.User.Welcome_page;

import java.util.ArrayList;

public class ForestAdapter extends RecyclerView.Adapter<ForestAdapter.ForestViewHolder> {
    ArrayList<ForestHelperClass> forestLocation;
    Context context;


    public ForestAdapter(Context context, ArrayList<ForestHelperClass> forestLocation) {
        this.forestLocation = forestLocation;
        this.context = context;
    }


    @NonNull
    @Override
    public ForestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.forest, parent, false);
        ForestViewHolder forestViewHolder = new ForestViewHolder(view);

        return forestViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull  ForestAdapter.ForestViewHolder holder, int position) {
        ForestHelperClass forestHelperClass = forestLocation.get(position);

        holder.image.setImageResource(forestHelperClass.getImage());
        holder.title.setText(forestHelperClass.getTitle());
        holder.desc.setText(forestHelperClass.getDesc());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                context.startActivity(new Intent(context, ForestFinal.class).putExtra("forest", forestLocation.get(position)));

            }
        });

    }

    @Override
    public int getItemCount() {
        return forestLocation.size();
    }

    public static class ForestViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title, desc;
        CardView cardView;

        public ForestViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.forest_image);
            title = itemView.findViewById(R.id.forest_title);
            desc = itemView.findViewById(R.id.forest_desc);
            cardView = itemView.findViewById(R.id.forest);



        }


    }

}
