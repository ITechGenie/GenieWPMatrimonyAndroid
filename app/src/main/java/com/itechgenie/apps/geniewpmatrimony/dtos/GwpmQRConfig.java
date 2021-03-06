package com.itechgenie.apps.geniewpmatrimony.dtos;

import java.io.Serializable;

import static com.itechgenie.apps.geniewpmatrimony.utilities.GwpmConstants.GWPM_FORWARD_SLASH;
import static com.itechgenie.apps.geniewpmatrimony.utilities.GwpmConstants.GWPM_IMAGE_URL_BASE;
import static com.itechgenie.apps.geniewpmatrimony.utilities.GwpmConstants.GWPM_REST_URL_BASE;

/**
 * Created by Prakash-hp on 14-05-2017.
 */

public class GwpmQRConfig implements Serializable {

    private String baseURL = null ;

    private String imgBaseUrl = null ;

    private String gwpm_oauth10a_client_key ;
    private String gwpm_oauth10a_client_secret ;
    private String gwpm_oauth10a_domain ;
    private String gwpm_oauth10a_api_url ;
    private String gwpm_oauth10a_token_request ;
    private String gwpm_oauth10a_token_access ;
    private String gwpm_oauth10a_token_authorize ;
    private String gwpm_oauth10a_callback_url ;
    private String gwpm_oauth10a_oauth_token ;
    private String gwpm_oauth10a_oauth_token_secret ;
    private String gwpm_oauth10a_oauth_verifier ;
    private String gwpm_oauth10a_api_version = "v1";

    public GwpmQRConfig() {
        super() ;
    }


    public String getBaseURL () {
        if (baseURL != null) {
            return baseURL ;
        } else {
            baseURL = getGwpm_oauth10a_domain() + GWPM_REST_URL_BASE
                    + GWPM_FORWARD_SLASH + getGwpm_oauth10a_api_version() ;
            return  baseURL ;
        }
    }

    public String getImageBaseURL () {
        if (imgBaseUrl != null) {
            return imgBaseUrl ;
        } else {
            imgBaseUrl = getGwpm_oauth10a_domain()
                    + GWPM_IMAGE_URL_BASE ;
            return  imgBaseUrl ;
        }
    }

    public String getGwpm_oauth10a_client_key() {
        return gwpm_oauth10a_client_key;
    }

    public void setGwpm_oauth10a_client_key(String gwpm_oauth10a_client_key) {
        this.gwpm_oauth10a_client_key = gwpm_oauth10a_client_key;
    }

    public String getGwpm_oauth10a_client_secret() {
        return gwpm_oauth10a_client_secret;
    }

    public void setGwpm_oauth10a_client_secret(String gwpm_oauth10a_client_secret) {
        this.gwpm_oauth10a_client_secret = gwpm_oauth10a_client_secret;
    }

    public String getGwpm_oauth10a_domain() {
        return gwpm_oauth10a_domain;
    }

    public void setGwpm_oauth10a_domain(String gwpm_oauth10a_domain) {
        this.gwpm_oauth10a_domain = gwpm_oauth10a_domain;
    }

    public String getGwpm_oauth10a_api_url() {
        return gwpm_oauth10a_api_url;
    }

    public void setGwpm_oauth10a_api_url(String gwpm_oauth10a_api_url) {
        this.gwpm_oauth10a_api_url = gwpm_oauth10a_api_url;
    }

    public String getGwpm_oauth10a_token_request() {
        return gwpm_oauth10a_token_request;
    }

    public void setGwpm_oauth10a_token_request(String gwpm_oauth10a_token_request) {
        this.gwpm_oauth10a_token_request = gwpm_oauth10a_token_request;
    }

    public String getGwpm_oauth10a_token_access() {
        return gwpm_oauth10a_token_access;
    }

    public void setGwpm_oauth10a_token_access(String gwpm_oauth10a_token_access) {
        this.gwpm_oauth10a_token_access = gwpm_oauth10a_token_access;
    }

    public String getGwpm_oauth10a_token_authorize() {
        return gwpm_oauth10a_token_authorize;
    }

    public void setGwpm_oauth10a_token_authorize(String gwpm_oauth10a_token_authorize) {
        this.gwpm_oauth10a_token_authorize = gwpm_oauth10a_token_authorize;
    }

    public String getGwpm_oauth10a_callback_url() {
        return gwpm_oauth10a_callback_url;
    }

    public void setGwpm_oauth10a_callback_url(String gwpm_oauth10a_callback_url) {
        this.gwpm_oauth10a_callback_url = gwpm_oauth10a_callback_url;
    }

