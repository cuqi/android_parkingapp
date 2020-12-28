package com.example.proektna;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MyReservations extends AppCompatActivity {

    RecyclerView mRecyclerView;
    myAdapter3 mAdapter;
    Button newReservation;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reservations);

        List<ReservationModel> values = new ArrayList<>();
        DBHelper dbHelper = new DBHelper(MyReservations.this);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MyReservations.this);
        int userID = prefs.getInt("user_id", 1);
        String fullName = prefs.getString("full_name", "Unknown user");

        textView = (TextView) findViewById(R.id.full_name);
        textView.setText("Ongoing parking reservations for " + fullName);

        newReservation = (Button) findViewById(R.id.new_reservation);
        newReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Cities.class);
                v.getContext().startActivity(intent);
            }
        });

        List<ReservationModel> values1 = dbHelper.getMyReservations(userID);

        mRecyclerView = (RecyclerView) findViewById(R.id.list);

        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new myAdapter3(values1, R.layout.my_reservations, this);

        mRecyclerView.setAdapter(mAdapter);
    }
}
