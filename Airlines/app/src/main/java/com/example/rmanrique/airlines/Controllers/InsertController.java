package com.example.rmanrique.airlines.Controllers;

import android.widget.Toast;

import com.example.rmanrique.airlines.Activities.AddPassengerActivity;
import com.example.rmanrique.airlines.Interfaces.GetAPI;
import com.example.rmanrique.airlines.Models.Passenger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InsertController implements Callback<Passenger> {
    static final String BASE_URL = "http://192.168.15.9:2403/";
    private AddPassengerActivity addPassengerActivityObject;

    public void start(AddPassengerActivity addPassengerActivityObj,Passenger passenger){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        GetAPI getAPI = retrofit.create(GetAPI.class);
        Call<Passenger> call = getAPI.createPassenger(passenger);
        call.enqueue(this);

        addPassengerActivityObject = addPassengerActivityObj; // Objeto de la clase donde se agrega
    }
    @Override
    public void onResponse(Call<Passenger> call, Response<Passenger> response) {
        if(response.isSuccessful()){
            Passenger passenger = response.body();
            Toast.makeText(addPassengerActivityObject.getApplicationContext(),"Contact "+ passenger.Name.toString() + " inserted", Toast.LENGTH_LONG).show();

        } else {
            System.out.println(response.errorBody());
        }
    }
    @Override
    public void onFailure(Call<Passenger> call, Throwable t) {
        t.printStackTrace();
    }
}
