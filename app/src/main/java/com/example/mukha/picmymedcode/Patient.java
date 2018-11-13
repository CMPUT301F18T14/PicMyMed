package com.example.mukha.picmymedcode;

import com.example.mukha.picmymedcode.ProblemFile.ProblemList;

public class Patient extends User {


    private String phoneNumber;
    private String email;
    private ProblemList problemList;

    public Patient(String username, String email, String phoneNumber) {

        super(username);
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.problemList = new ProblemList();

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

    public ProblemList getProblemList() {
        return problemList;
    }
}
