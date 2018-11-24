package com.example.QRcode;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cmput301f18t14.picmymed.R;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.Random;

public class GeneratorActivity extends AppCompatActivity {

    private final static int RANDOM_TEXT_LENGTH = 7;
    private String randomText;
    private Button generateQRcode;
    private ImageView showQRcode;

    private final static int REQUEST_CODE = 100;
    private final static int PERMISSION_REQUEST = 200;
    private Button scanButton;
    private TextView scannedQRTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generator);

        // randomText = (EditText) findViewById(R.id.random_text);
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
            @Override
            public void onClick(View v) {
                randomText = randomStringGenerator(RANDOM_TEXT_LENGTH);
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
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
    /* https://stackoverflow.com/questions/12116092/android-random-string-generator */
    private String randomStringGenerator(int maximum_length) {
        Random generator = new Random();
        StringBuilder randomStringBuilder = new StringBuilder();
        int randomLength = generator.nextInt(maximum_length);
        char tempChar;
        for (int i = 0; i < randomLength; i++){
            tempChar = (char) (generator.nextInt(96) + 32);
            randomStringBuilder.append(tempChar);
        }
        return randomStringBuilder.toString();
    }
}
