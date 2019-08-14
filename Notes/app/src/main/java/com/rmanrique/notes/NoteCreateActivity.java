package com.rmanrique.notes;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rmanrique.notes.Controllers.NoteController;
import com.rmanrique.notes.Models.Note;

public class NoteCreateActivity extends AppCompatActivity implements View.OnClickListener  {
    //region Interface components declaration
    private Button _btnDeteleNote,_btnCreateNote;
    public TextInputLayout _txtLayoutTitle,_txtLayoutNoteText,_txtLayoutAuthor;
    private EditText _editTextTitle,_editTextNoteText,_editTextAuthor;
    private static final String TAG = "NoteCreateActivity";
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_create);
        initializeComponents();
        setListeners();
        setValues();
    }
    private void initializeComponents(){
        _btnDeteleNote = findViewById(R.id.btnDeteleNote);
        _btnCreateNote = findViewById(R.id.btnCreateNote);
        _txtLayoutTitle = findViewById(R.id.txtLayoutTitle);
        _txtLayoutNoteText = findViewById(R.id.txtLayoutNoteText);
        _txtLayoutAuthor = findViewById(R.id.txtLayoutPasswordRegister);
        _editTextTitle = findViewById(R.id.editTextTitle);
        _editTextNoteText = findViewById(R.id.editTextNoteText);
        _editTextAuthor = findViewById(R.id.editTextAuthor);
        _editTextAuthor.requestFocus();
    }
    private void setListeners(){
        _btnDeteleNote.setOnClickListener(this);
        _btnCreateNote.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnCreateNote){
            Note note = retrieveData();
            if (note.getId() != null) {
                try {
                    if(updateNote() == true){
                        Snackbar snackbar = Snackbar.make(view, "Note updated successfully", Snackbar.LENGTH_LONG);
                        snackbar.show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(NoteCreateActivity.this, NotesActivity.class);
                                startActivity(intent);
                            }
                        }, 1500);
                    }else
                    {
                        Snackbar snackbar = Snackbar.make(view, "Try again", Snackbar.LENGTH_LONG);
                        snackbar.show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                emptyComponents();
                            }
                        }, 1500);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else{
                try{
                    if(createNote() == true){
                        Snackbar snackbar = Snackbar.make(view, "Note saved successfully", Snackbar.LENGTH_LONG);
                        snackbar.show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(NoteCreateActivity.this, NotesActivity.class);
                                startActivity(intent);
                            }
                        }, 1500);
                    }else
                    {
                        Snackbar snackbar = Snackbar.make(view, "Try again", Snackbar.LENGTH_LONG);
                        snackbar.show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                emptyComponents();
                            }
                        }, 1500);
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        if(view.getId() == R.id.btnDeteleNote){
            NoteController noteController = new NoteController();
            Note note = retrieveData();
            noteController.deleteNote(note.getId());
            Snackbar snackbar = Snackbar.make(view, "Note deleted successfully", Snackbar.LENGTH_LONG);
            snackbar.show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(NoteCreateActivity.this, NotesActivity.class);
                    startActivity(intent);
                }
            }, 1500);
        }
    }
    public boolean createNote(){
        Note note = new Note();
        NoteController noteController = new NoteController();
        note.setAuthor(_txtLayoutAuthor.getEditText().getText().toString());
        note.setTitle(_txtLayoutTitle.getEditText().getText().toString());
        note.setText(_txtLayoutNoteText.getEditText().getText().toString());
        note.setId(null);
        noteController.createNote(note);
        if(note.getText().isEmpty()== false && note.getAuthor().isEmpty() == false && note.getTitle().isEmpty() == false){
            return true;
        }else if(note.getText() != "" && note.getAuthor() != "" && note.getTitle() != ""){} return false;
    }
    public void emptyComponents(){
        _editTextAuthor.setText("");
        _editTextTitle.setText("");
        _editTextNoteText.setText("");
    }
    public boolean updateNote(){
        Note note;
        note = retrieveData();
        NoteController noteController = new NoteController();
        note.setTitle(_txtLayoutTitle.getEditText().getText().toString());
        note.setAuthor(_txtLayoutAuthor.getEditText().getText().toString());
        note.setText(_txtLayoutNoteText.getEditText().getText().toString());
        noteController.updateNote(note);
        if(note.getText().isEmpty()== false && note.getAuthor().isEmpty() == false && note.getTitle().isEmpty() == false){
            return true;
        }else if(note.getText() != "" && note.getAuthor() != "" && note.getTitle() != ""){} return false;
    }
    public Note retrieveData(){
        Note note = new Note();
        Intent intent = getIntent();
        note.setId(intent.getStringExtra("id"));
        note.setText(intent.getStringExtra("noteText"));
        note.setAuthor(intent.getStringExtra("author"));
        note.setTitle(intent.getStringExtra("title"));
        return note;
    }
    public void setValues(){
        Intent intent = getIntent();
        if(intent == null){
            return;
        }else{
            _editTextAuthor.setText(intent.getStringExtra("author"));
            _editTextTitle.setText(intent.getStringExtra("title"));
            _editTextNoteText.setText(intent.getStringExtra("noteText"));
        }
    }

}
