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

import com.example.travelcove.CategoryFinalView.RiverFinal;
import com.example.travelcove.Common.ItemView;
import com.example.travelcove.Common.SearchActivity;
import com.example.travelcove.R;
import com.example.travelcove.User.AllCategories;
import com.example.travelcove.User.Welcome_page;

import java.util.ArrayList;

public class RiverAdapter extends RecyclerView.Adapter<RiverAdapter.RiverViewHolder> {
    ArrayList<RiverHelperClass> riverLocation;
    Context context;


    public RiverAdapter(Context context, ArrayList<RiverHelperClass> riverLocation) {
        this.riverLocation = riverLocation;
        this.context = context;
    }


    @NonNull
    @Override
    public RiverViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.river, parent, false);
        RiverViewHolder riverViewHolder = new RiverViewHolder(view);

        return riverViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull  RiverAdapter.RiverViewHolder holder, int position) {
        RiverHelperClass riverHelperClass = riverLocation.get(position);

        holder.image.setImageResource(riverHelperClass.getImage());
        holder.title.setText(riverHelperClass.getTitle());
        holder.desc.setText(riverHelperClass.getDesc());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                context.startActivity(new Intent(context, RiverFinal.class).putExtra("sea", riverLocation.get(position)));

            }
        });

    }

    @Override
    public int getItemCount() {
        return riverLocation.size();
    }

    public static class RiverViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title, desc;
        CardView cardView;

        public RiverViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.river_image);
            title = itemView.findViewById(R.id.river_title);
            desc = itemView.findViewById(R.id.river_desc);
            cardView = itemView.findViewById(R.id.river);



        }


    }

}
