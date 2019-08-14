package com.example.rmanrique.ContactsApp.Interfaces;

import com.example.rmanrique.ContactsApp.Models.Contact;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface GetAPI {
    @GET("contacts")
    Call<List<Contact>> loadContact();

    @POST("contacts")
    Call<Contact> createContact(@Body Contact contact);
}
