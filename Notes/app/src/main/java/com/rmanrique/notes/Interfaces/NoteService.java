package com.rmanrique.notes.Interfaces;

import com.rmanrique.notes.Models.Note;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface NoteService {
    @GET("notes")
    Call<List<Note>> getNote();

    @POST("notes")
    Call<Note> createNote(@Body Note note);

    @PUT("notes/{id}")
    Call<Note> updateNote(@Body Note note, @Path("id") String id);

    @DELETE("notes/{id}")
    Call<String> deleteNote(@Path("id")String id);
}
