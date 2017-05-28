package com.itechgenie.apps.geniewpmatrimony.utilities;

import android.util.Log;

import com.itechgenie.apps.geniewpmatrimony.dtos.GwpmProfileDTO;
import com.itechgenie.apps.geniewpmatrimony.dtos.GwpmQRConfig;
import com.itechgenie.apps.geniewpmatrimony.dtos.GwpmRequestDTO;
import com.itechgenie.apps.geniewpmatrimony.dtos.GwpmResponseDTO;
import com.itechgenie.apps.geniewpmatrimony.dtos.GwpmSearchProfileDTO;
import com.itechgenie.apps.geniewpmatrimony.dtos.GwpmSearchResultDTO;

import java.util.ArrayList;
import java.util.List;

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

    public static GwpmQRConfig getGwpmQRConfig () {
        return gwpmQRConfig ;
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

    public GwpmProfileDTO getProfile(String profileId) throws ITWException {

        Log.d(LOGGER_TAG, "Loading profile: " + profileId ) ;

        String url = gwpmQRConfig.getBaseURL() ;

        if (profileId != null) {
            url += GWPM_REST_URL_PROFILE + GWPM_FORWARD_SLASH + profileId ;
        } else {
            url += GWPM_REST_URL_PROFILE + GWPM_ME ;
        }

        GwpmProfileDTO gwpmProfileDTO = null ;

        try {
            GwpmResponseDTO responseObject =  (GwpmResponseDTO) get(url, GwpmResponseDTO.class) ;
            Log.d(LOGGER_TAG, "Obtained profile object: " + responseObject) ;
            gwpmProfileDTO = responseObject.getData(GwpmProfileDTO.class);
        } catch (Exception e) {
            Log.e(LOGGER_TAG, "Exception occurred: " + e.getMessage() , e) ;
            throw new ITWException(LOGGER_TAG + "Exception occurred: " + e.getMessage(), e) ;
        }

        return gwpmProfileDTO ;
    }

    public List<GwpmSearchResultDTO> searchProfile(GwpmSearchProfileDTO gwpmSearchProfileDTO) throws ITWException {

        Log.d(LOGGER_TAG, "Searching profile with: " + gwpmSearchProfileDTO ) ;

        String url = gwpmQRConfig.getBaseURL() + GWPM_REST_URL_SEARCH ;

        List<GwpmSearchResultDTO> profileLists = null ;

        try {

            GwpmRequestDTO<GwpmSearchProfileDTO> gwpmRequestDTO = new GwpmRequestDTO<GwpmSearchProfileDTO>() ;
            gwpmRequestDTO.setData(gwpmSearchProfileDTO);

            GwpmResponseDTO responseObject =  (GwpmResponseDTO) post(url, gwpmRequestDTO, GwpmResponseDTO.class) ;
            Log.d(LOGGER_TAG, "Obtained profile object: " + responseObject) ;
            profileLists = responseObject.getData((new ArrayList<GwpmProfileDTO>()).getClass());
        } catch (Exception e) {
            Log.e(LOGGER_TAG, "Exception occurred: " + e.getMessage() , e) ;
            throw new ITWException(LOGGER_TAG + "Exception occurred: " + e.getMessage(), e) ;
        }
        return profileLists ;
    }

}
