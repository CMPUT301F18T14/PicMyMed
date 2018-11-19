package com.example.mukha.picmymedcode.Model;

import java.util.ArrayList;

public class Patient extends User {


    private String phoneNumber;
    private String email;
    ArrayList<Problem> problemList;

    public Patient(String username, String email, String phoneNumber) {

        super(username);
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.problemList = new ArrayList<Problem>();

    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ArrayList<Problem> getProblemList() {
       return problemList;
    }

    @Override
    public Boolean isPatient() {
        return Boolean.TRUE;
    }
}

