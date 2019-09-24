package com.example.android.sootakwanas;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DoctorAdapter extends RecyclerView.Adapter<ViewHolder> {

    public static ArrayList<Doctor> doctors;


    public DoctorAdapter(ArrayList<Doctor> doctors) {
        this.doctors = doctors;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =  LayoutInflater.from(parent.getContext()).inflate(R.layout.recycledoctorrow, parent, false);
        return new ViewHolder(v);
    }



   public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Doctor doct = doctors.get(position);
        holder.name.setText(doct.getName());
        holder.Number.setText(doct.getNumber());
        holder.government.setText(doct.getGovernment());
        holder.City.setText(doct.getCity());

    }

    @Override
    public int getItemCount() {
        if (doctors != null) {
            return doctors.size();
        } else {
            return 0;
        }

    }
}