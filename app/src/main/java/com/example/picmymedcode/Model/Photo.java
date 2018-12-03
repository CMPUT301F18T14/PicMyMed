/*
 * Photo
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

import android.graphics.Bitmap;

import java.io.File;
import java.io.Serializable;
import java.util.Random;

/**
 * Photo class sets the file path of a photo and returns it
 *
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.1, 16/11/18
 * @since   1.1
 */
public class Photo implements Serializable{



    private String filepath;
    private String base64EncodedString;
    /**
     * Construct initializes the variables
     *
     * @param filepath  String
     */
    public Photo(String filepath) {
        this.filepath = filepath;

    }


    /**
     * Method gets the file path of the photo
     *
     * @return  String
     */
    public String getPhotoPath() {
        return this.filepath;
    }

    /**
     * Method gets the string representation of the photo
     * @return
     */
    public String getBase64EncodedString() {
        return base64EncodedString;
    }

    /**
     * Method sets the string representation of the photo
     * @param base64EncodedString
     */
    public void setBase64EncodedString(String base64EncodedString) {
        this.base64EncodedString = base64EncodedString;
    }

}
