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
 * @version 1.2 12/02/18
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

    @Override
    public void onDraw(Canvas canvas){
        Log.d(TAG,"Reached onDraw");
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap,0,0,paint);
        canvas.drawText("X", xCoordinate - 20, yCoordinate + 32, paint);
    }

}