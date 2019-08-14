package com.example.rmanrique.airlines.Activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.rmanrique.airlines.Adapters.PassengerAdapter;
import com.example.rmanrique.airlines.Controllers.SelectController;
import com.example.rmanrique.airlines.Models.Passenger;
import com.example.rmanrique.airlines.R;

import java.util.List;

import maes.tech.intentanim.CustomIntent;

public class FlightsScheduleActivity extends AppCompatActivity {

    List<Passenger> contactList;
    RecyclerView recyclerView;
    FloatingActionButton floatButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flights_schedule);

        try{
            recyclerView = findViewById(R.id.recyclerView);
            floatButton = findViewById(R.id.floatingActionButton);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
            Events();
            SelectController selectController = new SelectController();
            selectController.start(this);

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public void getPassengers(List<Passenger>passengerList){ // Antes llamado GetProducts
        PassengerAdapter adapter = new PassengerAdapter(getApplicationContext(), passengerList);
        recyclerView.setAdapter(adapter);
    }
    public void  Events(){
        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AddPassengerActivity.class); // Me debe mandar a la actividad para agregar pasajeros
                startActivity(intent);
                CustomIntent.customType(FlightsScheduleActivity.this,"left-to-right");
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);

    }
}
