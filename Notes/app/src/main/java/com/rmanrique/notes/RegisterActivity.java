package com.rmanrique.notes;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    //region Interface components declaration
    private TextView _txtViewRegisterTitle;
    private TextInputLayout _txtLayoutUsernameRegister;
    private TextInputLayout _txtLayoutPasswordRegister;
    private EditText _editTextUsername;
    private EditText _editTextPassword;
    private Button _btnRegister;
    private ConstraintLayout _constraintLayout;
    private static final String TAG = "RegisterActivity";
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initializeComponents();
        setListeners();

        _editTextUsername.addTextChangedListener(new TextWatcher() {
            Intent intent = new Intent(RegisterActivity.this,NotesActivity.class);
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0){
                    String username = _txtLayoutUsernameRegister.getEditText().getText().toString();
                    validateUsername(username);
                }
                else
                    _editTextUsername.setError(null);

            }
        });
        _editTextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0){
                    String password = _txtLayoutPasswordRegister.getEditText().getText().toString();
                    validatePassword(password);
                }
                else
                    _editTextPassword.setError(null);
            }
        });
    }
    private void initializeComponents(){
        _txtViewRegisterTitle = findViewById(R.id.txtViewRegisterTitle);
        _txtLayoutUsernameRegister = findViewById(R.id.txtLayoutUsernameRegister);
        _txtLayoutPasswordRegister = findViewById(R.id.txtLayoutPasswordRegister);
        _editTextUsername = findViewById(R.id.editTextUsername);
        _editTextPassword = findViewById(R.id.editTextPassword);
        _btnRegister = findViewById(R.id.btnRegister);
        _constraintLayout = findViewById(R.id.constraintLayout);
    }
    public void setListeners(){
        _btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnRegister){
            Log.d(TAG, "onClick: btnRegister just clicked");
            Intent intent = new Intent(RegisterActivity.this,IntroductionActivity.class);
            String username = _txtLayoutUsernameRegister.getEditText().getText().toString();
            String password = _txtLayoutPasswordRegister.getEditText().getText().toString();

            if(validateUsername(username) == true && validatePassword(password) == true){
                startActivity(intent);
            }
        }
    }
    public boolean validateUsername(String username) {
        username = _txtLayoutUsernameRegister.getEditText().getText().toString().trim();
        Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$");
        if (username.isEmpty()) {
            _txtLayoutUsernameRegister.setError("Field can't be empty");
            return false;
        } else if (!USERNAME_PATTERN.matcher(username).matches()) {
            _txtLayoutUsernameRegister.setError("Invalid username");
            return false;
        } else {
            _txtLayoutUsernameRegister.setError(null);
            return true;
        }
    }
    public boolean validatePassword(String password) {
        password = _txtLayoutPasswordRegister.getEditText().getText().toString().trim();
        Pattern PASSWORD_PATTERN = Pattern.compile("^[a-zA-Z]{6,10}$");
        if(password.isEmpty())
        {
            _txtLayoutPasswordRegister.setError("Field can't be empty");
            return false;
        }else if(!PASSWORD_PATTERN.matcher(password).matches()){
            _txtLayoutPasswordRegister.setError("Invalid password");
            return false;
        }else{
            _txtLayoutPasswordRegister.setError(null);
            return true;
        }
    }
}
