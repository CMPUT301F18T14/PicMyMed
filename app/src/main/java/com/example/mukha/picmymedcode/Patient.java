package com.example.mukha.picmymedcode;

public class Patient extends User {


    private int phoneNumber;
    private String email;
    private ProblemList problemList;

    public Patient(String username, String password, String email, int phoneNumber) {

        super(username, password);
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

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ProblemList getProblemList() {
        return problemList;
    }
}
