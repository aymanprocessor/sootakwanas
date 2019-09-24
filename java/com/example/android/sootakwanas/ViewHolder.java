package com.example.android.sootakwanas;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


public class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView name;
        public final TextView Number;
        public final TextView government;
        public  final  TextView City;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            name = view.findViewById(R.id.doctorName);
            Number = view.findViewById(R.id.doctorTelephone);
            government = view.findViewById(R.id.doctorGovernment);
            City = view.findViewById(R.id.doctorCity);
        }


    }


