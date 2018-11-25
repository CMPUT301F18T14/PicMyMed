/*
 * PhotoIntentActivity
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
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.picmymedcode.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * This class sends intent to built-in camera app,
 * saves the photo temporarily in a placeholder file,
 * scale down the photo before loading into memory,
 * converts into bitmap and keeps on scaling down until the size is 64kb,
 * stores the photo in the internal private app space in .jpg format.
 *
 * @author  Md Touhidul (Apu) Islam
 * @version 1.1, 16/11/18
 * @since   1.1
 *
 * All the resources are a combination of
 * 1. https://android.jlelse.eu/loading-large-bitmaps-efficiently-in-android-66826cd4ad53
 * 2. https://developer.android.com/topic/performance/graphics/load-bitmap#java
 * 3. https://stuff.mit.edu/afs/sipb/project/android/docs/training/camera/photobasics.html
 * 4. https://stackoverflow.com/questions/51919925/compress-bitmap-to-a-specific-byte-size-in-android
 * 5. https://4nsi.com/faq/how-do-i-calculate-the-file-size-for-a-digital-image
 * 6. https://code.i-harness.com/en/q/f0775b
 * 7. https://medium.com/@adigunhammedolalekan/how-to-resize-images-for-better-upload-download-performance-android-development-fb7297f9ec24
 */

public class PhotoIntentActivity extends AppCompatActivity {

    private Button cameraButton;

    private final static String FILE_FORMATE = ".JPEG";

    private ImageView imageView;

    private ArrayList<Bitmap> bitmaps;

    private File photoFile = null;

    private final static int MAX_FILE_SIZE = 65536;

    private final static int MAX_NUMBER_OF_PHOTOS = 10;

    private final static String TAG = "PhotoIntentActivity: ";

    private static final int REQUEST_TAKE_PHOTO = 1;

    private static final int RESIZING_WIDTH = 300;

    private static final int RESIZING_HEIGHT = 300;

    /**
     * Method loads activity state
     *
     * @param savedInstanceState    Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_intent);

        imageView = (ImageView) findViewById(R.id.imageView_show1);

        bitmaps = new ArrayList<Bitmap>();

//        if (bitmaps.size() < MAX_NUMBER_OF_PHOTOS) {
//            //System.out.println(bitmaps.size());
//            dispatchTakePictureIntent();
//        }
//        else {
//            Toast.makeText(PhotoIntentActivity.this,
//                    "You can only have 10 photos per records.",
//                    Toast.LENGTH_LONG).show();
//        }

        cameraButton = (Button) findViewById(R.id.camera_button);

        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bitmaps.size() < MAX_NUMBER_OF_PHOTOS) {
                    //System.out.println(bitmaps.size());
                    dispatchTakePictureIntent();
                }
                else{
                    Toast.makeText(PhotoIntentActivity.this,
                            "You can only have 10 photos per records.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    /**
     * Method gets the image back from the camera intent and processes the image
     *
     * @param requestCode   int
     * @param resultCode    int
     * @param data          Intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            try {
                handleBigCameraPhoto(photoFile, imageView, MAX_FILE_SIZE);
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Could not carryout the operation!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * This method carryout the processing of the image after being taken from camera
     *
     * @param imageFile         The imageFile that contains the image returned from camera
     * @param imageView         The Layout where the image will be displayed
     * @param maxFileSize       The maximum byte size the file will be stored in the device
     * @throws IOException      Input Output exceptions for handling file
     */
    private void handleBigCameraPhoto(File imageFile, ImageView imageView, int maxFileSize) throws IOException {

        // Getting the absolute path of the image
        String imageFilePath = imageFile.getAbsolutePath();

        if (imageFilePath != null) {

            // Decodeing the image into bitmap with the ImageView layout dimensions
            Bitmap bitmap = decodeImageFromFiles(imageFilePath, RESIZING_WIDTH, RESIZING_HEIGHT);

            if (bitmap.getByteCount() <= maxFileSize) {
                /* If the number of bytes is less than the maximum bytes,
                 * then save the image with 100% quality */
                byte[] bitmapData = convertBitmapToJPEG(bitmap, 100);

                File fileToBeStored = writingByteArrayToFile(imageFile, bitmapData);

                galleryAddPic(fileToBeStored);

            } else {
                /* If the number of bytes is more than the maximum bytes,
                 * then save the image with 80% quality */
                byte[] bitmapData = convertBitmapToJPEG(bitmap, 80);

                // Keep on reducing the quality, until the bytes reaches the expected
                while (bitmapData.length >= maxFileSize) {
                    bitmapData = convertBitmapToJPEG(bitmap, 80);
                }

                File fileToBeStored = writingByteArrayToFile(imageFile, bitmapData);

                galleryAddPic(fileToBeStored);
            }
        }
    }

