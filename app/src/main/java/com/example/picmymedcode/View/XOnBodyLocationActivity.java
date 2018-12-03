/*
 * XOnBodyLocationActivity
 *
 * 1.2
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

package com.example.picmymedcode.View;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;

import com.example.picmymedcode.Controller.PicMyMedApplication;
import com.example.picmymedcode.Controller.PicMyMedController;
import com.example.picmymedcode.Model.BodyLocation;
import com.example.picmymedcode.Model.Patient;
import com.example.picmymedcode.R;

/**
 * XOnBodyLocationActivity extends AppCompatActivity to allow the user to
 * select a location on a body photo
 *
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.2, 02/12/18
 * @since   1.1
 */
public class XOnBodyLocationActivity extends AppCompatActivity {

    private static final String TAG = "XOnBodyLocationActivity";

    LinearLayout linearLayout;
    private DrawView drawView;
    private Button saveButton;
    private Button cancelButton;
    //private Bitmap bitmap;
    float[] coordinates;
    private Patient user;

    /**
     * Sets the state
     *
     * @param savedInstanceState    Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_bodylocation_photo);
        //warning, photo is stretched into the view and fills the entire space

        user = (Patient) PicMyMedApplication.getLoggedInUser();

        //initialize view elements
        drawView = (DrawView) findViewById(R.id.bodyLocation_x);
        saveButton = (Button) findViewById(R.id.bodyLocation_saveButton);
        cancelButton = (Button) findViewById(R.id.bodyLocation_cancelButton);

//        drawView.setLayoutParams(new LayoutParams(300,100));

        Log.d("Activity X", "I am here ");

        String base64 = getIntent().getStringExtra("base64String");

        // Creating a file object
        //file = new File(filePath);

        // Decoding the file into a Bitmap
        // Bitmap bitmap = BitmapFactory.decodeFile(filePath);

        byte[] decodedString = Base64.decode(base64, Base64.DEFAULT);
        // Converting to Bitmap
        Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//        int height = displayMetrics.heightPixels-50;
//        int width = displayMetrics.widthPixels;
//
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

//        ImageView imageView = (ImageView) findViewById(R.id.blank_imageView);
//
//        int width = imageView.getWidth();
//        int height = imageView.getHeight();

        float ratio = Math.min(width/(float)bitmap.getWidth(),height/(float)bitmap.getHeight());
        Log.d(TAG,"height: "+bitmap.getHeight()+" width: "+bitmap.getWidth());
        Log.d(TAG,"ratio "+ratio+" height: "+height+" width: "+width);

        int desiredWidth = (int) (bitmap.getWidth()*ratio);
        int desiredHeight = (int)(bitmap.getHeight()*ratio);

        drawView.setLayoutParams(new LayoutParams(desiredWidth,desiredHeight));

        Bitmap b = bitmap.copy(Bitmap.Config.ARGB_8888,true);

        drawView.setBitmap(bitmap);

        saveButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Handles user clicking save
             *
             * @param v View
             */
            @Override
            public void onClick(View v) {
                coordinates = drawView.getCoordinates();

                int index = getIntent().getIntExtra("photoIndex", 0);
                Intent backToAddRecordActivity = new Intent();
                backToAddRecordActivity.putExtra("x", coordinates[0]);
                backToAddRecordActivity.putExtra("y", coordinates[1]);
                backToAddRecordActivity.putExtra("bodyLocationPhotoIndex", index);
                setResult(RESULT_OK, backToAddRecordActivity);
                finish();

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Handles user clicking cancel
             *
             * @param v View
             */
            @Override
            public void onClick(View v) {
                finish();
                //return to other activity
            }
        });

    }
}