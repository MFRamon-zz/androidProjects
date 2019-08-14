package com.example.rmanrique.airlines.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rmanrique.airlines.Controllers.InsertController;
import com.example.rmanrique.airlines.Models.Passenger;
import com.example.rmanrique.airlines.R;

import java.util.regex.Pattern;

public class AddPassengerActivity extends AppCompatActivity {
    FloatingActionButton _floatButtonSavePassenger;
    TextView _txtViewTitleCreateAccount;
    TextInputLayout _InputLayoutAge,_InputLayoutName,_InputLayoutLastName;
    TextInputEditText txtEditTextName,txtEditTextLastName,txtEditTextAge;
    InsertController _insertController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_passenger);
        try{
            InitializeControls();
            Events();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        txtEditTextName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    validateName();
                } else
                    txtEditTextName.setError(null);
            }
        });
        txtEditTextLastName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0){
                    validateLastName();
                }
                else
                    txtEditTextLastName.setError(null);
            }
        });
    }
    private void Events(){
        try{
            _floatButtonSavePassenger.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(validateName() == false || validateLastName() == false){
                        Toast.makeText(AddPassengerActivity.this,"Fill in passenger's information",Toast.LENGTH_LONG).show();
                        txtEditTextName.setText("");
                        txtEditTextLastName.setText("");
                        txtEditTextAge.setText("");
                    }else
                    {
                        Passenger passenger = new Passenger(
                                _InputLayoutName.getEditText().getText().toString(),
                                _InputLayoutLastName.getEditText().getText().toString(),
                                Integer.parseInt(_InputLayoutAge.getEditText().getText().toString()),
                                null);
                        _insertController.start(AddPassengerActivity.this, passenger);
                        final AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                        builder.setMessage("Do you want to add another contact?")
                                .setTitle("Answer")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Passenger passenger = new Passenger(
                                                _InputLayoutName.getEditText().getText().toString(),
                                                _InputLayoutLastName.getEditText().getText().toString(),
                                                Integer.parseInt(_InputLayoutAge.getEditText().getText().toString()),
                                                null
                                        );
                                        txtEditTextName.setText("");
                                        txtEditTextLastName.setText("");
                                        txtEditTextAge.setText("");
                                        _insertController.start(AddPassengerActivity.this, passenger);
                                    }
                                }).setNegativeButton("Error while completing your request", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                Intent intent = new Intent(getApplicationContext(),FlightsScheduleActivity.class);
                                startActivity(intent);
                                builder.getContext().startActivity(intent);
                            }
                        });


                    }
                }

            });
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private  void InitializeControls(){
        txtEditTextName =findViewById(R.id.txtEditTextName);
        txtEditTextLastName =findViewById(R.id.txtEditTextLastName);
        txtEditTextAge = findViewById(R.id.txtEditTextAge);
        _InputLayoutAge = findViewById(R.id.txtInputAge);
        _InputLayoutName = findViewById(R.id.txtInputName);
        _InputLayoutLastName = findViewById(R.id.txtInputLastName);
        _floatButtonSavePassenger = findViewById(R.id.floatButtonSavePassenger);
        _insertController = new InsertController();
    }
    public boolean validateName() {
        String ContactName = _InputLayoutName.getEditText().getText().toString().trim();
        Pattern EMAIL_PATTERN = Pattern.compile("[A-Z][a-zA-Z]*");
        if(ContactName.isEmpty())
        {
            _InputLayoutName.setError("Field can't be empty");
            return false;
        }else if(!EMAIL_PATTERN.matcher(ContactName).matches()){
            _InputLayoutName.setError("Not a valid name");
            return false;
        }else{
            _InputLayoutName.setError(null);
            return true;
        }
    }
    public boolean validateLastName() {
        String ContactLastName = _InputLayoutLastName.getEditText().getText().toString().trim();
        Pattern EMAIL_PATTERN = Pattern.compile("[A-Z][a-zA-Z]*");
        if(ContactLastName.isEmpty())
        {
            _InputLayoutLastName.setError("Field can't be empty");
            return false;
        }else if(!EMAIL_PATTERN.matcher(ContactLastName).matches()){
            _InputLayoutLastName.setError("Not a valid name");
            return false;
        }else{
            _InputLayoutLastName.setError(null);
            return true;
        }
    }
}
