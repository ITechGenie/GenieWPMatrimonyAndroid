package com.itechgenie.apps.geniewpmatrimony.dtos;

import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Prakash-hp on 28-05-2017.
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GwpmSearchResultDTO implements Serializable
{

    @JsonProperty("ID")
    private String iD;
    @JsonProperty("user_login")
    private String userLogin;
    @JsonProperty("user_nicename")
    private String userNicename;
    @JsonProperty("user_email")
    private String userEmail;
    @JsonProperty("user_url")
    private String userUrl;
    @JsonProperty("user_registered")
    private String userRegistered;
    @JsonProperty("user_status")
    private String userStatus;
    @JsonProperty("display_name")
    private String displayName;
    @JsonProperty("gwpm_profile_photo")
    private GwpmProfilePhoto gwpmProfilePhoto;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -8826538412673134402L;

    private String thumbImageUrl ;

    public String getThumbImageUrl() {
        return thumbImageUrl;
    }

    public void setThumbImageUrl(String thumbImageUrl) {
        this.thumbImageUrl = thumbImageUrl;
    }

    @JsonProperty("ID")
    public String getID() {
        return iD;
    }

    @JsonProperty("ID")
    public void setID(String iD) {
        this.iD = iD;
    }

    @JsonProperty("user_login")
    public String getUserLogin() {
        return userLogin;
    }

    @JsonProperty("user_login")
    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    @JsonProperty("user_nicename")
    public String getUserNicename() {
        return userNicename;
    }

    @JsonProperty("user_nicename")
    public void setUserNicename(String userNicename) {
        this.userNicename = userNicename;
    }

    @JsonProperty("user_email")
    public String getUserEmail() {
        return userEmail;
    }

    @JsonProperty("user_email")
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @JsonProperty("user_url")
    public String getUserUrl() {
        return userUrl;
    }

    @JsonProperty("user_url")
    public void setUserUrl(String userUrl) {
        this.userUrl = userUrl;
    }

    @JsonProperty("user_registered")
    public String getUserRegistered() {
        return userRegistered;
    }

    @JsonProperty("user_registered")
    public void setUserRegistered(String userRegistered) {
        this.userRegistered = userRegistered;
    }

    @JsonProperty("user_status")
    public String getUserStatus() {
        return userStatus;
    }

    @JsonProperty("user_status")
    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    @JsonProperty("display_name")
    public String getDisplayName() {
        return displayName;
    }

    @JsonProperty("display_name")
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @JsonProperty("gwpm_profile_photo")
    public GwpmProfilePhoto getGwpmProfilePhoto() {
        return gwpmProfilePhoto;
    }

    @JsonProperty("gwpm_profile_photo")
    public void setGwpmProfilePhoto(GwpmProfilePhoto gwpmProfilePhoto) {
        this.gwpmProfilePhoto = gwpmProfilePhoto;
    }


    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("GwpmSearchResultDTO{");
        sb.append("iD='").append(iD).append('\'');
        sb.append(", userLogin='").append(userLogin).append('\'');
        sb.append(", userNicename='").append(userNicename).append('\'');
        sb.append(", userEmail='").append(userEmail).append('\'');
        sb.append(", userUrl='").append(userUrl).append('\'');
        sb.append(", userRegistered='").append(userRegistered).append('\'');
        sb.append(", userStatus='").append(userStatus).append('\'');
        sb.append(", displayName='").append(displayName).append('\'');
        sb.append(", gwpmProfilePhoto=").append(gwpmProfilePhoto);
        sb.append(", thumbImageUrl=").append(thumbImageUrl);
        sb.append(", additionalProperties=").append(additionalProperties);
        sb.append('}');
        return sb.toString();
    }

    public String getHtmlString() {
        final StringBuffer sb = new StringBuffer("<br /><h2>GwpmSearchResultDTO</h2><hr>");
        sb.append("<p><b>ID: </b> ").append(iD).append("<br />");
        sb.append(" <b> user_login: </b> ").append(userLogin).append("<br />");
        sb.append(" <b> user_nicename: </b> ").append(userNicename).append("<br />");
        sb.append(" <b> user_email: </b> ").append(userEmail).append("<br />");
        sb.append(" <b> user_url: </b> ").append(userUrl).append("<br />");
        sb.append(" <b> user_registered: </b> ").append(userRegistered).append("<br />");
        sb.append(" <b> user_status: </b> ").append(userStatus).append("<br />");
        sb.append(" <b> display_name: </b> ").append(displayName).append("<br />");
        sb.append(" <b> thumbImageUrl: </b> ").append((gwpmProfilePhoto!=null && gwpmProfilePhoto.getThumbName() != null ? "<img src='" + thumbImageUrl + "' alt='No Image' />" : "")).append("<br />");
        sb.append(" <b> View Thumb: </b> ").append((gwpmProfilePhoto!=null && gwpmProfilePhoto.getThumbName() != null ? "<a href src='" + thumbImageUrl + "'>Click Here</a>" : "" )).append("<br />");
        sb.append(" <b> gwpm_profile_photo: </b> ").append((gwpmProfilePhoto==null? "" : gwpmProfilePhoto.getHtmlString())).append("</p><br />");
        return sb.toString();
    }


}
