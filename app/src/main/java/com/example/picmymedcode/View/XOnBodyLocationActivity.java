package com.example.picmymedcode.View;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.picmymedcode.R;

public class XOnBodyLocationActivity extends AppCompatActivity {

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

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.aladdin);
        drawView.setBitmap(bitmap);

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
