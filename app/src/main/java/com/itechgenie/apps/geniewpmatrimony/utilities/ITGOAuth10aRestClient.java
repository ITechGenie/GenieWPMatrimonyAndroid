package com.itechgenie.apps.geniewpmatrimony.utilities;

import android.util.Log;

import com.google.api.client.auth.oauth.OAuthHmacSigner;
import com.google.api.client.auth.oauth.OAuthParameters;
import com.google.api.client.http.GenericUrl;
import com.itechgenie.apps.geniewpmatrimony.dtos.GwpmQRConfig;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Prakash-hp on 22-05-2017.
 */

public abstract class ITGOAuth10aRestClient implements GwpmConstants {

    final static String LOGGER_TAG = "ITGRestClient";

    static GwpmQRConfig gwpmQRConfig = null ;

    String baseURL = null ;

    public Object get(String url, Class className) throws IOException, GeneralSecurityException {

        OAuthHmacSigner signer = new OAuthHmacSigner();
        signer.clientSharedSecret = gwpmQRConfig.getGwpm_oauth10a_client_secret();
        signer.tokenSharedSecret = gwpmQRConfig.getGwpm_oauth10a_oauth_token_secret();

        OAuthParameters parameters = new OAuthParameters();

        parameters.consumerKey = gwpmQRConfig.getGwpm_oauth10a_client_key();
        parameters.nonce = (getNonce());
        parameters.signatureMethod = GWPM_HMAC_SHA1 ;
        parameters.timestamp = ((System.currentTimeMillis() / 1000) + "");
        parameters.token = gwpmQRConfig.getGwpm_oauth10a_oauth_token() ;
        parameters.version = GWPM_SIGN_VERSION ;
        parameters.signer = signer;

        GenericUrl arg1 = new GenericUrl(url);

        parameters.computeSignature("GET", arg1);

        Map<String, Object> headers = new HashMap<String, Object>();

        String authHeaders = parameters.getAuthorizationHeader() ;

        Log.d(LOGGER_TAG, "Auth Headers: " + authHeaders);

        headers.put("Accept", "application/json");
        headers.put("Content-type", "application/json");
        headers.put("Authorization", authHeaders);

        return  ITGRestClient.get(url, headers, null, className);
    }

    public static String getNonce() {
        String nonce ;
        String nonceString = "" + (System.currentTimeMillis() / 1000) ;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(nonceString.getBytes());
            nonce = String.valueOf(md.digest() ) ;
        } catch (Exception e) {
            nonce = nonceString ;
        }
        return nonce ;
    }

}
