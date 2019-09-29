package com.example.android.sootakwanas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class searchForDoctors extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {
    private TextView toolbarTitle;
    private Toolbar mToolbar;
    private Button mButtonDoctor;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchfordoctors);
        toolbarTitle = findViewById(R.id.toolbarTitle);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        toolbarTitle.setText("البحث عن الدكتور");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mButtonDoctor = findViewById(R.id.buttonDoctor);
        mButtonDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(searchForDoctors.this, DoctorResults.class);
                startActivity(i);

            }
        });


        Spinner coloredSpinner = findViewById(R.id.spinner1);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.government_arrays, R.layout.spinner_text_details);

        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        coloredSpinner.setAdapter(adapter);
        coloredSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }



}
