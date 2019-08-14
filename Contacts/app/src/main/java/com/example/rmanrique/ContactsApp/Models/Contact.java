package com.example.rmanrique.ContactsApp.Models;

public class Contact {

    public String Name;
    public String LastName;
    public int Age;
    public String id;

    public Contact(String name, String lastName, int age, String id) {
        this.Name = name;
        this.LastName = lastName;
        this.Age = age;
        this.id = id;
    }
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        this.LastName = lastName;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        this.Age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }




}

