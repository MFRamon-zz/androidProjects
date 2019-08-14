package com.example.rmanrique.airlines.Interfaces;

import com.example.rmanrique.airlines.Models.Passenger;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface GetAPI {
    @GET("passengers")
    Call<List<Passenger>> loadPassenger();

    @POST("passengers")
    Call<Passenger> createPassenger(@Body Passenger contact);
}
