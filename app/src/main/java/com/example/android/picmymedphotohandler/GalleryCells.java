/*
 * GalleryCells
 *
 * 1.2
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

package com.example.android.picmymedphotohandler;

import android.graphics.Bitmap;

/**
 * This class is a part of View that helps in dissecting information from
 * the Model that will be shown by the adapter.
 *
 * @author  Md Touhidul (Apu) Islam
 * @version 1.2, 02/12/18
 * @since   1.1
 */
public class GalleryCells {

    private String title;
    private Bitmap bitmap;
    private String filepath;
    private String base64;
    private int photoIndex;

    public int getPhotoIndex() {
        return photoIndex;
    }

    public void setPhotoIndex(int photoIndex) {
        this.photoIndex = photoIndex;
    }

    /**
     * Getter for title
     *
     * @return  String of title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Getter for bitmap
     *
     * @return  Bitmap file
     */
    public Bitmap getBitmap() {
        return bitmap;
    }

    /**
     * Getter for filepath
     *
     * @return  String of filepath
     */
    public String getFilepath() {
        return filepath;
    }

    /**
     * Setter for title
     *
     * @param title the title for the file
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Setter for bitmap
     *
     * @param bitmap the bitmap file
     */
    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    /**
     * Setter for filepath
     *
     * @param filepath  the absolute path of the file
     */
    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Method gets the photo representation
     *
     * @return  base64
     */
    public String getBase64() {
        return base64;
    }

    /**
     * Method sets the photo representation
     *
     * @param base64    String
     */
    public void setBase64(String base64) {
        this.base64 = base64;
    }
}
