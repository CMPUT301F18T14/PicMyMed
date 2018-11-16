package com.example.mukha.picmymedcode.Model;


public class CareProvider extends User {

    private PatientList patientList;

    public CareProvider(String username) throws IllegalArgumentException {
        super(username);

        this.patientList = new PatientList();
    }

    public PatientList getPatientList() {
        return this.patientList;
    }

    @Override
    public Boolean isPatient() {
        return Boolean.FALSE;
    }
}
