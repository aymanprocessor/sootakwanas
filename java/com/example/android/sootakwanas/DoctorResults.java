package com.example.android.sootakwanas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
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

import me.thanel.swipeactionview.SwipeActionView;
import me.thanel.swipeactionview.SwipeDirection;
import me.thanel.swipeactionview.SwipeGestureListener;


public class DoctorResults extends AppCompatActivity implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {
    private ArrayList<Doctor> doctors;
    private TextView toolbarTitle;
    private Toolbar mToolbar;
    private RecyclerView doctor;
    private RecyclerView.Adapter adapter;
    private RequestQueue mQueue;
    private RecyclerItemTouchHelper.RecyclerItemTouchHelperListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recycleviewdoctor);
        toolbarTitle = findViewById(R.id.toolbarTitle);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        toolbarTitle.setText("نتائج البحث");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        doctor = findViewById(R.id.recycler_view_doctor);
        mQueue = Volley.newRequestQueue(this);
        parseJson();
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper
                (0, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT , this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(doctor);

    }


    private void parseJson() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://api.myjson.com/bins/wqzbt";
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

                                // textView.append(name + " ," + phone + " , " + city + " , " + government +
                                //" \n\n" );


                                doctors.add(new Doctor(name, phone, government, city));

                            }

                            // this with JsonParse Method
                            final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                            doctor.setLayoutManager(layoutManager);
                            adapter = new DoctorAdapter(doctors);
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

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        listener.onSwiped(viewHolder, direction, viewHolder.getAdapterPosition());

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        listener.onPointerCaptureChanged(hasCapture);

    }
}
