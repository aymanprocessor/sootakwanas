package com.example.android.sootakwanas;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import mobile.sarproj.com.layout.SwipeLayout;


public class DoctorResults extends AppCompatActivity  {
    private ArrayList<Doctor> doctors;
    private TextView toolbarTitle;
    private Toolbar mToolbar;
    private RecyclerView doctor;
    private RecyclerView.Adapter adapter;
    private RequestQueue mQueue;
    private String gover;
    private List<Doctor> filtered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recycleviewdoctor);
        toolbarTitle = findViewById(R.id.toolbarTitle);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        gover = getIntent().getStringExtra("gover");
        toolbarTitle.setText("نتائج البحث");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        doctor = findViewById(R.id.recycler_view_doctor);
        mQueue = Volley.newRequestQueue(this);
        parseJson();

    }


    private void parseJson() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://api.myjson.com/bins/feb7l";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // this problem .. that is solution to  problem because without
                        // this the array is empty

                        doctors = new ArrayList<>();
                        try {
                            JSONArray doctorJsonArray = response.getJSONArray("user");
                            for (int i = 0; i < doctorJsonArray.length(); i++) {
                                JSONObject doctorDetails = doctorJsonArray.getJSONObject(i);

                                String name = doctorDetails.getString("name");
                                Log.d("الاسم", name);
                                String phone = doctorDetails.getString("phone");
                                Log.d("التليفون", phone);
                                String city = doctorDetails.getString("city");
                                Log.d("المدينة", city);
                                String government = doctorDetails.getString("government");
                                Log.d("المحافظة", government);
                                String lat = doctorDetails.getString("lat");
                                String lon = doctorDetails.getString("long");
                                String label = doctorDetails.getString("label");
                                String address = doctorDetails.getString("address");

                                doctors.add(new Doctor(name, phone, government, city,lat,lon,label,address));
                              filtered = doctors.stream().filter(x->x.Government == "الجيزة" ).collect(Collectors.toList());

                            }

                            // this with JsonParse Method
                            final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                            doctor.setLayoutManager(layoutManager);
                            //ArrayList<Doctor> filt = new ArrayList<>(filtered);
                            adapter = new DoctorAdapter(doctors,getApplicationContext());
                            doctor.setAdapter(adapter);





                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });



        requestQueue.add(request);
        mQueue.add(request);


    }

    }