    /**
     * This method creates a placeholder/temporary file
     *
     * @return                  The temporary file (Size: 0 bytes)
     * @throws IOException      Input Output exceptions for handling file
     */
    private File createPlaceHolderFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "PicMyMedImage_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        // Creating temporary file
        File image = File.createTempFile(
                imageFileName,      /* prefix */
                FILE_FORMATE,       /* suffix */
                storageDir          /* directory */
        );

        return image;
    }

    /**
     * This method sends the intent to camera
     */
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            // File photoFile = null;
            try {
                photoFile = createPlaceHolderFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                ex.printStackTrace();
                Toast.makeText(this, "File was not created!", Toast.LENGTH_SHORT).show();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                /* If the file is not null, open camera activity */
                //System.out.println("File Size in Dispatch Intent: " + photoFile.length());
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    /**
     * This methods decodes the JPEG to Bitmap using the Layout dimensions.
     *
     * @param ImageFilePath     The path of the original image
     * @param imageViewWidth    The ImageView Layout width
     * @param imageViewHeight   The ImageView Layout Height
     * @return                  Scaled down Bitmap image
     */
    public Bitmap decodeImageFromFiles(String ImageFilePath, int imageViewWidth, int imageViewHeight){
        BitmapFactory.Options scalingOptions = new BitmapFactory.Options();

        // Do not load the Bitmap into memory
        scalingOptions.inJustDecodeBounds = true;

        // Passing the scalingOptions to decode the original file
        BitmapFactory.decodeFile(ImageFilePath, scalingOptions);

        // Determine how much to scale down the image
        int scaleFactor = 1;
        int originalImageWidth = scalingOptions.outWidth;
        int originalImageHeight = scalingOptions.outHeight;
        while (originalImageWidth / scaleFactor / 2 >= imageViewWidth
                && originalImageHeight / scaleFactor / 2 >= imageViewHeight) {

            scaleFactor *= 2;
        }

        // Decode with the scaling factor
        scalingOptions.inSampleSize = scaleFactor;

        // Loading onto memory in order to save the bitmap
        scalingOptions.inJustDecodeBounds = false;

        // adding the bitmap to the Bitmap typed ArrayList
        bitmaps.add(BitmapFactory.decodeFile(ImageFilePath, scalingOptions));

        return bitmaps.get(bitmaps.size()-1);
    }

    /**
     * This method converts Bitmap to JPEG and returns the byte array.
     *
     * @param bitmap                The bitmap file
     * @param quality               The quality at which the bitmap will be reduced (value should be an integer between 0-100
     * @return                      Returns the byte array
     */
    public byte[] convertBitmapToJPEG(Bitmap bitmap, int quality) {
        // Convert the bitmap to stream
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        // Compress bitmap into byteArrayOutputStream
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, byteArrayOutputStream);

        return byteArrayOutputStream.toByteArray();
    }

    /**
     * This method is writing the bitmap byte array information to the placeholder file.
     *
     * @param compressedImageFile   The temporary file where bitmap byte array will be written
     * @param bitmapData            The byte array of the bitmap file
     * @return                      File written with byte array information
     * @throws IOException          Input Output exceptions for handling file
     */
    public File writingByteArrayToFile (File compressedImageFile, byte[] bitmapData) throws IOException {
        // Write the byteArrayOutputStream into compressedImageFile
        // Log.d(TAG, "WritingByteArrayToFile: The size of the image file before writing = " + compressedImageFile.length());
        FileOutputStream fileOutputStream = new FileOutputStream(compressedImageFile);
        fileOutputStream.write(bitmapData);
        fileOutputStream.flush();

        fileOutputStream.close();

        // Log.d(TAG, "WritingByteArrayToFile: The size of the image file after writing = " + compressedImageFile.length());

        return compressedImageFile;
    }

    /**
     * This method stores the file to the private disk space for the application.
     * @param compressedImageFile     The compressed image file that will be stored
     */
    private void galleryAddPic(File compressedImageFile) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri contentUri = Uri.fromFile(compressedImageFile);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

}