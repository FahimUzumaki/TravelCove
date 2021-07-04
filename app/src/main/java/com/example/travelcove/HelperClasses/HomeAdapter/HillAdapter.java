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

import com.example.travelcove.CategoryFinalView.HillFinal;
import com.example.travelcove.Common.ItemView;
import com.example.travelcove.Common.SearchActivity;
import com.example.travelcove.R;
import com.example.travelcove.User.AllCategories;
import com.example.travelcove.User.Welcome_page;

import java.util.ArrayList;

public class HillAdapter extends RecyclerView.Adapter<HillAdapter.HillViewHolder> {
    ArrayList<HillHelperClass> hillLocation;
    Context context;


    public HillAdapter(Context context, ArrayList<HillHelperClass> hillLocation) {
        this.hillLocation = hillLocation;
        this.context = context;
    }


    @NonNull
    @Override
    public HillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hill, parent, false);
        HillViewHolder hillViewHolder = new HillViewHolder(view);

        return hillViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull  HillAdapter.HillViewHolder holder, int position) {
        HillHelperClass hillHelperClass = hillLocation.get(position);

        holder.image.setImageResource(hillHelperClass.getImage());
        holder.title.setText(hillHelperClass.getTitle());
        holder.desc.setText(hillHelperClass.getDesc());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                context.startActivity(new Intent(context, HillFinal.class).putExtra("hill", hillLocation.get(position)));

            }
        });

    }

    @Override
    public int getItemCount() {
        return hillLocation.size();
    }

    public static class HillViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title, desc;
        CardView cardView;

        public HillViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.hill_image);
            title = itemView.findViewById(R.id.hill_title);
            desc = itemView.findViewById(R.id.hill_desc);
            cardView = itemView.findViewById(R.id.hill);



        }


    }

}
