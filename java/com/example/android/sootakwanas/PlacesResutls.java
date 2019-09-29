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

public class PlacesResutls  extends AppCompatActivity {

    private   ArrayList<Places> place;
    private TextView toolbarTitle;
    private Toolbar mToolbar;
    private RecyclerView places;
    private RecyclerView.Adapter adapter;
    private RequestQueue mQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycleviewplace);
        toolbarTitle = findViewById(R.id.toolbarTitle);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        toolbarTitle.setText("نتائج البحث");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mQueue = Volley.newRequestQueue(this);
        parseJson();
      //  place = new ArrayList<>();
        // places.add(new Places("كريم نجيب السيد", "الغربية","المحلة"));
        // places.add(new Places("ايمن محمد سعد", "الجيزة","الجيزة"));
        // places.add(new Places("اسلام عزت", "القاهرة","الدقى"));
        //  places.add(new Places("محمد علاء", "القاهرة","العبور"));


        this.places = findViewById(R.id.recycler_view_place);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        this.places.setLayoutManager(mLayoutManager);
        adapter = new PlacesAdapter(place);
        this.places.setAdapter(adapter);

    }

    private void parseJson() {
        String url = "https://api.myjson.com/bins/wqzbt";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        // this problem .. that is solution to  problem because without
                        // this the array is empty
                        place = new ArrayList<>();

                        try {
                            JSONArray doctorJsonArray = response.getJSONArray("user");

                            for (int i = 0; i < doctorJsonArray.length(); i++) {
                                JSONObject doctorDetails = doctorJsonArray.getJSONObject(i);

                                String name = doctorDetails.getString("name");
                                String city = doctorDetails.getString("city");
                                String government = doctorDetails.getString("government");

                                //   DoctorList.append(name + " ," + phone + " , " + city + " , " + government +
                                // " \n\n" );

                                place.add(new Places(name, government, city));
                            }
                            // this with parseJson Method
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                            places.setLayoutManager(mLayoutManager);
                            adapter = new PlacesAdapter(place);
                            places.setAdapter(adapter);

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

        mQueue.add(request);
    }
}
