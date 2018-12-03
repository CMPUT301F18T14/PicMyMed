/*
 * XFixedPhotoActivity
 *
 * 1.2
 *
 * November 16, 2018
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

import com.example.picmymedcode.R;
/**
 * XFixedPhotoActivity extends AppCompatActivity to handle user
 * putting an x on the map
 *
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.2, 02/12/18
 * @since   1.1
 */
public class XFixedPhotoActivity extends AppCompatActivity {

    private static final String TAG = "XFixedPhotoActivity: ";

    private DrawViewForFixedX drawViewForFixedX;

    //private Bitmap bitmap;
    float[] coordinates;

    /**
     * Sets the state
     *
     * @param savedInstanceState    Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xfixed_photo);
        //warning, photo is stretched into the view and fills the entire space

        //initialize view elements
        drawViewForFixedX = (DrawViewForFixedX) findViewById(R.id.bodyLocation_x);

        float xCoordinate = getIntent().getFloatExtra("x", 0);
        float yCoordinate = getIntent().getFloatExtra("y", 0);
        //float xCoordinate = getIntent().getFloatExtra("xCoordinate", 0);

        //float yCoordinate = getIntent().getFloatExtra("yCoordinate", 0);

        String base64 = getIntent().getStringExtra("base64String");

        byte[] decodedString = Base64.decode(base64, Base64.DEFAULT);
        // Converting to Bitmap
        Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        float ratio = Math.min(width/(float)bitmap.getWidth(),height/(float)bitmap.getHeight());
        Log.d(TAG,"height: "+bitmap.getHeight()+" width: "+bitmap.getWidth());
        Log.d(TAG,"ratio "+ratio+" height: "+height+" width: "+width);

        int desiredWidth = (int) (bitmap.getWidth()*ratio);
        int desiredHeight = (int)(bitmap.getHeight()*ratio);

        drawViewForFixedX.setLayoutParams(new LayoutParams(desiredWidth,desiredHeight));

        //Bitmap b = bitmap.copy(Bitmap.Config.ARGB_8888,true);

        drawViewForFixedX.setBitmap(bitmap);

        drawViewForFixedX.setCoordinateXY(xCoordinate, yCoordinate);



    }
}