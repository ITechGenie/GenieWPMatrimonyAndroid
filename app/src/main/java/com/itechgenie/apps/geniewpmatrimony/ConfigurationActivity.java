package com.itechgenie.apps.geniewpmatrimony;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

public class ConfigurationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configration);

        Button wViewBt = (Button) findViewById(R.id.openWebViewId);
        wViewBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebView webView = (WebView)findViewById(R.id.webView);
                // you can load an html code
                // webView.loadData("yourCode Html to load on the webView " , "text/html" , "utf-8");
                // you can load an URL
                webView.setWebViewClient(new MyBrowser());
                webView.getSettings().setLoadsImagesAutomatically(true);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
                webView.loadUrl("http://114.1.1.9/wp474/wp-login.php");
            }
        });

        Button btnQr = (Button) findViewById(R.id.captureQRBtn);
        btnQr.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(ConfigurationActivity.this, CaptureQrActivity.class);
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
    private class MyBrowser extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }

        //@Override
        public boolean  _shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
