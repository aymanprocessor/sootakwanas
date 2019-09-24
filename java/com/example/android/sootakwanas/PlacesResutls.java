package com.example.android.sootakwanas;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PlacesResutls  extends AppCompatActivity {
    private TextView toolbarTitle;
    private Toolbar mToolbar;
    private RecyclerView places;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycleviewplace);
        toolbarTitle = findViewById(R.id.toolbarTitle);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        toolbarTitle.setText("نتائج البحث");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final ArrayList<Places> places = new ArrayList<>();
       // places.add(new Places("كريم نجيب السيد", "الغربية","المحلة"));
        // places.add(new Places("ايمن محمد سعد", "الجيزة","الجيزة"));
       // places.add(new Places("اسلام عزت", "القاهرة","الدقى"));
      //  places.add(new Places("محمد علاء", "القاهرة","العبور"));


        this.places = findViewById(R.id.recycler_view_place);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        this.places.setLayoutManager(mLayoutManager);
        adapter = new PlacesAdapter(places);
        this.places.setAdapter(adapter);



    }
}

