package com.itechgenie.apps.geniewpmatrimony.loaders;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import com.itechgenie.apps.geniewpmatrimony.R;
import com.itechgenie.apps.geniewpmatrimony.dtos.GwpmProfileDTO;
import com.itechgenie.apps.geniewpmatrimony.tasks.GwpmProfileFetchTask;

import static com.itechgenie.apps.geniewpmatrimony.utilities.GwpmConstants.GWPM_PROFILE_LOAD_ID;

public class ProfileActivity extends AppCompatActivity  implements GwpmProfileFetchTask.callBack {

    final static String LOGGER_TAG = "ProfileActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        String profileId = null ;
        Bundle b = this.getIntent().getExtras();
        if (b != null)
            profileId = b.getString(GWPM_PROFILE_LOAD_ID);

        new GwpmProfileFetchTask(ProfileActivity.this).execute(profileId);

    }

    @Override
    public void returnText(Object value) {
        Log.d(LOGGER_TAG, "Obtained response: " + value) ;
        TextView tv = (TextView) findViewById(R.id.gwpmProfileShowId);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tv.setText(android.text.Html.fromHtml(((GwpmProfileDTO)value).getHtmlString(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            tv.setText(android.text.Html.fromHtml(((GwpmProfileDTO)value).getHtmlString()));
        }

    }

}
