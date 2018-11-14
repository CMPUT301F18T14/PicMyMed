package com.example.mukha.picmymedcode;

public abstract class User {

    private final String username;
    private static final Integer MAX_USER_ID_LENGTH = 8;

    public User(String username) throws TooManyCharactersException {

        if (username.length() <= MAX_USER_ID_LENGTH) {
            this.username = username;
        } else {
            throw new TooManyCharactersException();
        }

    }

    public String getUsername(){
        return this.username;
    }

}
