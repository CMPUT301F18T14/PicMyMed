package com.example.mukha.picmymedcode;

public class CareProvider extends User {

    PatientList patientList;

    public CareProvider(String username, String password) {
        super(username, password);
        this.patientList = new PatientList();
    }
    public PatientList getPatientList() {
        return this.patientList;
    }
}
