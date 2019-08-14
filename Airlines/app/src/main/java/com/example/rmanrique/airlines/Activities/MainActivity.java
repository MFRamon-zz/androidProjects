package com.example.rmanrique.airlines.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rmanrique.airlines.R;

import maes.tech.intentanim.CustomIntent;

public class MainActivity extends AppCompatActivity {

    ImageView _imgViewExitLogo,_imgViewLogoApp;
    Button _btnLogIn,_btnLoginFirebase,_btnCreateAccountFirebase;
    TextView _txtViewLoginTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeComponents();
        _btnCreateAccountFirebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AddUserActivity.class);
                startActivity(intent);
                CustomIntent.customType(MainActivity.this,"left-to-right");
            }
        });
        _btnLoginFirebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LoginFirebaseActivity.class);
                startActivity(intent);
                CustomIntent.customType(MainActivity.this,"left-to-right");
            }
        });
        _imgViewExitLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });

    }
    void initializeComponents(){
        _imgViewExitLogo = findViewById(R.id.imgViewExitLogo);
        _imgViewLogoApp = findViewById(R.id.imgViewLogoApp);
//        _btnLogIn = findViewById(R.id.btnLogIn);
        _btnLoginFirebase = findViewById(R.id.btnLoginFirebase);
        _btnCreateAccountFirebase = findViewById(R.id.btnCreateAccountFirebase);
        _txtViewLoginTitle = findViewById(R.id.txtViewLoginTitle);
    }
}
