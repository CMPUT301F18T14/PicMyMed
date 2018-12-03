/*
 * PicMyMedController
 *
 * 1.2
 *
 * November 16, 2018
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
import java.util.Random;

/**
 * BodyLocationPhoto extends Photo and implements Serializable to add photos
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.2, 02/12/18
 * @since   2.0
 */
public class BodyLocationPhoto extends Photo implements Serializable {
    private String photoID;
    private String label;

    /**
     * Method initializes BodyLocationPhoto
     *
     * @param filepath  String
     */
    public BodyLocationPhoto(String filepath) {
        super(filepath);
        setUniquePhotoID();
    }

    /**
     * Method gets the label for a photo
     *
     * @return  label
     */
    public String getLabel() {
        return label;
    }

    /**
     * Method sets the label for a photo
     *
     * @param label String
     */
    public void setLabel(String label) {
        this.label = label;
    }


    /**
     * Method to generate a unique photo id
     */
    private void setUniquePhotoID() {
        final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        final int LENGTH_OF_THE_RANDOM_STRING = 18;

        StringBuilder stringBuilder = new StringBuilder();   // Builds the string
        Random random = new Random();               // Generates random numbers

        // Keeps on adding random numbers until the string is filled
        while (stringBuilder.length() < LENGTH_OF_THE_RANDOM_STRING) { // length of the random string
            // Randomly picks a character from CHARACTERS by randomly choosing index
            int index = (int) (random.nextFloat() * CHARACTERS.length());

            // Appending the random character to the String builder
            stringBuilder.append(CHARACTERS.charAt(index));
        }

        // Converting the object to String
        this.photoID = stringBuilder.toString();

    }
    public String getPhotoID() {
        return photoID;
    }

}
