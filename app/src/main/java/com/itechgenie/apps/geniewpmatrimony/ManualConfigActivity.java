package com.itechgenie.apps.geniewpmatrimony;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ManualConfigActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_config);

        /* EditText btnQr = (EditText) findViewById(R.id.manualWebPageURLId);
        btnQr.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    Toast.makeText(getApplicationContext(), "got the focus", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getApplicationContext(), "lost the focus", Toast.LENGTH_LONG).show();
                }
            }
        }); */
    }

    public void onClickGo(View view) {

        EditText btnQr = (EditText) findViewById(R.id.manualWebPageURLId);
        String webUrl = btnQr.getText().toString() ;

        Log.d("ManualConfigActivity", "Obtained URL: " + webUrl ) ;
        if (webUrl != null ) {
            webUrl = webUrl.toLowerCase() ;
                if ( webUrl.startsWith("http") || webUrl.startsWith("https") ) {
                    if (!webUrl.endsWith("/")) {
                        webUrl += "/";
                    }
                    webUrl += "wp-admin/admin.php?page=gpwmp_oauth10a" ;
                    WebView webView = (WebView)findViewById(R.id.webView);
                    webView.setWebViewClient(new MyBrowser());
                    webView.getSettings().setLoadsImagesAutomatically(true);
                    webView.getSettings().setJavaScriptEnabled(true);
                    webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
                    webView.loadUrl(webUrl);
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid URL !!", Toast.LENGTH_LONG).show();
                }
        }

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
