package com.itechgenie.apps.geniewpmatrimony;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class GwpmAAIntentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gwpm_aaintent);

        Intent intent = getIntent();
        String action = intent.getAction();
        Uri data = intent.getData();

        Log.d("GwpmAAIntentActivity", "Obtained Intent Activity call with Action: " + action + " - data: " + data) ;

    }
}
