package com.example.android.sootakwanas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class searchForPlaces extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView toolbarTitle;
    private Toolbar mToolbar;
    private Button mButtonPlaces;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchforplaces);
        toolbarTitle = findViewById(R.id.toolbarTitle);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        toolbarTitle.setText("البحث عن الأماكن");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mButtonPlaces = findViewById(R.id.buttonPlaces);
        mButtonPlaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(searchForPlaces.this, PlacesResutls.class);
                startActivity(i);

            }
        });
        Spinner coloredSpinner2 = findViewById(R.id.spinner1);
        Spinner coloredSpinner1 = findViewById(R.id.spinner2);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.place_arrays, R.layout.spinner_text_details);
        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(this,R.array.government_arrays,R.layout.spinner_text_details);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        coloredSpinner2.setAdapter(adapter);
        coloredSpinner2.setOnItemSelectedListener(this);
        adapter1.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        coloredSpinner1.setAdapter(adapter1);
        coloredSpinner1.setOnItemSelectedListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

