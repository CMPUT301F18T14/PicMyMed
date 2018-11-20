/*
 * PhotoList
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
 * PhotoList object creates an array list of photos and controls the number of photos per record
 *
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.1, 16/11/18
 * @since   1.1
 */
public class PhotoList {

    private ArrayList<Photo> photoList;

    /**
     * Constructor initializes variables
     */
    PhotoList(){
        photoList = new ArrayList<Photo>();
    }

    /**
     * Method gets list of photos
     *
     * @return photoList
     */
    public ArrayList<Photo> getPhotoList() {
        return photoList;
    }
    /**
     * Method gets a photo from list
     *
     * @param index int
     */
    public Photo getPhoto(int index) {
        if (index < photoList.size()) {
            return this.photoList.get(index);
        }
        else {
            return null;
        }
    }

    /**
     * Method deletes a photo from the list
     * @param index int
     */
    public void deletePhoto(int index) {
        if (index < photoList.size()) {
            this.photoList.remove(index);
        }
    }

    /**
     * Method adds photo to the list
     *
     * @param photo Photo
     */
    public void addPhoto(Photo photo) {
        this.photoList.add(photo);
    }

    /**
     * Method gets the size of the photo list
     *
     * @return  int
     */
    public int sizeOfPhotoList() {
        return this.photoList.size();
    }
}
