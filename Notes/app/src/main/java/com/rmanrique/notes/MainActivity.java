package com.rmanrique.notes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //region Interface components declaration
    private Button _btnLogin,_btnRegister,_btnSkip;
    private static final String TAG = "MainActivity";
    private ImageView _imageView;
    private TextView _descrptionTextView,_companyTextView;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeComponents();
        setListeners();
    }
    public void initializeComponents(){
        _btnLogin = findViewById(R.id.btnLogin);
        _btnRegister = findViewById(R.id.btnRegister);
        _btnSkip = findViewById(R.id.btnSkip);
        _imageView = findViewById(R.id.logoImageView);
        _descrptionTextView = findViewById(R.id.descrptionTextView);
        _companyTextView = findViewById(R.id.companyTextView);
    }
    public void setListeners(){
        _btnLogin.setOnClickListener(this);
        _btnRegister.setOnClickListener(this);
        _btnSkip.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnLogin){
            Log.d(TAG, "onClick: btnLogin just clicked");
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
        }
        if(view.getId() == R.id.btnSkip){
            Log.d(TAG, "onClick: btnSkip just clicked");
            Intent intent = new Intent(MainActivity.this,NotesActivity.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            finish();
        }
        if(view.getId() == R.id.btnRegister){
            Log.d(TAG, "onClick: btnRegister just clicked");
            Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
        }
    }
}
