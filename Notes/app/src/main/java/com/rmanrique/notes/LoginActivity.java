package com.rmanrique.notes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener  {
    //region Interface components declaration
    private TextView _txtViewLoginTitle;
    private EditText _editTextUsername,_editTextPassword;
    private TextInputLayout _txtLayoutUsername,_txtLayoutPassword;
    private Button _btnLogin;
    private static final String TAG = "LoginActivity";
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeComponents();
        setListeners();
    }
    public void initializeComponents(){
        _txtViewLoginTitle = findViewById(R.id.txtViewLoginTitle);
        _editTextUsername = findViewById(R.id.editTextUsername);
        _editTextPassword = findViewById(R.id.editTextPassword);
        _txtLayoutPassword = findViewById(R.id.txtLayoutPasswordRegister);
        _txtLayoutUsername = findViewById(R.id.txtLayoutUsernameRegister);
        _btnLogin = findViewById(R.id.btnLogin);
    }
    public void setListeners(){
        _btnLogin.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnLogin){
            Log.d(TAG, "onClick: btnLogin just clicked");
            Intent intent = new Intent(LoginActivity.this,NotesActivity.class);
            startActivity(intent);
        }
    }

}
