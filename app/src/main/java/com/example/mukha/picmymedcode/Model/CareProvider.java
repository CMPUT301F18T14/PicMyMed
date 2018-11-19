package com.example.mukha.picmymedcode.Model;

public class CareProvider extends User {

    PatientList patientList;

    public CareProvider(String username) {
        super(username);
        this.patientList = new PatientList();
    }

    public PatientList getPatientList() {
        return this.patientList;
    }
}
