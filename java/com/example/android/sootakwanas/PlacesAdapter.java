package com.example.android.sootakwanas;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import mobile.sarproj.com.layout.SwipeLayout;

public  class PlacesAdapter  extends RecyclerView.Adapter<ViewHolder> {
    public static ArrayList<Places> place;
    public Context context;


    public PlacesAdapter(ArrayList<Places> place , Context mContext) {
        this.place = place;
        this.context = mContext;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleviewplacesrow, parent, false);

        return new ViewHolder(v);
    }


    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
      final  Places  places = place.get(position);
        holder.name.setText(places.getName());
        holder.Address.setText( places.getAddress()+" - "+places.getCity()+" - "+ places.getGovernment());
        holder.swipeLay.setOnActionsListener(new SwipeLayout.SwipeActionsListener() {
            @Override
            public void onOpen(int direction, boolean isContinuous) {
                if(direction==SwipeLayout.RIGHT) {
                    holder.swipeLay.close();
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW);
                    mapIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mapIntent.setData(Uri.parse("geo:0,0?q="+places.getLat()+","+places.getLon()+'('+ places.getLabel()+")&z=17"));
                    context.startActivity(mapIntent);
                }
            }

            @Override
            public void onClose() {

            }
        });
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
