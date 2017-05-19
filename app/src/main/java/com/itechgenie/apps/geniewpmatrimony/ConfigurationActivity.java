package com.itechgenie.apps.geniewpmatrimony;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class ConfigurationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configration);



    }

    public void onClickManual(View view) {

        Log.d("MainActivity", "Manual Config Button Clicked: " + view.getId() );
        Toast.makeText(ConfigurationActivity.this, "Manual Config Button Clicked: " + view.getId(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(ConfigurationActivity.this, ManualConfigActivity.class);
        startActivity(intent);

    }

    public void onClickConfigKey(View view) {

        Log.d("MainActivity", "Config Key Button Clicked: " + view.getId() );
        Toast.makeText(ConfigurationActivity.this, "Config Key Button Clicked: " + view.getId(), Toast.LENGTH_SHORT).show();

        //Intent intent = new Intent(MainActivity.this, ConfigurationActivity.class);
        //startActivity(intent);

        Intent intent = new Intent(ConfigurationActivity.this, CaptureQrActivity.class);
        startActivity(intent);

    }
}
