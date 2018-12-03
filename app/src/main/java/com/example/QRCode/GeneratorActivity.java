/*
 * GeneratorActivity
 *
 * 1.2
 *
 * November 16, 2018
 *
 * Copyright (C) 2018 CMPUT301F18T14. All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.example.QRCode;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.picmymedcode.Controller.PicMyMedApplication;
import com.example.picmymedcode.Model.User;
import com.example.picmymedcode.R;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

/**
 * GeneratorActivity extends AppCompatActivity to handle scanning a QR code
 *
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.2, 02/12/18
 * @since   1.1
 */
public class GeneratorActivity extends AppCompatActivity {

    private String randomText;
    private Button generateQRcode;
    private ImageView showQRcode;

    private final static int REQUEST_CODE = 100;
    private final static int PERMISSION_REQUEST = 200;
    private Button scanButton;
    private TextView scannedQRTextView;
    private User user;

    /**
     * Creates the state
     *
     * @param savedInstanceState    Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generator);

        user = PicMyMedApplication.getLoggedInUser();

        generateQRcode = (Button) findViewById(R.id.generate_button);
        showQRcode = (ImageView) findViewById(R.id.qr_imageview);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, PERMISSION_REQUEST);
        }

        scanButton = (Button) findViewById(R.id.scan_button);

        scannedQRTextView = (TextView) findViewById(R.id.scanned_textview);

        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent scannerIntent = new Intent(GeneratorActivity.this, ScannerActivity.class);
                startActivityForResult(scannerIntent, REQUEST_CODE);
            }
        });


        generateQRcode.setOnClickListener(new View.OnClickListener() {
            /**
             * Handles clicking generate QR code button
             *
             * @param v View
             */
            @Override
            public void onClick(View v) {
                randomText = user.getRandomUserID();
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                scannedQRTextView.setText(randomText);
                try {
                    BitMatrix bitMatrix = multiFormatWriter.encode(randomText, BarcodeFormat.QR_CODE, 200, 200);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                    showQRcode.setImageBitmap(bitmap);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        });



    }

    /**
     * Handles Activity permissions
     *
     * @param requestCode   int
     * @param resultCode    int
     * @param data          Intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                final Barcode barcode = data.getParcelableExtra("barcode");
                scannedQRTextView.post(new Runnable() {
                    @Override
                    public void run() {
                        scannedQRTextView.setText(barcode.displayValue);
                    }
                });
            }
        }
    }
}