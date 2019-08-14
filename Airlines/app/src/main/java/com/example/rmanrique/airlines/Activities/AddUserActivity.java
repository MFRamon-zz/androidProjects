package com.example.rmanrique.airlines.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rmanrique.airlines.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

import java.util.regex.Pattern;

import maes.tech.intentanim.CustomIntent;

public class AddUserActivity extends AppCompatActivity {

     TextView _txtViewTitleCreateAccount;
     TextInputLayout _txtInputEmail,_txtInputPassword;
     FloatingActionButton _floatButtonCreateAccount;
     ProgressDialog progressDialog;
     FirebaseAuth firebaseAuth;

     //EditText
     TextInputEditText _txtEditTextEmailFirebase,_txtEditTextPasswordFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        initializeComponents();
        registerUserFirebase();

        _txtEditTextEmailFirebase.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0){
                    String email = _txtInputEmail.getEditText().getText().toString();
                    validateEmail(email);
                }
                else
                    _txtEditTextEmailFirebase.setError(null);
            }
        });
        _txtEditTextPasswordFirebase.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0){
                    String password = _txtInputPassword.getEditText().getText().toString();
                    validatePassword(password);
                }
                else
                    _txtEditTextPasswordFirebase.setError(null);
            }
        });
    }

    void initializeComponents(){
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        _txtViewTitleCreateAccount = findViewById(R.id.txtViewTitleCreateAccount);
        _txtInputEmail = findViewById(R.id.txtInputName);
        _txtInputPassword = findViewById(R.id.txtInputPasswordFirebase);
        _floatButtonCreateAccount = findViewById(R.id.floatButtonCreateAccount);
        _txtEditTextEmailFirebase = findViewById(R.id.txtEditTextEmailFirebase);
        _txtEditTextPasswordFirebase = findViewById(R.id.txtEditTextPasswordFirebase);
    }
    void registerUserFirebase(){
        _floatButtonCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = _txtInputEmail.getEditText().getText().toString();
                String password = _txtInputPassword.getEditText().getText().toString();
                if(validateEmail(email) == false || validatePassword(password) == false || (validateEmail(email) == false || validatePassword(password) == false)){
                    Toast.makeText(AddUserActivity.this,"Fill in your data",Toast.LENGTH_LONG).show();
                    _txtEditTextEmailFirebase.setText("");
                    _txtEditTextPasswordFirebase.setText("");
                }else {
                    firebaseAuth.createUserWithEmailAndPassword(email,password)
                            .addOnCompleteListener(AddUserActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        Intent intent = new Intent(getApplicationContext(),IntroductionActivity.class);
                                        startActivity(intent);
                                        CustomIntent.customType(AddUserActivity.this,"left-to-right");
                                        Toast.makeText(AddUserActivity.this,"Registered Succesfully",Toast.LENGTH_LONG).show();
                                    }else{
                                        FirebaseAuthException e = (FirebaseAuthException)task.getException();
                                        Toast.makeText(AddUserActivity.this, "Failed Registration: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                                        Log.e("LoginActivity", "Failed Registration", e);
//                                    _txtEditTextEmailFirebase.setText("");
//                                    _txtEditTextPasswordFirebase.setText("");
                                        progressDialog.hide();
                                        return;
                                    }
                                }
                            });
                }
            }
        });
    }
    public boolean validateEmail(String email) {
        email = _txtInputEmail.getEditText().getText().toString().trim();
        Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$");
        if(email.isEmpty())
        {
            _txtInputEmail.setError("Field can't be empty");
            return false;
        }else if(!EMAIL_PATTERN.matcher(email).matches()){
            _txtInputEmail.setError("Invalid e-mail");
            return false;
        }else{
            _txtInputEmail.setError(null);
            return true;
        }
    }
    public boolean validatePassword(String password) {
        password = _txtInputPassword.getEditText().getText().toString().trim();
        Pattern PASSWORD_PATTERN = Pattern.compile("^[a-zA-Z]{6,10}$");
        if(password.isEmpty())
        {
            _txtInputPassword.setError("Field can't be empty");
            return false;
        }else if(!PASSWORD_PATTERN.matcher(password).matches()){
            _txtInputPassword.setError("Invalid password");
            return false;
        }else{
            _txtInputPassword.setError(null);
            return true;
        }
    }
}
