package com.itechgenie.apps.geniewpmatrimony;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import com.itechgenie.apps.geniewpmatrimony.dtos.GwpmQRConfig;
import com.itechgenie.apps.geniewpmatrimony.utilities.GwpmConstants;
import com.itechgenie.apps.geniewpmatrimony.utilities.ITGDbManager;
import com.itechgenie.apps.geniewpmatrimony.utilities.ITGUtility;


public class ConfigConfirmActivity extends AppCompatActivity implements GwpmConstants {

    private static final String LOGGER_NAME = "ConfigConfirmActivity";

    private ITGDbManager dbManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_confirm);

        String gwpmConfigJson = this.getIntent().getExtras().getString(GWPM_GLOBAL_CONFIG_JSON);

        Log.d(LOGGER_NAME, "Obtained the config here: " + gwpmConfigJson);

        TextView tv = (TextView) findViewById(R.id.gwpmConfigShowId);

        dbManager = new ITGDbManager(this);
        dbManager.open();

        Log.d("ConfigConfirmActivity", "Obtained Json Value: " + gwpmConfigJson);

        try {

            GwpmQRConfig gwpmQRConfig = (GwpmQRConfig) ITGUtility.jsonToObject(gwpmConfigJson, GwpmQRConfig.class) ;
            Log.d("ConfigConfirmActivity", "Obtained Object Value: " + gwpmQRConfig);
            Long insertRes = dbManager.insert(GWPM_GLOBAL_CONFIG_JSON, gwpmConfigJson);
            Log.d("ConfigConfirmActivity", "Save Response: " + insertRes);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tv.setText(android.text.Html.fromHtml(gwpmQRConfig.getHtmlString(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            tv.setText(android.text.Html.fromHtml ( gwpmQRConfig.getHtmlString()));
        }


        } catch (Exception e) {
            Log.e(LOGGER_NAME, "Exception in converting the object: " + e.getMessage() , e) ;
        }

    }
}
