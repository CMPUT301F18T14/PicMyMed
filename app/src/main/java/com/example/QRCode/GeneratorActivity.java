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

public class GeneratorActivity extends AppCompatActivity {

    private String randomText;
    private Button generateQRcode;
    private ImageView showQRcode;

    private final static int REQUEST_CODE = 100;
    private final static int PERMISSION_REQUEST = 200;
    private Button scanButton;
    private TextView scannedQRTextView;
    private User user;



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