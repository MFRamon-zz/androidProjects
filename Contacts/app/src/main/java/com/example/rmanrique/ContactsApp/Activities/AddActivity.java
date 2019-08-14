package com.example.rmanrique.ContactsApp.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.rmanrique.ContactsApp.Controllers.InsertController;
import com.example.rmanrique.ContactsApp.Models.Contact;
import com.example.rmanrique.ContactsApp.R;

import java.util.regex.Pattern;

public class AddActivity extends AppCompatActivity {
    TextInputEditText _TextName,_TextLastName,_TextAge;
    TextInputLayout _InputLayoutAge,_InputLayoutName,_InputLayoutLastName;
    ImageView _AddImage;
    Button _AddButton;
    InsertController _InsertController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        try{
            InitializeControls();
            Events();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        _TextName.addTextChangedListener(new TextWatcher() {
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
                    _TextName.setError(null);
            }
        });
        _TextLastName.addTextChangedListener(new TextWatcher() {
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
                    _TextLastName.setError(null);
            }
        });
    }

    private  void InitializeControls(){
        _TextName =findViewById(R.id.NameAdd);
        _TextLastName =findViewById(R.id.LastNameAdd);
        _TextAge = findViewById(R.id.AgeAdd);
        _InputLayoutAge = findViewById(R.id.AgeAddLayout);
        _InputLayoutName = findViewById(R.id.NameAddLayout);
        _InputLayoutLastName = findViewById(R.id.LastNameAddLayout);
        _AddImage = findViewById(R.id.imgAdd);
        _AddButton = findViewById(R.id.btnSave);
        _InsertController = new InsertController();
    }

    private void Events(){
        try{
            _AddButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Contact contact = new Contact(
                            _InputLayoutName.getEditText().getText().toString(),
                            _InputLayoutLastName.getEditText().getText().toString(),
                            Integer.parseInt(_InputLayoutAge.getEditText().getText().toString()),
                            null

                    );
                    if(validateName() == true || validateLastName() == true){
                        final AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                        builder.setMessage("Error")
                                .setTitle("Error")
                                .setPositiveButton("Retry",null);
                        builder.create().show();
                    }else{
                        _InsertController.start(AddActivity.this, contact);}


                    final AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setMessage("Wanna add another contact?")
                            .setTitle("Answer")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Contact contact = new Contact(
                                            _InputLayoutName.getEditText().getText().toString(),
                                            _InputLayoutLastName.getEditText().getText().toString(),
                                            Integer.parseInt(_InputLayoutAge.getEditText().getText().toString()),
                                            null
                                    );
                                    _TextName.setText("");
                                    _TextLastName.setText("");
                                    _TextAge.setText("");
                                    _InsertController.start(AddActivity.this, contact);
                                }
                            })
                            .setNegativeButton("Nope", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                    Intent intent = new Intent(getApplicationContext(),ContactsActivity.class);
                                    startActivity(intent);
                                    builder.getContext().startActivity(intent);
                                }
                            });


                }

            });
        }catch(Exception ex){
            ex.printStackTrace();
        }

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