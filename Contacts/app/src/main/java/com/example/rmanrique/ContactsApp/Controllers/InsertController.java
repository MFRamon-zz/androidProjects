package com.example.rmanrique.ContactsApp.Controllers;

import android.widget.Toast;

import com.example.rmanrique.ContactsApp.Activities.AddActivity;
import com.example.rmanrique.ContactsApp.Interfaces.GetAPI;
import com.example.rmanrique.ContactsApp.Models.Contact;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InsertController implements Callback<Contact> {

    static final String BASE_URL = "http://192.168.15.9:2403/";

    private AddActivity addActivityObject;

    public void start(AddActivity addActivityObj,Contact contact){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        GetAPI getAPI = retrofit.create(GetAPI.class);
        Call<Contact> call = getAPI.createContact(contact);
        call.enqueue(this);
        addActivityObject = addActivityObj;
    }
    @Override
    public void onResponse(Call<Contact> call, Response<Contact> response) {
        if(response.isSuccessful()){
            Contact contact = response.body();
            Toast.makeText(addActivityObject.getApplicationContext(),"Contact "+ contact.Name.toString() + " inserted", Toast.LENGTH_LONG).show();
        } else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<Contact> call, Throwable t) {
        t.printStackTrace();
    }
}
