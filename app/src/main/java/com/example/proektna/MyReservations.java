package com.example.proektna;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reservations);

        List<ReservationModel> values = new ArrayList<>();
        DBHelper dbHelper = new DBHelper(MyReservations.this);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MyReservations.this);
        int userID = prefs.getInt("user_id", 1);


        List<ReservationModel> values1 = dbHelper.getMyReservations(userID);

        mRecyclerView = (RecyclerView) findViewById(R.id.list);

        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new myAdapter3(values1, R.layout.my_reservations, this);

        mRecyclerView.setAdapter(mAdapter);
    }
}
