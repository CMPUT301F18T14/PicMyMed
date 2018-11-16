package com.example.mukha.picmymedcode.Model;

import java.util.ArrayList;

public class PatientList {

    private final ArrayList<String> patientList;

    public PatientList() {
        this.patientList = new ArrayList<String>();
    }

    public ArrayList<String> getPatientList() {
        return patientList;
    }

    public String getPatient(int index) {
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

    public void addPatient(String patient) {
        this.patientList.add(patient);
    }

    public boolean hasPatient(String patient) {
        return this.patientList.contains(patient);
    }

    public int sizeOfPatientList() {
        return this.patientList.size();
    }

}
