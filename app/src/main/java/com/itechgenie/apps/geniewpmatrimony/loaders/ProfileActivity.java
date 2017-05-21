package com.itechgenie.apps.geniewpmatrimony.loaders;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.itechgenie.apps.geniewpmatrimony.R;
import com.itechgenie.apps.geniewpmatrimony.tasks.GwpmProfileFetchTask;

public class ProfileActivity extends AppCompatActivity  implements GwpmProfileFetchTask.callBack {

    final static String LOGGER_TAG = "ProfileActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        new GwpmProfileFetchTask(ProfileActivity.this).execute();

    }

    @Override
    public void returnText(Object value) {
        Log.d(LOGGER_TAG, "Obtained response: " + value) ;
        TextView tv = (TextView) findViewById(R.id.gwpmProfileShowId);

        tv.setText( String.valueOf(value )) ;

    }

}
