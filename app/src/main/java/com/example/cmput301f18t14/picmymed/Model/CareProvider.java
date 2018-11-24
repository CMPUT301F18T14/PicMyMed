/*
 * CareProvider
 *
 * 1.1
 *
 * Copyright (C) 2018 CMPUT301F18T14. All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.example.cmput301f18t14.picmymed.Model;

import java.util.ArrayList;

/**
 * CareProvider class extends User class to create a CareProvider object with a patient list.
 *
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.1, 16/11/18
 * @since   1.1
 */
public class CareProvider extends User {

    private ArrayList<Patient> patientList;
    /**
     * CareProvider constructor to initialize the member variables.
     *
     * @param username                  String of CareProvider username
     * @throws IllegalArgumentException throws exception if the username is too long or 0 characters.
     */
    public CareProvider(String username) throws IllegalArgumentException {
        super(username);

        this.patientList = new ArrayList<Patient>();
    }
    /**
     * getPatientList returns the patient list
     *
     * @return  patientList
     */
    public ArrayList<Patient> getPatientList() {
        return this.patientList;
    }
    /**
     * isPatient checks if the user is a patient
     *
     * @return  boolean
     */
    @Override
    public Boolean isPatient() {
        return Boolean.FALSE;
    }
}