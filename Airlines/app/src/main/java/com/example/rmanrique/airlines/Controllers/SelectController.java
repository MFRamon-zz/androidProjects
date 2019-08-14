package com.example.rmanrique.airlines.Controllers;

import com.example.rmanrique.airlines.Activities.FlightsScheduleActivity;
import com.example.rmanrique.airlines.Interfaces.GetAPI;
import com.example.rmanrique.airlines.Models.Passenger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SelectController implements Callback<List<Passenger>> {
    static final String BASE_URL = "http://192.168.15.9:2403/";
    private FlightsScheduleActivity flightsScheduleActivity;

    public void start(FlightsScheduleActivity flightsScheduleActivityObj){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        GetAPI getAPI = retrofit.create(GetAPI.class);

        Call<List<Passenger>> call = getAPI.loadPassenger();
        call.enqueue(this);

        flightsScheduleActivity = flightsScheduleActivityObj;

    }
    @Override
    public void onResponse(Call<List<Passenger>> call, Response<List<Passenger>> response){
        if(response.isSuccessful()){
            List<Passenger>passengerList = response.body();
            try{
                flightsScheduleActivity.getPassengers(passengerList);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            System.out.println(response.errorBody());
        }

    }
    @Override
    public void onFailure(Call<List<Passenger>> call, Throwable t) {t.printStackTrace();}
}
