package com.example.mukha.picmymedcode.Model;

import java.util.LinkedList;
import java.util.Queue;

public abstract class User {

    private final String username;
    private static final Integer MAX_USER_ID_LENGTH = 8;
    private String id;

    public User(String username) throws IllegalArgumentException {

        if (username.length() == 0) {
            throw new IllegalArgumentException("User ID cannot be empty!");
        } else if (username.length() <= MAX_USER_ID_LENGTH) {
            throw new IllegalArgumentException(String.format("User ID should not exceed %s characters!", String.valueOf(MAX_USER_ID_LENGTH)));
        } else {
            this.username = username;
        }

    }


    public String getUsername(){
        return this.username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
