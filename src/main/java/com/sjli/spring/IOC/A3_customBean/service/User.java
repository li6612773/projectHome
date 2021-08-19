package com.sjli.spring.IOC.A3_customBean.service;

public class User {
    public User() {
        
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }


    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    private int Id;

    public User(int id, String email, String password, String name) {
        Id = id;
        Email = email;
        Password = password;
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    private String Email;
    private String Password;
    private String Name;


}
