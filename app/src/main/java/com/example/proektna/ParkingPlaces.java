package com.example.proektna;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParkingPlaces extends AppCompatActivity {
    RecyclerView mRecyclerView;
    myAdapter2 mAdapter;
    Button toolbarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_places);

        Intent intent = getIntent();
        String timeSlot = intent.getStringExtra("time");
        String date = intent.getStringExtra("date");

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("timeSlot", timeSlot);
        editor.putString("date", date);
        editor.commit();

        int userID = prefs.getInt("user_id", 1);
        String city_name = prefs.getString("city_name", "Austin");

        String username = prefs.getString("username", "nov" );
        String password = prefs.getString("password", "nov");
        DBHelper dbHelper = new DBHelper(ParkingPlaces.this);

        List<ParkingModel> parkingModels = dbHelper.getParkings(city_name);

        Toast.makeText(this, parkingModels.get(0).toString(), Toast.LENGTH_LONG).show();

        List<String> parkings = new ArrayList<>();
        for(int i = 0; i < parkingModels.size(); i++) {
            parkings.add(parkingModels.get(i).getParking_name());
        }

        toolbarButton = (Button) findViewById(R.id.toolbar_button);
        toolbarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MyReservations.class);
                startActivity(intent);
            }
        }) ;

        mRecyclerView = (RecyclerView) findViewById(R.id.parkings);

        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new myAdapter2(parkingModels, R.layout.my_parking, this);

        mRecyclerView.setAdapter(mAdapter);
    }
}