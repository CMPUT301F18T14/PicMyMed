package com.example.mukha.picmymedcode;

public class TooManyListElementsException extends Exception {

    public TooManyListElementsException(){
        super("Attempted to add more elements to a list than is allowed.");
    }
}
