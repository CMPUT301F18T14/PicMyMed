/*
 * PatientList
 *
 * 1.1
 *
 * November 16, 2018
 *
 * Copyright 2018 CMPUT301F18T14. All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.mukha.picmymedcode.Model;

import java.util.ArrayList;
/**
 * PatientList class creates and handles a care provider's list of patients
 *
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.1, 16/11/18
 * @since   1.1
 */
public class PatientList {

    private final ArrayList<String> patientList;

    /**
     * Constructor initializes variables
     */
    public PatientList() {
        this.patientList = new ArrayList<String>();
    }

    /**
     * Method returns an array list of strings of patients
     *
     * @return  ArrayList patientList
     */
    public ArrayList<String> getPatientList() {
        return patientList;
    }

    /**
     * Method gets a particular patient
     *
     * @param index int
     * @return      String patient from patientList
     */
    public String getPatient(int index) {
        if (index < patientList.size()) {
            return this.patientList.get(index);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Method deletes a patient
     *
     * @param index int
     */
    public void deletePatient(int index) {
        if (index < patientList.size()) {
            this.patientList.remove(index);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Method adds a patient to the patient list
     *
     * @param patient   String patient
     */
    public void addPatient(String patient) {
        this.patientList.add(patient);
    }

    /**
     * Method checks if patient list contains a particular patient
     *
     * @param patient   String patient
     * @return          Boolean
     */
    public boolean hasPatient(String patient) {
        return this.patientList.contains(patient);
    }

    /**
     * Method checks the size of the patient list
     *
     * @return  int
     */
    public int sizeOfPatientList() {
        return this.patientList.size();
    }

}
