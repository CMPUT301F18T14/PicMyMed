package com.example.picmymedcode.View;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.picmymedcode.R;

public class DisplayBodyLocationPhotoWthXActivity extends AppCompatActivity {

    DrawView drawView;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_bodylocation_x);

        drawView = (DrawView) findViewById(R.id.display_bodyLocation_x);
        drawView.doNothingOnTouch(); //set flag so that user can't interact with drawing canvas

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.aladdin);
        drawView.setBitmap(bitmap);

    }



}
