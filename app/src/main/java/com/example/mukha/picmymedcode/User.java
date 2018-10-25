package com.example.mukha.picmymedcode;

public class User {

    private String username;
    private String password;

    public User() {
        this.username = null;
    }
    public User(String username){
        this.username = username;
    }
    public String getUsername(){
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }
}
