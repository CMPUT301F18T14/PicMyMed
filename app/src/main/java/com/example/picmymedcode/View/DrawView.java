package com.example.picmymedcode.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.picmymedcode.R;

public class DrawView extends View {

    private static final String TAG = "DrawViewStuff";

    private Paint paint;
    private Canvas canvas;
    private Bitmap immutable;
    private Bitmap bitmap;
    boolean mark = false; //to see if there's already an x on the canvas

    //dimensions of the view
    int displayWidth;
    int displayHeight;

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupDrawing();
    }

    private void setupDrawing(){
        Log.d(TAG,"Reached setupDrawing");
        paint = new Paint();
        paint.setColor(Color.RED);
//        paint.setAntiAlias(true);
        paint.setTextSize(80F);
        immutable = BitmapFactory.decodeResource(getResources(),R.drawable.aladdin);
        bitmap = immutable.copy(Bitmap.Config.ARGB_8888, true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Log.d(TAG,"Reached onSizeChanged");
        displayWidth=w;
        displayHeight=h;
        super.onSizeChanged(w,h,oldw,oldh);
        canvas = new Canvas(bitmap);
    }

    @Override
    public void onDraw(Canvas canvas){
        Log.d(TAG,"Reached onDraw");
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap,0,0,paint);
    }

//    @Override
//    public final void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
//        int sizew = MeasureSpec.getSize(widthMeasureSpec);
//        int sizeh = MeasureSpec.getSize(heightMeasureSpec);
//
////        Log.d(TAG,"onMeasure: view w: "+sizew+"; view h: "+sizeh);
//        float ratio = Math.min(sizew/immutable.getWidth(),sizeh/immutable.getHeight());
//
//        int desiredWidth = (int) (immutable.getWidth()*ratio);
//        int desiredHeight = (int)(immutable.getHeight()*ratio);
//
//        setMeasuredDimension(desiredWidth,desiredHeight);
//    }

    @Override
    public  boolean onTouchEvent(MotionEvent event){
        Log.d(TAG,"Reached onTouchEvent");
        float x = event.getX();
        float y = event.getY();

      if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (!mark){
                canvas.drawText("X", x - 20, y + 32, paint);
                Log.d(TAG, "TOUCH x: " + x + " y: " + y + "  mark: false");
                mark=true;
                invalidate();
            } else {
//                bitmap = immutable.copy(Bitmap.Config.ARGB_8888, true);
//                bitmap = Bitmap.createScaledBitmap(bitmap,displayWidth,displayHeight,false);

                canvas = new Canvas(bitmap);
                canvas.drawBitmap(bitmap,0,0,paint);
                canvas.drawText("X", x - 20, y + 32, paint);
                Log.d(TAG, "TOUCH x: " + x + " y: " + y + "  mark: true");
                invalidate();
            }
        }
        return true;
    }
}
