/*
 * Patient
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

/**
 * Patient class extends User to create a patient object with a phone number, email and list of problems
 *
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.1, 16/11/18
 * @since   1.1
 */

import com.example.mukha.picmymedcode.View.TooManyCharactersException;

public class Patient extends User {


    private String phoneNumber;
    private String email;
    private ProblemList problemList;

    /**
     * This method constructor initializes the username, email and phone number for Patient
     *
     * @param username                  String username
     * @param email                     String email
     * @param phoneNumber               String phone number
     * @throws IllegalArgumentException throws an exception when username is too long or 0 characters
     */
    public Patient(String username, String email, String phoneNumber) throws IllegalArgumentException {

        super(username);
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.problemList = new ProblemList();

    }

    /**
     * Method gets user email
     *
     * @return  String email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Method sets user email
     *
     * @param email String email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Method gets user phone number
     *
     * @return String phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Method sets user phone number
     *
     * @param phoneNumber   String phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Method gets user problem list
     *
     * @return  problemList
     */
    public ProblemList getProblemList() {
        return problemList;
    }

    /**
     * Method checks if user is a patient
     *
     * @return  Boolean
     */
    @Override
    public Boolean isPatient() {
        return Boolean.TRUE;
    }
}
