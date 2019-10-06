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
import com.google.gson.JsonArray;

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
        this.places = findViewById(R.id.recycler_view_place);
        mQueue = Volley.newRequestQueue(this);
        parseJson();

    }

    private void parseJson() {
        String url =  "https://api.myjson.com/bins/feb7l";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        // this problem .. that is solution to  problem because without
                        // this the array is empty
                        place = new ArrayList<>();

                        try {
                            JSONArray placeJsonArray = response.getJSONArray("user");

                            for (int i = 0; i < placeJsonArray.length(); i++) {
                                JSONObject PlaceDetails = placeJsonArray.getJSONObject(i);

                                String name = PlaceDetails.getString("name");
                                String city =PlaceDetails.getString("city");
                                String government = PlaceDetails.getString("government");
                                String lat = PlaceDetails.getString("lat");
                                String lon = PlaceDetails.getString("long");
                                String label = PlaceDetails.getString("label");
                                String address = PlaceDetails.getString("address");

                                place.add(new Places(name, government, city , lat , lon  , label, address));
                            }
                            // this with parseJson Method
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                            places.setLayoutManager(mLayoutManager);
                            adapter = new PlacesAdapter(place, getApplicationContext());
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
