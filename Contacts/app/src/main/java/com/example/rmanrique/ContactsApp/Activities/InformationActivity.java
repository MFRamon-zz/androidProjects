package com.example.rmanrique.ContactsApp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.rmanrique.ContactsApp.Models.Contact;
import com.example.rmanrique.ContactsApp.R;
import com.google.gson.Gson;

public class InformationActivity extends AppCompatActivity {

    private TextView _Name,_LastName,_Age;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);


        Intent intent = getIntent();
        Gson gson = new Gson();
        String json = intent.getStringExtra("json");
        InitializeControls();
        LoadInformation(json);

    }

    private void InitializeControls(){
        _Name = findViewById(R.id.NameInformation);
        _LastName =  findViewById(R.id.LastNameAddLayout);
        _Age = findViewById(R.id.AgeInformation);
    }

    public void LoadInformation(String JSON) {
        Gson gson = new Gson();
        Contact contact = gson.fromJson(JSON, Contact.class);

        _Name.setText(contact.Name);
        _LastName.setText(contact.LastName);
        _Age.setText(String.valueOf(contact.Age));

    }
}
