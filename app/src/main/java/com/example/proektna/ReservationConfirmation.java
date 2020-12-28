package com.example.proektna;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ReservationConfirmation extends AppCompatActivity {
    public String parking_name;
    public Button toolbarButton;
    public Button newReservationButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_confirmation);

        toolbarButton = (Button) findViewById(R.id.toolbar_button);
        toolbarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MyReservations.class);
                startActivity(intent);
            }
        }) ;

        newReservationButton = (Button) findViewById(R.id.new_reservation);
        newReservationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Cities.class);
                startActivity(intent);
            }
        }) ;

        Intent intent = getIntent();
        parking_name = intent.getStringExtra("parking_name");
    }

    public void toMapsActivity(View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("parking_name", parking_name);
        startActivity(intent);
    }
}