    public String getGwpm_oauth10a_oauth_token() {
        return gwpm_oauth10a_oauth_token;
    }

    public void setGwpm_oauth10a_oauth_token(String gwpm_oauth10a_oauth_token) {
        this.gwpm_oauth10a_oauth_token = gwpm_oauth10a_oauth_token;
    }

    public String getGwpm_oauth10a_oauth_token_secret() {
        return gwpm_oauth10a_oauth_token_secret;
    }

    public void setGwpm_oauth10a_oauth_token_secret(String gwpm_oauth10a_oauth_token_secret) {
        this.gwpm_oauth10a_oauth_token_secret = gwpm_oauth10a_oauth_token_secret;
    }

    public String getGwpm_oauth10a_oauth_verifier() {
        return gwpm_oauth10a_oauth_verifier;
    }

    public void setGwpm_oauth10a_oauth_verifier(String gwpm_oauth10a_oauth_verifier) {
        this.gwpm_oauth10a_oauth_verifier = gwpm_oauth10a_oauth_verifier;
    }

    public String getGwpm_oauth10a_api_version() {
        return gwpm_oauth10a_api_version;
    }

    public void setGwpm_oauth10a_api_version(String gwpm_oauth10a_api_version) {
        this.gwpm_oauth10a_api_version = gwpm_oauth10a_api_version;
    }

    public String getHtmlString() {
        final StringBuffer sb = new StringBuffer("<h2>GwpmQRConfig</h2><hr>");
        sb.append("<p><b> gwpm_oauth10a_client_key: </b>").append(gwpm_oauth10a_client_key).append("<br />");
        sb.append("<b> gwpm_oauth10a_client_secret: </b>").append(gwpm_oauth10a_client_secret).append("<br />");
        sb.append("<b> gwpm_oauth10a_domain: </b>").append(gwpm_oauth10a_domain).append("<br />");
        sb.append("<b> gwpm_oauth10a_api_url: </b>").append(gwpm_oauth10a_api_url).append("<br />");
        sb.append("<b> gwpm_oauth10a_token_request: </b>").append(gwpm_oauth10a_token_request).append("<br />");
        sb.append("<b> gwpm_oauth10a_token_access: </b>").append(gwpm_oauth10a_token_access).append("<br />");
        sb.append("<b> gwpm_oauth10a_token_authorize: </b>").append(gwpm_oauth10a_token_authorize).append("<br />");
        sb.append("<b> gwpm_oauth10a_callback_url: </b>").append(gwpm_oauth10a_callback_url).append("<br />");
        sb.append("<b> gwpm_oauth10a_oauth_token: </b>").append(gwpm_oauth10a_oauth_token).append("<br />");
        sb.append("<b> gwpm_oauth10a_oauth_token_secret: </b>").append(gwpm_oauth10a_oauth_token_secret).append("<br />");
        sb.append("<b> gwpm_oauth10a_oauth_verifier: </b>").append(gwpm_oauth10a_oauth_verifier).append("</p>");
        sb.append("<b> gwpm_oauth10a_api_version: </b>").append(gwpm_oauth10a_api_version).append("</p>");
        sb.append("<hr>");
        return sb.toString();
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("GwpmQRConfig{");
        sb.append("gwpm_oauth10a_client_key='").append(gwpm_oauth10a_client_key).append('\'');
        sb.append(", gwpm_oauth10a_client_secret='").append(gwpm_oauth10a_client_secret).append('\'');
        sb.append(", gwpm_oauth10a_domain='").append(gwpm_oauth10a_domain).append('\'');
        sb.append(", gwpm_oauth10a_api_url='").append(gwpm_oauth10a_api_url).append('\'');
        sb.append(", gwpm_oauth10a_token_request='").append(gwpm_oauth10a_token_request).append('\'');
        sb.append(", gwpm_oauth10a_token_access='").append(gwpm_oauth10a_token_access).append('\'');
        sb.append(", gwpm_oauth10a_token_authorize='").append(gwpm_oauth10a_token_authorize).append('\'');
        sb.append(", gwpm_oauth10a_callback_url='").append(gwpm_oauth10a_callback_url).append('\'');
        sb.append(", gwpm_oauth10a_oauth_token='").append(gwpm_oauth10a_oauth_token).append('\'');
        sb.append(", gwpm_oauth10a_oauth_token_secret='").append(gwpm_oauth10a_oauth_token_secret).append('\'');
        sb.append(", gwpm_oauth10a_oauth_verifier='").append(gwpm_oauth10a_oauth_verifier).append('\'');
        sb.append(", gwpm_oauth10a_api_version='").append(gwpm_oauth10a_api_version).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
