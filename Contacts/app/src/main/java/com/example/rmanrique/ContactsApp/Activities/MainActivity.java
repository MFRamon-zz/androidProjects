package com.example.rmanrique.ContactsApp.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rmanrique.ContactsApp.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import in.shadowfax.proswipebutton.ProSwipeButton;

public class MainActivity extends AppCompatActivity {

    TextView _Title;
    ImageView _Logo;
    TextInputLayout _Username,_Password;
    TextInputEditText _UserEdit,_PassEdit;
    ProSwipeButton  _Login;

    private Matcher matcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _Title = findViewById(R.id.LoginTitle);
        _Logo = findViewById(R.id.LoginLogo);
        _Username = findViewById(R.id.LastNameAddLayout);
        _Password = findViewById(R.id.PasswordLogin);

        _UserEdit = findViewById(R.id.UserData);
        _PassEdit = findViewById(R.id.PasswordData);
        _Login = findViewById(R.id.ButtonLogin);


        try{
            _Login.setOnSwipeListener(new ProSwipeButton.OnSwipeListener() {
                @Override
                public void onSwipeConfirm(){
                    if((validateEmail() && validatePassword())){
                        _Username.setErrorEnabled(false);
                        _Password.setErrorEnabled(false);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                _Login.showResultIcon(true);
                            }
                        }, 2000);
                        Intent intent = new Intent(getApplicationContext(),ContactsActivity.class);
                        startActivity(intent);
                    }
                    else{
                        AlertDialog.Builder builder  =  new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("The credentials are incorrect")
                                .setTitle("Error")
                                .setPositiveButton("Accept",null);
                        builder.create().show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                _Login.showResultIcon(false);
                            }
                        }, 2000);
                    }
                }
            });
        }catch(Exception ex){
            Log.e("Error:",ex.getMessage());
        }


        _UserEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0){
                    validateEmail();
                }
                else
                    _Username.setError(null);
            }
        });

        _PassEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0){
                    validatePassword();
                }
                else
                    _Password.setError(null);
            }
        });

    }
    public boolean validateEmail() {
        String username = _Username.getEditText().getText().toString().trim();
        Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$");
        if(username.isEmpty())
        {
            _Username.setError("Field can't be empty");
            return false;
        }else if(!EMAIL_PATTERN.matcher(username).matches()){
            _Username.setError("Invalid e-mail");
            return false;
        }else{
            _Username.setError(null);
            return true;
        }
    }
    public boolean validatePassword() {
        String password = _Password.getEditText().getText().toString().trim();
        Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{4,8}$");
        if(password.isEmpty())
        {
            _Password.setError("Field can't be empty");
            return false;
        }else if(!PASSWORD_PATTERN.matcher(password).matches()){
            _Password.setError("Invalid password");
            return false;
        }else{
            _Password.setError(null);
            return true;
        }
    }


}
