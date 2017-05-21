package com.itechgenie.apps.geniewpmatrimony;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.itechgenie.apps.geniewpmatrimony.dtos.GwpmQRConfig;
import com.itechgenie.apps.geniewpmatrimony.utilities.GwpmConstants;
import com.itechgenie.apps.geniewpmatrimony.utilities.ITGDbManager;
import com.itechgenie.apps.geniewpmatrimony.utilities.ITGUtility;

import java.io.IOException;


public class ConfigConfirmActivity extends AppCompatActivity implements GwpmConstants {

    private static final String LOGGER_NAME = "ConfigConfirmActivity";

    private ITGDbManager dbManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_confirm);

        String gwpmConfigJson = null ;

        if (this.getIntent().getExtras() != null)
            gwpmConfigJson = this.getIntent().getExtras().getString(GWPM_GLOBAL_CONFIG_JSON);

        Log.d(LOGGER_NAME, "Obtained the config here: " + gwpmConfigJson);

        dbManager = new ITGDbManager(this);
        dbManager.open();

        Log.d("ConfigConfirmActivity", "Obtained Json Value: " + gwpmConfigJson);

        try {

            if (gwpmConfigJson != null) {
                Log.d(LOGGER_NAME, "Storing value in DB: " );
                Long insertRes = dbManager.insert(GWPM_GLOBAL_CONFIG_JSON, gwpmConfigJson);
                Log.d("ConfigConfirmActivity", "Save Response: " + insertRes);
                displayConfigs(gwpmConfigJson) ;
            } else {
                Log.d(LOGGER_NAME, "Config is empty, Loading from DB") ;
                gwpmConfigJson = dbManager.fetchByKeyName(GWPM_GLOBAL_CONFIG_JSON) ;
                displayConfigs(gwpmConfigJson) ;
            }

        } catch (Exception e) {
            Log.e(LOGGER_NAME, "Exception in converting the object: " + e.getMessage(), e);
        }

    }

    private void displayConfigs(String gwpmConfigJson) throws IOException {
        TextView tv = (TextView) findViewById(R.id.gwpmConfigShowId);
        GwpmQRConfig gwpmQRConfig = (GwpmQRConfig) ITGUtility.jsonToObject(gwpmConfigJson, GwpmQRConfig.class);
        Log.d("ConfigConfirmActivity", "Obtained Object Value: " + gwpmQRConfig);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tv.setText(android.text.Html.fromHtml(gwpmQRConfig.getHtmlString(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            tv.setText(android.text.Html.fromHtml(gwpmQRConfig.getHtmlString()));
        }
    }

    public void onClickClearConfigs(View view) {

        Log.d("ConfigConfirmActivity", "Forwarding to Config Activity: ");
        Intent intent = new Intent(ConfigConfirmActivity.this, MainActivity.class);
        startActivity(intent);

    }

    public void onClickClearCurrentConfig(View view) {

        Log.d(LOGGER_NAME, "Delete Config Button Clicked: " + view.getId());

        int i = dbManager.deleteByKey(GWPM_GLOBAL_CONFIG_JSON);
        Log.d(LOGGER_NAME, "Delete key config value: " + i ) ;

        Toast.makeText(this,"Deleted Config !",Toast.LENGTH_LONG).show();

        Log.d(LOGGER_NAME, "Forwarding to Config Activity: ");
        Intent intent = new Intent(ConfigConfirmActivity.this, ConfigurationActivity.class);
        startActivity(intent);

    }

}