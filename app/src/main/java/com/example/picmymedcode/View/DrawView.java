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

    private Paint paint;
    private Path path;
    private Canvas canvas;
    private Bitmap bitmap;

//    public DrawView(Context context) {
//        super(context);
//    }

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupDrawing();
    }
    //
//    public DrawView(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        init(attrs, defStyleAttr);
//    }
//
    private void setupDrawing(){
        path = new Path();
        paint = new Paint();
        paint.setColor(Color.RED);
//        paint.setAntiAlias(true);
//        paint.setStyle(Paint.Style.STROKE);
        paint.setTextSize(40F);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w,h,oldw,oldh);
        bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.aladdin);
//        int inX = bitmap.getWidth();
//        int inY = bitmap.getHeight();
//        int scaleFactor = inY/h;
//        int newX = inX / scaleFactor;

        bitmap = bitmap.createScaledBitmap(bitmap,w,h,false);
        canvas = new Canvas(bitmap);
    }

    @Override
    public void onDraw(Canvas canvas){
//        super.onDraw(canvas);
        canvas.drawBitmap(bitmap,0,0,paint);
        canvas.drawPath(path,paint);

    }

    @Override
    public  boolean onTouchEvent(MotionEvent event){
        float x = event.getX();
        float y = event.getY();
//        Log.d("Label","x");

//        if(event.getAction()==MotionEvent.ACTION_DOWN){
//            path.moveTo(x,y);
//        }else if(event.getAction()==MotionEvent.ACTION_MOVE) {
//            path.moveTo(x,y);
//        }else if(event.getAction()==MotionEvent.ACTION_UP){
//        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
//                path.moveTo(x, y);
                Log.d("Label","x: "+x+" y: "+y);
                canvas.drawText("X", x-6,y+15,paint);
                break;
//            case MotionEvent.ACTION_MOVE:
//                path.lineTo(x, y);
//                break;
//            case MotionEvent.ACTION_UP:
//                canvas.drawPath(path, paint);
//                path.reset();
//                break;
//            default:
//                return false;
        }

        invalidate();
        return true;
    }
}
