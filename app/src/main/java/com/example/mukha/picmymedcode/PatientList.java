package com.example.mukha.picmymedcode;

import java.util.ArrayList;

public class PatientList {

    private final ArrayList<Patient> patientList;

    public PatientList() {
        this.patientList = new ArrayList<Patient>();
    }

    public ArrayList<Patient> getPatientList() {
        return patientList;
    }

    public Patient getPatient(int index) {
        return this.patientList.get(index);
    }

    public void deletePatient(int index) {
        if (index < patientList.size()) {
            this.patientList.remove(index);
        }
    }

    public void addPatient(Patient patient) {
        this.patientList.add(patient);
    }

    public boolean hasPatient(Patient patient) { return this.patientList.contains(patient); }

    public int sizeOfPatientList() {
        return this.patientList.size();
    }

}
