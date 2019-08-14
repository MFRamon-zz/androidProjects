package com.rmanrique.notes.Models;

public class Note {
    //Attributes
    public String id;
    public String author;
    public String text;
    public String title;

    //Getters
    public String getId() {
        return id;
    }
    public String getAuthor() {
        return author;
    }
    public String getText() {
        return text;
    }
    public String getTitle() {
        return title;
    }

    //Setters
    public void setId(String id) {
        this.id = id;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setText(String text) {
        this.text = text;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
