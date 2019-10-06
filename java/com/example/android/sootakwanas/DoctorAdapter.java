package com.example.android.sootakwanas;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import mobile.sarproj.com.layout.SwipeLayout;

public class DoctorAdapter extends RecyclerView.Adapter<ViewHolder> {

    public static ArrayList<Doctor> doctors;
    public Context context;


    public DoctorAdapter(ArrayList<Doctor> doctors, Context mContext) {
        this.doctors = doctors;
        this.context = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =  LayoutInflater.from(parent.getContext()).inflate(R.layout.recycledoctorrow, parent, false);
        return new ViewHolder(v);
    }



   public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final ArrayList<Doctor> doctlist = doctors.stream().filter(x->x.getGovernment().equals("الغربية")).collect(Collectors.toList());
final Doctor doct = doctlist.get(position);
            holder.name.setText(doct.getName());
            holder.Number.setText(doct.getNumber());
            holder.Address.setText(doct.getAddress() + " - " + doct.getCity() + " - " + doct.getGovernment());


       holder.swipeLay.setOnActionsListener(new SwipeLayout.SwipeActionsListener() {
           @Override
           public void onOpen(int direction, boolean isContinuous) {
               if(direction==SwipeLayout.LEFT){
                   holder.swipeLay.close();


                   Intent callIntent = new Intent(Intent.ACTION_CALL);
                   callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                   callIntent.setData(Uri.parse("tel:0"+doct.getNumber()));
                   if (context.checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {


                       Toast.makeText(context, " permission denied", Toast.LENGTH_SHORT).show();

                       return;
                   }
                   context.startActivity(callIntent);
               }else if(direction==SwipeLayout.RIGHT) {
                   holder.swipeLay.close();
                   Intent mapIntent = new Intent(Intent.ACTION_VIEW);
                   mapIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                   mapIntent.setData(Uri.parse("geo:0,0?q="+doct.getLat()+","+doct.getLon()+'('+ doct.getLabel()+")&z=17"));
                   context.startActivity(mapIntent);
               }

               }
           public void onClose() {

           }
       });


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