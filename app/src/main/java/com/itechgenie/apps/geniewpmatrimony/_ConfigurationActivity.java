package com.itechgenie.apps.geniewpmatrimony;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

public class _ConfigurationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configration);

        Button btnQr = (Button) findViewById(R.id.captureQRBtn);
        btnQr.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(_ConfigurationActivity.this, CaptureQrActivity.class);
               startActivity(intent);
           }
       });

        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    ImageView myImageView = (ImageView) findViewById(R.id.imgview);
                    Bitmap myBitmap = BitmapFactory.decodeResource(
                            getApplicationContext().getResources(),
                            R.drawable.index);
                    myImageView.setImageBitmap(myBitmap);

                    Log.d("ConfigurationActivity", "Bitmap image is set, trying to load barcode value !") ;

                    BarcodeDetector detector =
                            new BarcodeDetector.Builder(getApplicationContext())
                                    .setBarcodeFormats(Barcode.DATA_MATRIX | Barcode.QR_CODE)
                                    .build();

                    Frame frame = new Frame.Builder().setBitmap(myBitmap).build();
                    SparseArray<Barcode> barcodes = detector.detect(frame);

                    Barcode thisCode = barcodes.valueAt(0);
                    TextView txtView = (TextView) findViewById(R.id.txtContent);
                    txtView.setText(thisCode.rawValue);

                    if(!detector.isOperational()){
                        txtView.setText("Could not set up the detector!");
                        return;
                    }
                } catch (Exception e) {
                    Log.e("ConfigurationActivity", "Exception in reading the Barcode: " + e.getMessage(), e) ;
                    e.printStackTrace();
                }


            }
        });


    }
}
