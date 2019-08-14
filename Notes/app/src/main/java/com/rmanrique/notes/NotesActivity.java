package com.rmanrique.notes;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.rmanrique.notes.Controllers.NoteController;

public class NotesActivity extends AppCompatActivity implements View.OnClickListener {
    //region Interface components declaration
    private ConstraintLayout _consLayoutNotes, _consLayoutTitleNotes;
    private FloatingActionButton _addNoteButton;
    private RecyclerView _rcvNotes;
    private TextView _txtViewNotesTitle;
    private NoteController _noteController;
    private static final String TAG = "NotesActivity";
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        initializeComponents();
        setListeners();
        if(isOnline()){
            getNotes();
        }else{
            Snackbar snackbar = Snackbar.make(_consLayoutNotes, "Could not connect to a network", Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    }
    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.addNoteButton){
            Log.d(TAG, "onClick: addNoteButton just clicked");
            Intent intent = new Intent(NotesActivity.this,NoteCreateActivity.class);
            startActivity(intent);
        }
    }
    public void initializeComponents(){
        _consLayoutNotes = findViewById(R.id.consLayoutNotes);
        _consLayoutTitleNotes = findViewById(R.id.consLayoutTitleNotes);
        _addNoteButton = findViewById(R.id.addNoteButton);
        _rcvNotes = findViewById(R.id.rcvNotes);
        _txtViewNotesTitle = findViewById(R.id.txtViewNotesTitle);
    }
    public void setListeners(){
        _consLayoutNotes.setOnClickListener(this);
        _consLayoutTitleNotes.setOnClickListener(this);
        _addNoteButton.setOnClickListener(this);
        _rcvNotes.setOnClickListener(this);
        _txtViewNotesTitle.setOnClickListener(this);
    }
    public void  getNotes(){
        LinearLayoutManager llm = new LinearLayoutManager(this);
        _rcvNotes.setLayoutManager(llm);
        _rcvNotes.setHasFixedSize(true);
        _noteController =  new NoteController();
        _noteController.getNotes(_rcvNotes,this);
    }
    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }
}
