package com.example.mukha.picmymedcode;

public class Patient extends User {



    private Integer phoneNumber;
    private String email;
    private ProblemList problemList;

    public Patient() {
        this.problemList = new ProblemList();
        this.email = null;
        this.phoneNumber = null;
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

}
