/*
 * PicMyMedController
 *
 * 2.0
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
/**
 * BodyLocationPhoto extends Photo and implements Serializable to add photos
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 2.0, 30/11/18
 * @since   2.0
 */
public class BodyLocationPhoto extends Photo implements Serializable {
    private String label;

    /**
     * Method initializes BodyLocationPhoto
     *
     * @param filepath  String
     */
    public BodyLocationPhoto(String filepath) {
        super(filepath);
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

}
