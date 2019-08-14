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
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rmanrique.airlines.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

import maes.tech.intentanim.CustomIntent;

public class LoginFirebaseActivity extends AppCompatActivity {
    TextView _txtViewTitleLoginFirebase;
    FloatingActionButton _floatButtonLoginFirebase;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;

    //TextInputLayouts
    TextInputLayout _txtInputEmailLogFirebase,_txtInputPasswordFLogFirebase;
    //TextInputEditText
    TextInputEditText _txtEditTextEmailLoginFirebase,_txtEditTextPasswordLoginFirebase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_firebase);
//        if(firebaseAuth.getCurrentUser() != null){
//            finish();
//            startActivity(new Intent(getApplicationContext(),FlightsScheduleActivity.class));
//        }
        initializeComponents();
        loginFirebase();

        _txtEditTextEmailLoginFirebase.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0){
                    String email = _txtInputEmailLogFirebase.getEditText().getText().toString();
                    validateEmail(email);
                }
                else
                    _txtEditTextEmailLoginFirebase.setError(null);
            }
        });
        _txtEditTextPasswordLoginFirebase.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0){
                    String password = _txtInputPasswordFLogFirebase.getEditText().getText().toString();
                    validatePassword(password);
                }
                else
                    _txtEditTextPasswordLoginFirebase.setError(null);
            }
        });
    }
    void initializeComponents(){
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        _txtViewTitleLoginFirebase = findViewById(R.id.txtViewLoginTitle);
        _txtInputEmailLogFirebase = findViewById(R.id.txtInputName);
        _txtInputPasswordFLogFirebase = findViewById(R.id.txtInputPasswordFLogFirebase);
        _txtEditTextEmailLoginFirebase = findViewById(R.id.txtEditTextEmailLoginFirebase);
        _txtEditTextPasswordLoginFirebase = findViewById(R.id.txtEditTextPasswordLoginFirebase);
        _floatButtonLoginFirebase = findViewById(R.id.floatButtonLoginFirebase);
    }
    void loginFirebase(){
        _floatButtonLoginFirebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = _txtInputEmailLogFirebase.getEditText().getText().toString();
                String password = _txtInputPasswordFLogFirebase.getEditText().getText().toString();
                if(validateEmail(email) == false || validatePassword(password) == false || (validateEmail(email) == false || validatePassword(password) == false)){
                    Toast.makeText(LoginFirebaseActivity.this,"Fields are not filled correctly",Toast.LENGTH_LONG).show();
                    _txtEditTextEmailLoginFirebase.setText("");
                    _txtEditTextPasswordLoginFirebase.setText("");
                }else {
                    firebaseAuth.signInWithEmailAndPassword(email, password).
                            addOnCompleteListener(LoginFirebaseActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressDialog.dismiss();
                                    if (task.isSuccessful()) {
                                        progressDialog.setMessage("Logging in");
                                        progressDialog.show();
                                        finish();
                                        startActivity(new Intent(getApplicationContext(), FlightsScheduleActivity.class));
                                        CustomIntent.customType(LoginFirebaseActivity.this,"left-to-right");
                                    }
                                }
                            });
                }
            }
        });
    }
    public boolean validateEmail(String email) {
        email = _txtInputEmailLogFirebase.getEditText().getText().toString().trim();
        Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$");
        if(email.isEmpty())
        {
            _txtInputEmailLogFirebase.setError("Field can't be empty");
            return false;
        }else if(!EMAIL_PATTERN.matcher(email).matches()){
            _txtInputEmailLogFirebase.setError("Invalid e-mail");
            return false;
        }else{
            _txtInputEmailLogFirebase.setError(null);
            return true;
        }
    }
    public boolean validatePassword(String password) {
        password = _txtInputPasswordFLogFirebase.getEditText().getText().toString().trim();
        Pattern PASSWORD_PATTERN = Pattern.compile("^[a-zA-Z]{6,10}$");
        if(password.isEmpty())
        {
            _txtInputPasswordFLogFirebase.setError("Field can't be empty");
            return false;
        }else if(!PASSWORD_PATTERN.matcher(password).matches()){
            _txtInputPasswordFLogFirebase.setError("Invalid password");
            return false;
        }else{
            _txtInputPasswordFLogFirebase.setError(null);
            return true;
        }
    }
}
