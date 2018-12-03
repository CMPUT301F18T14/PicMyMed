/*
 * LoadingImageFiles
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
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.example.picmymedcode.Model.Photo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * This class loads images stored in the internal disk space of the application,
 * and converts those images into Bitmap.
 *
 * @author  Md Touhidul (Apu) Islam
 * @version 1.2, 02/12/18
 * @since   1.1
 */
public class LoadingImageFiles {

    private File directory;

    private File[] files;

    private ArrayList<Bitmap> bitmaps;

    private ArrayList<String> filePaths;

    /**
     * Constructor for the class. It initializes the member variables.
     *
     * @param directoryFile    The internal directory file in the absolute path.
     */
    public LoadingImageFiles(File directoryFile){
        //directoryPath = path;
        directory = directoryFile;
        files = directory.listFiles();
        bitmaps = new ArrayList<Bitmap>();
        filePaths = new ArrayList<String>();
    }

    /**
     * This method converts the Base64String into a Bitmap.
     *
     * @return  an array list of bitmaps
     */
    public ArrayList<Bitmap> base65ToBitmap(ArrayList<Photo> photos) {

        byte[] decodedString;
        Bitmap decodedByte;
        for (int i = 0; i < photos.size(); i++) {
            // Converting to byte array
            decodedString = Base64.decode(photos.get(i).getBase64EncodedString(), Base64.DEFAULT);
            // Converting to Bitmap
            decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            bitmaps.add(decodedByte);
        }
        return bitmaps;
    }

    /**
     * This method converts the file into bitmap using the absolute path of the file.
     *
     * @return  an array of bitmap files
     */
    public ArrayList<Bitmap> jpegToBitmap(){
        for (int i = 0; i < files.length; i++){
            File imageFile = new File(files[i].getAbsolutePath());

            if (imageFile.exists() && imageFile.length() != 0){
                filePaths.add(imageFile.getAbsolutePath());
                Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
                bitmaps.add(bitmap);
            }
        }
        return bitmaps;
    }

    /**
     * This method returns the absolute path of the file stored in
     * a File type array using an index
     *
     * @return  List of String containing absolute paths of the files
     */
    public ArrayList<String> absoluteFilePaths(){
        return filePaths;
    }
}
