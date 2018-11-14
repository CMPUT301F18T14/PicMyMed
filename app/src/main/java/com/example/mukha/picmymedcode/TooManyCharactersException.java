package com.example.mukha.picmymedcode;

public class TooManyCharactersException extends Exception {

    TooManyCharactersException() {
        super("The input went over the maximum number of characters allowed.");
    }
}
