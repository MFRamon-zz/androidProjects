package com.rmanrique.notes.Controllers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.rmanrique.notes.Adapters.NoteAdapter;
import com.rmanrique.notes.Interfaces.NoteService;
import com.rmanrique.notes.Models.Note;
import com.rmanrique.notes.Services.ServiceBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoteController {
    public List<Note> notesList;
    public RecyclerView.Adapter notesAdapter;
    public RecyclerView notesRecyclerView;
    public Context context;

    public void getNotes(final RecyclerView rcView, Context cntxt) {
        context = cntxt;
        notesRecyclerView = rcView;
        notesList = new ArrayList<>();
        NoteService noteService = ServiceBuilder.buildService(NoteService.class);
        Call<List<Note>> notesRequest = noteService.getNote();

        notesRequest.enqueue(new Callback<List<Note>>() {
            @Override
            public void onResponse(Call<List<Note>> call, Response<List<Note>> response) {
                switch (response.code()) {
                    case 200:
                        notesList.clear();
                        notesList = response.body();
                        notesRecyclerView.setAdapter(new NoteAdapter(notesList));
                        break;
                    case 401:
                        break;
                    default:
                        break;
                }
            }
            @Override
            public void onFailure(Call<List<Note>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
    public void createNote(Note note){
        NoteService noteService = ServiceBuilder.buildService(NoteService.class);
        Call<Note> createNoteRequest = noteService.createNote(note);
        createNoteRequest.enqueue(new Callback<Note>() {
            @Override
            public void onResponse(Call<Note> call, Response<Note> response) {
                switch (response.code()) {
                    case 200:
                        break;
                    case 401:
                        break;
                    default:
                        break;
                }
            }
            @Override
            public void onFailure(Call<Note> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void updateNote(Note note){
        NoteService noteService = ServiceBuilder.buildService(NoteService.class);
        Call<Note> updateNoteRequest = noteService.updateNote(note,note.getId());
        updateNoteRequest.enqueue(new Callback<Note>() {
            @Override
            public void onResponse(Call<Note> call, Response<Note> response) {
                switch (response.code()) {
                    case 200:
                        break;
                    case 401:
                        break;
                    default:
                        break;
                }
            }
            @Override
            public void onFailure(Call<Note> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
    public void deleteNote(String id){
        NoteService noteService = ServiceBuilder.buildService(NoteService.class);
        Call<String> deleteNoteRequest = noteService.deleteNote(id);
        deleteNoteRequest.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                switch (response.code()) {
                    case 200:
                        break;
                    case 401:
                        break;
                    default:
                        break;
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
}
