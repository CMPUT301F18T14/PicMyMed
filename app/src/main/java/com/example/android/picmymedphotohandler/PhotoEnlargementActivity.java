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

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.mukha.picmymedcode.R;

import java.io.File;

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

    private ImageView imageView;

    private ImageButton deleteButton;

    private String filePath;

    private File file;

    private final static String TAG = "PhotoEnlargeActivity: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_enlargement);

        // Getting filePath sent from previous activity
        filePath = getIntent().getStringExtra("filePath");

        // Creating a file object
        file = new File(filePath);

        // Decoding the file into a Bitmap
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);

        deleteButton = (ImageButton) findViewById(R.id.button_delete);

        imageView = (ImageView) findViewById(R.id.imageViewEnlarged);

        // Setting the bitmap into imageView
        imageView.setImageBitmap(bitmap);

        // Deleting photos upon pressing delete button
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (file.exists()){
                    // Deletes the file
                    file.delete();

                    // Finishes the activity and returns to previous activity
                    finish();
                }
            }
        });
    }
}
