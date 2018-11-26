/*
 * User
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
package com.example.picmymedcode.Model;

/**
 * User is an abstract class that provides the common functionality
 * of a Patient and Careprovider (username and userid)
 *
 * @author  Apu, Debra, Eenna, Ian, Shawna, Umer
 * @version 1.1, 16/11/18
 * @since   1.1
 */
public abstract class User {

    private final String username;
    private static final Integer MIN_USER_ID_LENGTH = 8;
    private String userID;
    private String phoneNumber;
    private String email;

    /**
     * Initializes the username, verifying that it is a correct length
     * and assigns a userid for database purposes
     * @param username                  String
     * @throws IllegalArgumentException throws an exception when the username is empty or too long
     */
    public User(String username, String email, String phoneNumber) throws IllegalArgumentException {

        if (username.length() < MIN_USER_ID_LENGTH) {
            throw new IllegalArgumentException(String.format("User ID should be at least %s characters!", String.valueOf(MIN_USER_ID_LENGTH)));
        } else {
            this.username = username;
            setEmail(email);
            setPhoneNumber(phoneNumber);
        }
    }
    /**
     * Method gets username
     *
     * @return username String
     */
    public String getUsername(){
        return this.username;
    }

    /**
     * Method returns userid
     *
     * @return userid String
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Method sets the userid
     *
     * @param userID String
     */
    public void setUserID(String userID) {

        this.userID = userID;
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
        if (email.length() == 0) {
            throw new IllegalArgumentException("Email cannot be empty!");
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            throw new IllegalArgumentException("Invalid email address!");
        } else {
            this.email = email;
        }

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

        if (phoneNumber.length() == 0) {
            throw new IllegalArgumentException("Phone number cannot be empty!");
        } else if (!phoneNumber.matches("^[+]?[0-9]{10,13}$")) {
            throw new IllegalArgumentException(("Phone number is invalid!"));
        } else {
            this.phoneNumber = phoneNumber;
        }


    }

    /**
     * Method to be implemented by subclasses, and will return
     * true if the user is a patient
     *
     * @return Boolean
     */
    public abstract Boolean isPatient();

}