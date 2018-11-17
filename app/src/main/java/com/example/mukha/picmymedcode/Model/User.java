package com.example.mukha.picmymedcode.Model;

public abstract class User {

    private final String username;
    private static final Integer MAX_USER_ID_LENGTH = 8;
    private String userID;

    public User(String username) throws IllegalArgumentException {

        if (username.length() == 0) {
            throw new IllegalArgumentException("Username cannot be empty!");
        } else if (username.length() > MAX_USER_ID_LENGTH) {
            throw new IllegalArgumentException(String.format("User ID should not exceed %s characters!", String.valueOf(MAX_USER_ID_LENGTH)));

        } else {
            this.username = username;
        }

    public User(String username) {
        this.username = username;
    }

    public String getUsername(){
        return this.username;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public abstract Boolean isPatient();

}
