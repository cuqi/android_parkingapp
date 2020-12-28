package com.example.proektna;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ReservationForm extends AppCompatActivity {

    Button toolbarButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_form);

//        toolbarButton = (Button) findViewById(R.id.toolbar_button);
//        toolbarButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(v.getContext(), MyReservations.class);
//                startActivity(intent);
//            }
//        }) ;
    }
}