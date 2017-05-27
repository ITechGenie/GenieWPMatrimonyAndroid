package com.itechgenie.apps.geniewpmatrimony.utilities;

import android.util.Log;

import com.itechgenie.apps.geniewpmatrimony.dtos.GwpmProfileDTO;
import com.itechgenie.apps.geniewpmatrimony.dtos.GwpmQRConfig;
import com.itechgenie.apps.geniewpmatrimony.dtos.GwpmSearchProfileDTO;

/**
 * Created by Prakash-hp on 21-05-2017.
 */

public class GwpmBusinessEntity extends ITGOAuth10aRestClient {

    final static String LOGGER_TAG = "GwpmBusinessEntity";

    private static GwpmBusinessEntity gwpmBusinessEntity = null ;

    private GwpmBusinessEntity() {
    }

    public static void setGwpmQRConfig (GwpmQRConfig gwpmQRConfigIn) {
        gwpmQRConfig = gwpmQRConfigIn;
    }

    public static GwpmBusinessEntity object() {
        if (gwpmQRConfig == null)
            throw new UnsupportedOperationException(LOGGER_TAG + " Object init failure due to empty Config !") ;

        if(gwpmBusinessEntity == null){
            gwpmBusinessEntity = new GwpmBusinessEntity();
        }
        return gwpmBusinessEntity ;
    }

    public GwpmProfileDTO getMyProfile() throws ITWException {
        return getProfile(null) ;
    }

    private String getBaseURL () {
        if (baseURL != null) {
            return baseURL ;
        } else {
            return  gwpmQRConfig.getGwpm_oauth10a_domain() + GWPM_REST_URL_BASE
                     + GWPM_FORWARD_SLASH + gwpmQRConfig.getGwpm_oauth10a_api_version() ;
        }
    }

    public GwpmProfileDTO getProfile(String profileId) throws ITWException {

        Log.d(LOGGER_TAG, "Loading profile: " + profileId ) ;

        String url = getBaseURL() ;

        if (profileId != null) {
            url += GWPM_REST_URL_PROFILE + GWPM_FORWARD_SLASH + profileId ;
        } else {
            url += GWPM_REST_URL_PROFILE + GWPM_ME ;
        }

        GwpmProfileDTO profileObject = null ;

        try {
            profileObject =  (GwpmProfileDTO) get(url, GwpmProfileDTO.class) ;
            Log.d(LOGGER_TAG, "Obtained profile object: " + profileObject) ;
        } catch (Exception e) {
            Log.e(LOGGER_TAG, "Exception occurred: " + e.getMessage() , e) ;
            throw new ITWException(LOGGER_TAG + "Exception occurred: " + e.getMessage(), e) ;
        }
        return profileObject ;
    }

    public GwpmProfileDTO searchProfile(GwpmSearchProfileDTO gwpmSearchProfileDTO) throws ITWException {

        Log.d(LOGGER_TAG, "Searching profile with: " + gwpmSearchProfileDTO ) ;

        String url = getBaseURL() + GWPM_REST_URL_SEARCH ;

        GwpmProfileDTO profileObject = null ;

        try {
            profileObject =  (GwpmProfileDTO) get(url, GwpmProfileDTO.class) ;
            Log.d(LOGGER_TAG, "Obtained profile object: " + profileObject) ;
        } catch (Exception e) {
            Log.e(LOGGER_TAG, "Exception occurred: " + e.getMessage() , e) ;
            throw new ITWException(LOGGER_TAG + "Exception occurred: " + e.getMessage(), e) ;
        }
        return profileObject ;
    }

}
