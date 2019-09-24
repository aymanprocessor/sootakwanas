package com.example.android.sootakwanas;
import android.os.Bundle;
import android.widget.TextView;

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
import java.util.List;


public class DoctorResults extends AppCompatActivity {
    private   ArrayList<Doctor> doctors;
    private  TextView DoctorList;
    private TextView toolbarTitle;
    private Toolbar mToolbar;
    private  RecyclerView doctor;
    private RecyclerView.Adapter adapter;
    private RequestQueue mQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recycleviewdoctor);
        toolbarTitle = findViewById(R.id.toolbarTitle);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        toolbarTitle.setText("نتائج البحث");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.doctor = findViewById(R.id.recycler_view_doctor);
        mQueue = Volley.newRequestQueue(this);
        parseJson();
        doctors = new ArrayList<>();
        //   doctors.add(new Doctor("كريم نجيب السيد", "01285804996","الغربية","المحلة"));
        //  doctors.add(new Doctor("احمد عزت", "01144114144","القاهرة","الدقى"));
        //  doctors.add(new Doctor("ايمن محمد سعد", "02235225552","الجيزة","الحوامدية"));
        //  doctors.add(new Doctor("اسلام عزت", "015284585","القاهرة","الدقى"));
        // doctors.add(new Doctor("محمد علاء", "002584555","القاهرة","العبور"));



        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        this.doctor.setLayoutManager(layoutManager);
        adapter = new DoctorAdapter(doctors);
        this.doctor.setAdapter(adapter);

    }

    private  void  parseJson(){
        String url = "https://api.myjson.com/bins/wqzbt";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray doctorJsonArray = response.getJSONArray("user");

                     for (int i = 0 ; i <doctorJsonArray.length(); i++){
                         JSONObject doctorDetails = doctorJsonArray.getJSONObject(i);

                       String name = doctorDetails.getString("name");
                       String phone = doctorDetails.getString("phone");
                       String city = doctorDetails.getString("city");
                       String government  = doctorDetails.getString("government");

//                       DoctorList.append(name + " ," + phone + " , " + city + " , " + governement +
//                             " \n\n" );

                         doctors.add(new Doctor(name, phone,government,city));

                     } } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
    }

    }







