package com.example.picmymedcode.View;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;

import com.example.picmymedcode.R;

public class XOnBodyLocationActivity extends AppCompatActivity {

    private static final String TAG = "XOnBodyLocationActivity";

    LinearLayout linearLayout;
    private DrawView drawView;
    private Button saveButton;
    private Button cancelButton;
    private Bitmap bitmap;
    float[] coordinates;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_bodylocation_photo);
        //warning, photo is stretched into the view and fills the entire space

        //initialize view elements
        drawView = (DrawView) findViewById(R.id.bodyLocation_x);
        saveButton = (Button) findViewById(R.id.bodyLocation_saveButton);
        cancelButton = (Button) findViewById(R.id.bodyLocation_cancelButton);

//        drawView.setLayoutParams(new LayoutParams(300,100));

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.aladdin);

//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//        int height = displayMetrics.heightPixels-50;
//        int width = displayMetrics.widthPixels;
//
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

//        ImageView imageView = (ImageView) findViewById(R.id.blank_imageView);
//
//        int width = imageView.getWidth();
//        int height = imageView.getHeight();

        float ratio = Math.min(width/(float)bitmap.getWidth(),height/(float)bitmap.getHeight());
        Log.d(TAG,"height: "+bitmap.getHeight()+" width: "+bitmap.getWidth());
        Log.d(TAG,"ratio "+ratio+" height: "+height+" width: "+width);

        int desiredWidth = (int) (bitmap.getWidth()*ratio);
        int desiredHeight = (int)(bitmap.getHeight()*ratio);

        drawView.setLayoutParams(new LayoutParams(desiredWidth,desiredHeight));

        Bitmap b = bitmap.copy(Bitmap.Config.ARGB_8888,true);

        drawView.setBitmap(b);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coordinates = drawView.getCoordinates();
                Toast.makeText(XOnBodyLocationActivity.this,"x: " + coordinates[0] +
                        " y: " + coordinates[1], Toast.LENGTH_SHORT).show();

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //return to other activity
            }
        });

    }
}
