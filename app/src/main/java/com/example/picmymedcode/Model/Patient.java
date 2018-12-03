/*
 * Patient
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

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Patient class extends User to create a patient object with a phone number, email and list of problems
 *
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.1, 16/11/18
 * @since   1.1
 */
public class Patient extends User implements Serializable {

    ArrayList<Problem> problemList;
    ArrayList<BodyLocationPhoto> bodyLocationPhotoList;

    /**
     * This method constructor initializes the username, email and phone number for Patient
     *
     * @param username                  String username
     * @param email                     String email
     * @param phoneNumber               String phone number
     * @throws IllegalArgumentException throws an exception when username is too long or 0 characters
     */
    public Patient(String username, String email, String phoneNumber) {

        super(username, email, phoneNumber);
        this.problemList = new ArrayList<Problem>();
        this.bodyLocationPhotoList = new ArrayList<BodyLocationPhoto>();

    }

    /**
     * Method gets user problem list
     *
     * @return  problemList
     */
    public ArrayList<Problem> getProblemList() {
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

    /**
     * Method gets the body location photo list
     *
     * @return  bodyLocationPhotoList
     */
    public ArrayList<BodyLocationPhoto> getBodyLocationPhotoList() {
        return bodyLocationPhotoList;
    }

    /**
     * Method sets the body location photo list
     *
     * @param bodyLocationPhotoList ArrayList<BodyLocationPhoto></BodyLocationPhoto>
     */
    public void setBodyLocationPhotoList(ArrayList<BodyLocationPhoto> bodyLocationPhotoList) {
        this.bodyLocationPhotoList = bodyLocationPhotoList;
    }

    /**
     * Method adds a body location photo
     *
     * @param photo BodyLocationPhoto
     */
    public void addBodyLocationPhoto(BodyLocationPhoto photo) {
        this.bodyLocationPhotoList.add(photo);
    }

    /**
     * Method removes a body location photo
     *
     * @param index int
     */
    public void removeBodyLocationPhoto(int index) {
        this.bodyLocationPhotoList.remove(index);
    }
}

