package com.example.picmymedcode.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * DrawView extends View to load a bitmap and draw a single instance of
 * an X on the screen. The X is drawn on a touch event.
 *
 * @author Eenna, Debra, Shawna, Ian, Apu, Umar
 * @version 1.2 12/02/18
 * @since 1.2
 */
public class DrawView extends View {

    private static final String TAG = "DrawViewStuff";

    private Paint paint;
    private Canvas canvas;

    /* The bitmap that keeps the original bitmap. */
    private Bitmap nonCanvasBitmap;
    private Bitmap bitmap;

    /* The marker that documents if there's already an X drawn on the
     * canvas. False if no touch event occurred. True if a touch event occurred. */
    private boolean mark = false;

    /* The dimensions of the view. */
    private int displayWidth;
    private int displayHeight;

    /* The coordinateS of the touch event on the view. */
    private float xCoordinate;
    private float yCoordinate;

    /**
     * Instantiates a new Draw view.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public DrawView(Context context, AttributeSet attrs) {
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
     * Registers the bitmap to display in the canvas.
     *
     * @param bitmap the bitmap
     */
    public void setBitmap(Bitmap bitmap) {
        this.nonCanvasBitmap = bitmap;
        this.bitmap = bitmap;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Log.d(TAG,"Reached onSizeChanged");
        displayWidth=w;
        displayHeight=h;
        super.onSizeChanged(w,h,oldw,oldh);
        if (bitmap == null){
            //in the case that no bitmap was passed to the view
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
    }

    /**
     * Draws a red X on the bitmap. The coordinates are stored in variables.
     * The X is displayed only once on the bitmap.
     *
     * @param event the user's gesture event
     * @return true register the touch event
     */
    @Override
    public  boolean onTouchEvent(MotionEvent event){
        Log.d(TAG,"Reached onTouchEvent");
        xCoordinate = event.getX();
        yCoordinate = event.getY();

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (!mark) {
                canvas.drawText("X", xCoordinate - 20, yCoordinate + 32, paint);
                Log.d(TAG, "TOUCH x: " + xCoordinate + " y: " + yCoordinate + "  mark: false");
                mark = true;
                invalidate();
            } else {
                bitmap = nonCanvasBitmap.copy(Bitmap.Config.ARGB_8888, true);
                bitmap = Bitmap.createScaledBitmap(bitmap, displayWidth, displayHeight, false);
                canvas = new Canvas(bitmap);
                canvas.drawBitmap(bitmap, 0, 0, paint);
                canvas.drawText("X", xCoordinate - 20, yCoordinate + 32, paint);
                Log.d(TAG, "TOUCH x: " + xCoordinate + " y: " + yCoordinate + "  mark: true");
                invalidate();
            }
        }
        return true;
    }

    /**
     * Get coordinates of the touch. Returns 0,0 for x and y if no touch event occurred.
     *
     * @return the float [ ] the coordinates. X-coordinate at position 0, and y-coordinate at 1
     */
    public float[] getCoordinates(){
        return new float[]{xCoordinate,yCoordinate};
    }

}