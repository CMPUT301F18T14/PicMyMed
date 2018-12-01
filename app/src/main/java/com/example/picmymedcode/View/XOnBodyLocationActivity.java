package com.example.picmymedcode.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.picmymedcode.R;

public class XOnBodyLocationActivity extends AppCompatActivity {

    private DrawView drawView;
    private Button saveButton;
    private Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_bodylocation_photo);

        //initialize view elements
        drawView = (DrawView) findViewById(R.id.bodyLocation_x);
        saveButton = (Button) findViewById(R.id.bodyLocation_saveButton);
        cancelButton = (Button) findViewById(R.id.bodyLocation_cancelButton);

    }
}
