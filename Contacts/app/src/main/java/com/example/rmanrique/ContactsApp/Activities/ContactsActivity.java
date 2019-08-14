package com.example.rmanrique.ContactsApp.Activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.rmanrique.ContactsApp.Adapter.ContactAdapter;
import com.example.rmanrique.ContactsApp.Controllers.SelectController;
import com.example.rmanrique.ContactsApp.Models.Contact;
import com.example.rmanrique.ContactsApp.R;

import java.util.List;

public class ContactsActivity extends AppCompatActivity {

    //a list to store all the products
    List<Contact> contactList;
    RecyclerView recyclerView;
    FloatingActionButton floatButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
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

    public void getProducts(List<Contact>contactList){
        ContactAdapter adapter = new ContactAdapter(getApplicationContext(), contactList);
        recyclerView.setAdapter(adapter);
    }

    public void  Events(){
        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AddActivity.class);
                startActivity(intent);
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
