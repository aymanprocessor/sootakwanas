package com.example.android.sootakwanas;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import mobile.sarproj.com.layout.SwipeLayout;


public class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView name;
        public final TextView Number;
        public final TextView Address;

    public SwipeLayout swipeLay;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            name = view.findViewById(R.id.doctorName);
            Number = view.findViewById(R.id.doctorTelephone);
            Address = view.findViewById(R.id.doctorAddress);
            swipeLay = view.findViewById(R.id.swipeLay1);
        }


    }


