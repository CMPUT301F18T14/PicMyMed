/*
 * UserActivity
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
 * User is an abstract class that provides the common functionality
 * of a Patient and Careprovider (username and userid)
 *
 * @author  Apu, Debra, Eenna, Ian, Shawna, Umer
 * @version 1.1, 16/11/18
 * @since   1.1
 */

public abstract class User {

    private final String username;
    private static final Integer MAX_USER_ID_LENGTH = 8;
    private String userID;


    /**
     * Initializes the username, verifying that it is a correct length
     * and assigns a userid for database purposes
     * @param username
     * @throws IllegalArgumentException
     */
    public User(String username) throws IllegalArgumentException {

        if (username.length() == 0) {
            throw new IllegalArgumentException("Username cannot be empty!");
        } else if (username.length() > MAX_USER_ID_LENGTH) {
            throw new IllegalArgumentException(String.format("User ID should not exceed %s characters!", String.valueOf(MAX_USER_ID_LENGTH)));

        } else {
            this.username = username;
        }

    }


    /**
     * Returns the username
     * @return username
     */
    public String getUsername(){
        return this.username;
    }

    /**
     * Returns userid
     * @return userid
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Sets the userid
     * @param userID
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * To be implemented by subclasses, to return
     * true if the user is a patient
     * @return
     */
    public abstract Boolean isPatient();

}
