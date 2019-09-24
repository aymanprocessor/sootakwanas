package com.example.android.sootakwanas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public  class PlacesAdapter  extends RecyclerView.Adapter<ViewHolder> {
    public static ArrayList<Places> place;

    public PlacesAdapter(ArrayList<Places> place) {
        this.place = place;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleviewplacesrow, parent, false);

        return new ViewHolder(v);
    }


    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Places  places = place.get(position);
        holder.name.setText(places.getName());
        holder.government.setText(places.getGovernment());
        holder.City.setText(places.getCity());
    }

    @Override
    public int getItemCount() {
        if (place != null) {
            return place.size();
        } else {
            return 0;
        }

    }
}
