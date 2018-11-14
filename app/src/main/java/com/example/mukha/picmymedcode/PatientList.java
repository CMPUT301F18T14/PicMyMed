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
        if (index < patientList.size()) {
            return this.patientList.get(index);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public void deletePatient(int index) {
        if (index < patientList.size()) {
            this.patientList.remove(index);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public void addPatient(Patient patient) {
        this.patientList.add(patient);
    }

    public boolean hasPatient(Patient patient) {
        return this.patientList.contains(patient);
    }

    public int sizeOfPatientList() {
        return this.patientList.size();
    }

}
