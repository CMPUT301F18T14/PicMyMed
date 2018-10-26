package com.example.mukha.picmymedcode;

public class Patient extends User {


    private Integer phoneNumber;
    private String email;
    private ProblemList problemList;

    public Patient() {
        this(null, null);
    }
    public Patient(String email, Integer phoneNumbet) {

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

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ProblemList getProblemList() {
        return problemList;
    }
}
