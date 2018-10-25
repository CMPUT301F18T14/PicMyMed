package com.example.mukha.picmymedcode;

import java.util.ArrayList;

public class PatientList {

    private ArrayList<Patient> patientList;

    public PatientList() {

    }

    public ArrayList<Patient> getPatientList() {
        return patientList;
    }

    public Patient getPatient(int index) {
        return this.patientList.get(index);
    }

    public void deletePatient(int index) {
        this.patientList.remove(index);
    }

    public void addPatient(Patient patient) {
        this.patientList.add(patient);
    }

}
