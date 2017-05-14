package com.itechgenie.apps.geniewpmatrimony;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

public class CaptureQrActivity extends AppCompatActivity {

    public CameraSource cameraSource ;
    BarcodeDetector barcodeDetector ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_capture_qr);

        final SurfaceView cameraView = (SurfaceView)findViewById(R.id.camera_view);
       final TextView barcodeInfo = (TextView)findViewById(R.id.code_info);

        barcodeDetector =
                new BarcodeDetector.Builder(this)
                        .setBarcodeFormats(Barcode.QR_CODE)
                        .build();

        cameraSource = new CameraSource
                .Builder(this, barcodeDetector)
                .setAutoFocusEnabled(true)
                .setRequestedPreviewSize(620, 480)
                .build();

        cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    int permissionCheck = ContextCompat.checkSelfPermission(CaptureQrActivity.this,
                            Manifest.permission.CAMERA);
                    if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                        cameraSource.start(cameraView.getHolder());

                    } else {
                        ActivityCompat.requestPermissions(CaptureQrActivity.this,
                                new String[]{Manifest.permission.CAMERA},
                                PackageManager.PERMISSION_GRANTED);
                        Toast.makeText(CaptureQrActivity.this, "Permission Denied for Camera Access", Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException ie) {
                    Log.e("CAMERA SOURCE", ie.getMessage());
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {
            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();

                Log.d("CaptureQrActivity", "Receiving something: !" + barcodes.size()) ;

                if (barcodes.size() != 0) {
                    String qrText = barcodes.valueAt(0).displayValue;
                    Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    v.vibrate(100);
                    Log.d("CaptureQrActivity", "Obtained Test: " + qrText) ;
                    barcodeInfo.post(new Runnable() {    // Use the post method of the TextView
                        public void run() {
                            barcodeInfo.setText(    // Update the TextView
                                    barcodes.valueAt(0).displayValue
                            );

                        }
                    });
                    switchToQrProcessor (qrText) ;
                }
            }
        });



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if(requestCode == PackageManager.PERMISSION_GRANTED){

            //If permission is granted
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                //Displaying a toast
                Toast.makeText(this,"Permission granted now you can read the storage",Toast.LENGTH_LONG).show();
            }else{
                //Displaying another toast if permission is not granted
                Toast.makeText(this,"Oops you just denied the permission",Toast.LENGTH_LONG).show();
            }
        }
    }

    public void switchToQrProcessor(String qrValue) {

        // barcodeDetector.release();
      //  View qrCaptureId =  findViewById(R.id.qrCaptureLayoutId);
      //  View qrProcessorId =  findViewById(R.id.finalProcessorLayoutId);

        View qrCaptureId =  findViewById(R.id.camera_view);
        View qrProcessorId =  findViewById(R.id.code_info);
        qrCaptureId.setVisibility(View.GONE);
        qrProcessorId.setVisibility(View.VISIBLE);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (cameraSource != null)
        cameraSource.release();
        if (barcodeDetector != null)
        barcodeDetector.release();
    }

}
