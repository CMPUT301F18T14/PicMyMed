/*
 * DrawViewForFixedX
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

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * DrawViewForFixedX extends View to load a bitmap and draw an X on the coordinates
 * provided.
 *
 * @author Eenna, Apu, Debra, Shawna, Ian, Umar
 * @version 1.2 02/12/18
 * @since 1.2
 */
public class DrawViewForFixedX extends View {

    private static final String TAG = "DrawViewStuff";

    private Paint paint;
    private Canvas canvas;
    private Bitmap bitmap;
    private float xCoordinate;
    private float yCoordinate;

    /**
     * Instantiates a new Draw view for fixed x.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public DrawViewForFixedX(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupDrawing();
    }

    private void setupDrawing(){
        Log.d(TAG,"Reached setupDrawing");
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(80F);
    }

    /**
     * Sets bitmap.
     *
     * @param bitmap the bitmap
     */
    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    /**
     * Sets coordinates for where the X will be displayed on the bitmap.
     *
     * @param x the x
     * @param y the y
     */
    public void setCoordinateXY(float x, float y) {
        this.xCoordinate = x;
        this.yCoordinate = y;
    }

    /**
     * Method sets sized change
     *
     * @param w     int
     * @param h     int
     * @param oldw  oldw
     * @param oldh  oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Log.d(TAG,"Reached onSizeChanged");
        super.onSizeChanged(w,h,oldw,oldh);
        if (bitmap == null){
            bitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
        }
        bitmap = Bitmap.createScaledBitmap(bitmap,w,h,false);
        canvas = new Canvas(bitmap);
    }

    /**
     * Method handles drawing on the canvas
     *
     * @param canvas    Canvas
     */
    @Override
    public void onDraw(Canvas canvas){
        Log.d(TAG,"Reached onDraw");
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap,0,0,paint);
        canvas.drawText("X", xCoordinate - 20, yCoordinate + 32, paint);
    }

}