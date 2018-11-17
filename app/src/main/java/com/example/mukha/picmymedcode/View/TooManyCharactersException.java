package com.example.mukha.picmymedcode.View;

public class TooManyCharactersException extends Exception {

    public TooManyCharactersException() {
        super("The input went over the maximum number of characters allowed.");
    }
}
