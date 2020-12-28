package com.example.proektna;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Arrays;
import java.util.List;

public class Cities extends AppCompatActivity {

    RecyclerView mRecyclerView;
    myAdapter mAdapter;
    Button toolbarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);

//        Intent intent = getIntent();
//        String username = intent.getStringExtra("username");
//        String password = intent.getStringExtra("password");
//
//        DBHelper dbHelper = new DBHelper(Cities.this);

        List<String> values = Arrays.asList("Austin", "Chicago", "Los Angeles", "Miami", "New York", "Oklahoma", "Portland", "San Francisco");

        mRecyclerView = (RecyclerView) findViewById(R.id.list);

        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new myAdapter(values, R.layout.my_row, this);

        mRecyclerView.setAdapter(mAdapter);

        toolbarButton = (Button) findViewById(R.id.toolbar_button);
        toolbarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MyReservations.class);
                startActivity(intent);
            }
        }) ;
    }
}