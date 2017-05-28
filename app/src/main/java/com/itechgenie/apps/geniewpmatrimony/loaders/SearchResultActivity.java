package com.itechgenie.apps.geniewpmatrimony.loaders;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import com.itechgenie.apps.geniewpmatrimony.R;
import com.itechgenie.apps.geniewpmatrimony.dtos.GwpmQRConfig;
import com.itechgenie.apps.geniewpmatrimony.dtos.GwpmSearchResultDTO;
import com.itechgenie.apps.geniewpmatrimony.utilities.GwpmBusinessEntity;
import com.itechgenie.apps.geniewpmatrimony.utilities.ITGUtility;

import java.util.List;
import java.util.Map;

import static com.itechgenie.apps.geniewpmatrimony.utilities.GwpmConstants.GWPM_FORWARD_SLASH;
import static com.itechgenie.apps.geniewpmatrimony.utilities.GwpmConstants.GWPM_PROFILE_SEARCH_RESULTS;

public class SearchResultActivity extends AppCompatActivity {

    final static String LOGGER_TAG = "SearchResultActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        List<Map<String, Object>> json = null;

        /* if (this.getIntent().getExtras() != null)
            json = this.getIntent().getExtras().getString(GWPM_PROFILE_SEARCH_RESULTS);  */


        GwpmQRConfig gwpmQRConfig = GwpmBusinessEntity.getGwpmQRConfig();

        String baseImgBaseUrl = gwpmQRConfig.getImageBaseURL();

        Bundle b = this.getIntent().getExtras();
        if (b != null)
            json = (List) b.getSerializable(GWPM_PROFILE_SEARCH_RESULTS);

        Log.d(LOGGER_TAG, "Obtained response: - " + json);

        StringBuffer stringBuffer = new StringBuffer();
        try {
            for (Object profile : json) {
                GwpmSearchResultDTO gwpmProfileDTO = (GwpmSearchResultDTO) ITGUtility.castObject(profile, GwpmSearchResultDTO.class);
                if (gwpmProfileDTO.getGwpmProfilePhoto() != null && gwpmProfileDTO.getGwpmProfilePhoto().getThumbName() != null) {
                    String thumbUrl = baseImgBaseUrl + GWPM_FORWARD_SLASH + gwpmProfileDTO.getID()
                            + GWPM_FORWARD_SLASH + gwpmProfileDTO.getGwpmProfilePhoto().getThumbName() ;
                    Log.d(LOGGER_TAG, "Thumb URL: " + thumbUrl ) ;
                    gwpmProfileDTO.setThumbImageUrl(thumbUrl);
                }
                stringBuffer.append(gwpmProfileDTO.getHtmlString());
            }
        } catch (Exception e) {
            Log.e(LOGGER_TAG, "Exception occurred: " + e.getMessage(), e);
        }

        TextView tv = (TextView) findViewById(R.id.gwpmSearchResultShowId);

        if (("").equalsIgnoreCase(stringBuffer.toString())) {
            stringBuffer.append("<h2>No Profiles found !<h2/>");
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tv.setText(android.text.Html.fromHtml(stringBuffer.toString(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            tv.setText(android.text.Html.fromHtml(stringBuffer.toString()));
        }
    }
}
