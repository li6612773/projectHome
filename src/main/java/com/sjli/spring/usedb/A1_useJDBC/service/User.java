package com.sjli.spring.usedb.A1_useJDBC.service;

import java.nio.CharBuffer;

public class User {
    public long id;
    private String name;
    public CharBuffer number;

    public User(String name, String number) {
        this.name = name;
        this.number  = CharBuffer.wrap(number.toCharArray());
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
