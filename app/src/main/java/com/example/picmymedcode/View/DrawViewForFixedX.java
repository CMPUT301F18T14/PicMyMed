package com.example.picmymedcode.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class DrawViewForFixedX extends View {

    private static final String TAG = "DrawViewStuff";

    private Paint paint;
    private Canvas canvas;
    private Bitmap nonCanvasBitmap;
    private Bitmap bitmap;
    boolean mark = false; //to see if there's already an x on the canvas

    //dimensions of the view
    int displayWidth;
    int displayHeight;

    //the coordinates of the touch event on the view
    float xCoordinate;
    float yCoordinate;

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

    public void setBitmap(Bitmap bitmap) {
        this.nonCanvasBitmap = bitmap;
        this.bitmap = bitmap;
    }

    public void setCoordinateXY(float x, float y) {
        this.xCoordinate = x;
        this.yCoordinate = y;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Log.d(TAG,"Reached onSizeChanged");
        displayWidth=w;
        displayHeight=h;
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

    public float[] getCoordinates(){
        float[] coordinates = new float[]{xCoordinate,yCoordinate};
        return coordinates;
    }

}