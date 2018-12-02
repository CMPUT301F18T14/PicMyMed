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

public class XFixedPhotoActivity extends AppCompatActivity {

    private static final String TAG = "XFixedPhotoActivity: ";

    private DrawViewForFixedX drawViewForFixedX;

    //private Bitmap bitmap;
    float[] coordinates;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xfixed_photo);
        //warning, photo is stretched into the view and fills the entire space

        //initialize view elements
        drawViewForFixedX = (DrawViewForFixedX) findViewById(R.id.bodyLocation_x);

        float xCoordinate = 500;
        float yCoordinate = 800;
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