/*
 * GalleryActivity
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

package com.example.android.picmymedphotohandler;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.picmymedcode.Controller.PicMyMedApplication;
import com.example.picmymedcode.Controller.PicMyMedController;
import com.example.picmymedcode.Model.BodyLocationPhoto;
import com.example.picmymedcode.Model.Patient;
import com.example.picmymedcode.Model.Photo;
import com.example.picmymedcode.Model.Problem;
import com.example.picmymedcode.Model.Record;
import com.example.picmymedcode.R;
import com.example.picmymedcode.View.BodyLocationPhotoManagerActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * GalleryActivity performs actions on the database and
 * the GalleryAdapter settings to show the Gallery
 *
 * @author  Md Touhidul (Apu) Islam
 * @version 1.1, 16/11/18
 * @since   1.1
 *
 * Ideas Combined from the following sources:
 * 1. https://www.quora.com/How-do-I-display-images-from-a-specific-directory-in-internal-storage-in-RecyclerView
 * 2. https://www.youtube.com/watch?v=jGc0LG2MNKA
 * 3. https://developer.android.com/reference/android/content/Context
 * Used in: GalleryActivity.java, GalleryAdapter.java, GalleryCells.java, LoadingImageFiles.java
 */

public class GalleryActivity extends AppCompatActivity {
    android.support.v7.widget.Toolbar toolbar;

    private RecyclerView recyclerView;

    private RecyclerView.LayoutManager layoutManager;

    private GalleryAdapter galleryAdapter;

    private ArrayList<GalleryCells> galleryCells;

    private LoadingImageFiles loadingImageFiles;

    private Record record;

    private Patient user;

    private ImageButton addButton;

    private static final int CAMERA_REQUEST_CODE = 333;

    private int problemIndex;

    /**
     * Method loads state
     *
     * @param savedInstanceState    Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.galleryToolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setTitle("Record Photo Gallery");

        user = (Patient)PicMyMedApplication.getLoggedInUser();

        startActivity();
    }

    /**
     * This method performs operation on the data
     * to make it viewable under the defined adapter setting.
     *
     * @return      ArrayList of GalleryCells containing modified data for adapter compatibility
     */
    private ArrayList<GalleryCells> preparedData() {
        ArrayList<GalleryCells> imagesModified = new ArrayList<>();
        ArrayList<Bitmap> bitmaps = loadingImageFiles.jpegToBitmap();
        ArrayList<String> filePaths = loadingImageFiles.absoluteFilePaths();

        for(int i = 0; i < bitmaps.size(); i++){
            GalleryCells galleryCells = new GalleryCells();
            galleryCells.setTitle(""+(i + 1));
            galleryCells.setBitmap(bitmaps.get(i));
            galleryCells.setFilepath(filePaths.get(i));
            imagesModified.add(galleryCells);
        }
        return imagesModified;
    }

    /**
     * This method initiates all the required things to show the gallery.
     */
    private void startActivity() {

        int receivedIntentFrom = getIntent().getIntExtra("intentSender", 0);

        if (receivedIntentFrom == 1) {                  // From AddRecordActivity
            problemIndex = getIntent().getIntExtra("problemIndex", 0);
            int recordIndex = getIntent().getIntExtra("recordIndex", 0);
            record = user.getProblemList().get(problemIndex).getRecordList().get(recordIndex);

            // Prepare the data for adapter compatibility
            galleryCells = preparedData(record.getPhotoList());
            // Initialize the adapter
            galleryAdapter = new GalleryAdapter(galleryCells, GalleryActivity.this, problemIndex, recordIndex);

        } else if (receivedIntentFrom == 2) {           // From BodyLocation Activity
            // Prepare the data for adapter compatibility
            addButton.setVisibility(View.VISIBLE);
            galleryCells = preparedData((ArrayList<Photo>)(ArrayList<?>) user.getBodyLocationPhotoList());
            // Initialize the adapter
            galleryAdapter = new GalleryAdapter(galleryCells, GalleryActivity.this);
        }

        // Load the image files
        loadingImageFiles = new LoadingImageFiles(getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES));

        // Initialize the recycler view
        recyclerView = (RecyclerView) findViewById(R.id.gallery);
        recyclerView.setHasFixedSize(true);

        // Initialize the layout format and span
        layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        // Set the layout in the recycler view
        recyclerView.setLayoutManager(layoutManager);



        // Set the adapter to the recycler view
        recyclerView.setAdapter(galleryAdapter);
    }

    /**
     * This method performs operation on the data
     * to make it viewable under the defined adapter setting.
     *
     * @return      ArrayList of GalleryCells containing modified data for adapter compatibility
     */
    private ArrayList<GalleryCells> preparedData(ArrayList<Photo> photos) {
        ArrayList<GalleryCells> galleryCellsArrayList = new ArrayList<>();
        byte[] decodedString;
        Bitmap decodedByte;

        for (int i = 0; i < photos.size(); i++) {
            GalleryCells galleryCells = new GalleryCells();
            decodedString = Base64.decode(photos.get(i).getBase64EncodedString(), Base64.DEFAULT);
            decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            galleryCells.setPhotoIndex(i);
            galleryCells.setTitle(""+(i+1));
            galleryCells.setBase64(photos.get(i).getBase64EncodedString());
            galleryCells.setBitmap(decodedByte);
            galleryCellsArrayList.add(galleryCells);
        }

        return galleryCellsArrayList;
    }

    /**
     * Method sets photo location path and adds body photo location
     *
     * @param requestCode   int
     * @param resultCode    int
     * @param data          Intent data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == CAMERA_REQUEST_CODE) {
            try {
                Log.d("DEBUG BodyLocation","BodyLocation is being fetched!!!");
                Photo photo = (Photo) data.getSerializableExtra("photoObject");

                BodyLocationPhoto bodyLocationPhoto = new BodyLocationPhoto(photo.getPhotoPath());
                bodyLocationPhoto.setBase64EncodedString(photo.getBase64EncodedString());

                Log.d("BodyLocation is here!!!", photo.getPhotoPath());
                PicMyMedController.addBodyLocationPhoto(bodyLocationPhoto, GalleryActivity.this);
            } catch (Exception e) {
                Log.d("DEBUG BodyLocation", e.getMessage());
            }
        }
    }

    /**
     * This method refreshes everything upon resuming.
     */
    @Override
    protected void onResume() {
        super.onResume();
        startActivity();
    }
}
