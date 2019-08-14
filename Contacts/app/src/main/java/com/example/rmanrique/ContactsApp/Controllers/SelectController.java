package com.example.rmanrique.ContactsApp.Controllers;

import com.example.rmanrique.ContactsApp.Activities.ContactsActivity;
import com.example.rmanrique.ContactsApp.Interfaces.GetAPI;
import com.example.rmanrique.ContactsApp.Models.Contact;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SelectController implements Callback<List<Contact>> {

    static final String BASE_URL = "http://192.168.15.9:2403/";
    private ContactsActivity contactsActivity;
    public void start(ContactsActivity contactsActivityObj){
        Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        GetAPI getAPI = retrofit.create(GetAPI.class);
        Call<List<Contact>> call = getAPI.loadContact();
        call.enqueue(this);

        contactsActivity = contactsActivityObj;

    }
    @Override
    public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response){
        if(response.isSuccessful()){
            List<Contact>contactList = response.body();
            try{
                contactsActivity.getProducts(contactList);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            System.out.println(response.errorBody());
        }
    }
    @Override
    public void onFailure(Call<List<Contact>> call, Throwable t) {t.printStackTrace();}
}
