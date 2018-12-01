/*
 * PhotoEnlargementActivity
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

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.picmymedcode.Controller.PicMyMedApplication;
import com.example.picmymedcode.Controller.PicMyMedController;
import com.example.picmymedcode.Model.BodyLocationPhoto;
import com.example.picmymedcode.Model.Patient;
import com.example.picmymedcode.R;
import com.example.picmymedcode.View.AddProblemActivity;
import com.example.picmymedcode.View.BodyLocationPhotoManagerActivity;
import com.example.picmymedcode.View.ProblemActivity;
import com.example.picmymedcode.View.ProfileActivity;

import java.io.File;
import java.util.ArrayList;

/**
 * This class enlarges the photo selected from the GalleryActivity. It decodes the path again and
 * sets it in imageview layout. The "Delete" icon at the left of the toolbar deletes the selected
 * photo upon click.
 *
 * @author  Md Touhidul (Apu) Islam
 * @version 1.1, 16/11/18
 * @since   1.1
 */

public class PhotoEnlargementActivity extends AppCompatActivity {

    android.support.v7.widget.Toolbar toolbar;
    private ImageView imageView;

    private String filePath;

    private File file;

    private String base64;

    private byte[] decodedString;

    private int index;

    private Bitmap bitmap;

    private final static String TAG = "PhotoEnlargeActivity: ";

    private ArrayList<BodyLocationPhoto> bodyLocationPhotos;

    private final static int CAMERA_REQUEST_CODE = 99;

    private Dialog labellingDialog;

    /**
     * Method loads activity state
     *
     * @param savedInstanceState    Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_enlargement);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.enlargementToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getIntent().getStringExtra("photoLabel"));
//        getSupportActionBar().setDisplayShowTitleEnabled(false);

        final Patient patient = (Patient) PicMyMedApplication.getLoggedInUser();
        bodyLocationPhotos = patient.getBodyLocationPhotoList();


        imageView = (ImageView) findViewById(R.id.imageViewEnlarged);


        // Getting filePath sent from previous activity
        // filePath = getIntent().getStringExtra("filePath");
        base64 = getIntent().getStringExtra("base64String");

        index = getIntent().getIntExtra("index", 0);
        // Creating a file object
        //file = new File(filePath);

        // Decoding the file into a Bitmap
        // Bitmap bitmap = BitmapFactory.decodeFile(filePath);

        decodedString = Base64.decode(base64, Base64.DEFAULT);
        // Converting to Bitmap
        bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        // Setting the bitmap into imageView
        imageView.setImageBitmap(bitmap);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.enlargement_toolbar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.button_delete:
                PicMyMedController.removeBodyLocationPhoto(index);
                finish();
                break;
            case R.id.labelButton:
                labellingDialog = new Dialog(PhotoEnlargementActivity.this);
                labellingDialog.setContentView(R.layout.dialogbox_for_photo_labling);
                final EditText writeLabel = (EditText) labellingDialog.findViewById(R.id.label_EditText);
                Button saveLabel = (Button) labellingDialog.findViewById(R.id.save_label_button);

                writeLabel.setEnabled(true);
                saveLabel.setEnabled(true);

                labellingDialog.show();

                saveLabel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PicMyMedController.updateBodyLocationPhoto(index, writeLabel.getText().toString());
                        getSupportActionBar().setTitle(writeLabel.getText().toString());
                        labellingDialog.cancel();
                    }
                });
                break;
            case R.id.cameraButton:
                Intent photoIntent = new Intent(PhotoEnlargementActivity.this,PhotoIntentActivity.class);
                startActivityForResult(photoIntent, CAMERA_REQUEST_CODE);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
