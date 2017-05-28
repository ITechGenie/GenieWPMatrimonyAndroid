package com.itechgenie.apps.geniewpmatrimony.dtos;

import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GwpmProfileDTO {

    @JsonProperty("userId")
    private Integer userId;
    @JsonProperty("user_email")
    private String userEmail;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("gwpm_gender")
    private Object gwpmGender;
    @JsonProperty("gwpm_dob")
    private Object gwpmDob;
    @JsonProperty("description")
    private String description;
    @JsonProperty("gwpm_contact_no")
    private Object gwpmContactNo;
    @JsonProperty("gwpm_martial_status")
    private Object gwpmMartialStatus;
    @JsonProperty("gwpm_zodiac")
    private Object gwpmZodiac;
    @JsonProperty("gwpm_starsign")
    private Object gwpmStarsign;
    @JsonProperty("gwpm_sevvai_dosham")
    private Object gwpmSevvaiDosham;
    @JsonProperty("gwpm_caste")
    private Object gwpmCaste;
    @JsonProperty("gwpm_religion")
    private Object gwpmReligion;
    @JsonProperty("gwpm_physical")
    private Object gwpmPhysical;
    @JsonProperty("gwpm_address")
    private String gwpmAddress;
    @JsonProperty("gwpm_education")
    private String gwpmEducation;
    @JsonProperty("gwpm_work")
    private String gwpmWork;
    @JsonProperty("gwpm_tmasvs")
    private String gwpmTmasvs;
    @JsonProperty("gwpm_profile_photo")
    private Object gwpmProfilePhoto;
    @JsonProperty("containsDynamicField")
    private String containsDynamicField;
    @JsonProperty("dynamicFieldsValidation")
    private Object dynamicFieldsValidation;
    @JsonProperty("dynamicFields")
    private List<String> dynamicFields = null;
    @JsonProperty("user_login")
    private String userLogin;
    @JsonProperty("gender")
    private Object gender;
    @JsonProperty("gwpm_dynamic_field_count")
    private String gwpmDynamicFieldCount;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("userId")
    public Integer getUserId() {
        return userId;
    }

    @JsonProperty("userId")
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @JsonProperty("user_email")
    public String getUserEmail() {
        return userEmail;
    }

    @JsonProperty("user_email")
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @JsonProperty("first_name")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("first_name")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("last_name")
    public String getLastName() {
        return lastName;
    }

    @JsonProperty("last_name")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty("gwpm_gender")
    public Object getGwpmGender() {
        return gwpmGender;
    }

    @JsonProperty("gwpm_gender")
    public void setGwpmGender(Object gwpmGender) {
        this.gwpmGender = gwpmGender;
    }

    @JsonProperty("gwpm_dob")
    public Object getGwpmDob() {
        return gwpmDob;
    }

    @JsonProperty("gwpm_dob")
    public void setGwpmDob(Object gwpmDob) {
        this.gwpmDob = gwpmDob;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("gwpm_contact_no")
    public Object getGwpmContactNo() {
        return gwpmContactNo;
    }

    @JsonProperty("gwpm_contact_no")
    public void setGwpmContactNo(Object gwpmContactNo) {
        this.gwpmContactNo = gwpmContactNo;
    }

    @JsonProperty("gwpm_martial_status")
    public Object getGwpmMartialStatus() {
        return gwpmMartialStatus;
    }

    @JsonProperty("gwpm_martial_status")
    public void setGwpmMartialStatus(Object gwpmMartialStatus) {
        this.gwpmMartialStatus = gwpmMartialStatus;
    }

    @JsonProperty("gwpm_zodiac")
    public Object getGwpmZodiac() {
        return gwpmZodiac;
    }

    @JsonProperty("gwpm_zodiac")
    public void setGwpmZodiac(Object gwpmZodiac) {
        this.gwpmZodiac = gwpmZodiac;
    }

    @JsonProperty("gwpm_starsign")
    public Object getGwpmStarsign() {
        return gwpmStarsign;
    }

    @JsonProperty("gwpm_starsign")
    public void setGwpmStarsign(Object gwpmStarsign) {
        this.gwpmStarsign = gwpmStarsign;
    }

    @JsonProperty("gwpm_sevvai_dosham")
    public Object getGwpmSevvaiDosham() {
        return gwpmSevvaiDosham;
    }

    @JsonProperty("gwpm_sevvai_dosham")
    public void setGwpmSevvaiDosham(Object gwpmSevvaiDosham) {
        this.gwpmSevvaiDosham = gwpmSevvaiDosham;
    }

    @JsonProperty("gwpm_caste")
    public Object getGwpmCaste() {
        return gwpmCaste;
    }

    @JsonProperty("gwpm_caste")
    public void setGwpmCaste(Object gwpmCaste) {
        this.gwpmCaste = gwpmCaste;
    }

    @JsonProperty("gwpm_religion")
    public Object getGwpmReligion() {
        return gwpmReligion;
    }

    @JsonProperty("gwpm_religion")
    public void setGwpmReligion(Object gwpmReligion) {
        this.gwpmReligion = gwpmReligion;
    }

    @JsonProperty("gwpm_physical")
    public Object getGwpmPhysical() {
        return gwpmPhysical;
    }

    @JsonProperty("gwpm_physical")
    public void setGwpmPhysical(Object gwpmPhysical) {
        this.gwpmPhysical = gwpmPhysical;
    }

    @JsonProperty("gwpm_address")
    public String getGwpmAddress() {
        return gwpmAddress;
    }

    @JsonProperty("gwpm_address")
    public void setGwpmAddress(String gwpmAddress) {
        this.gwpmAddress = gwpmAddress;
    }

    @JsonProperty("gwpm_education")
    public String getGwpmEducation() {
        return gwpmEducation;
    }

    @JsonProperty("gwpm_education")
    public void setGwpmEducation(String gwpmEducation) {
        this.gwpmEducation = gwpmEducation;
    }

    @JsonProperty("gwpm_work")
    public String getGwpmWork() {
        return gwpmWork;
    }

    @JsonProperty("gwpm_work")
    public void setGwpmWork(String gwpmWork) {
        this.gwpmWork = gwpmWork;
    }

    @JsonProperty("gwpm_tmasvs")
    public String getGwpmTmasvs() {
        return gwpmTmasvs;
    }

    @JsonProperty("gwpm_tmasvs")
    public void setGwpmTmasvs(String gwpmTmasvs) {
        this.gwpmTmasvs = gwpmTmasvs;
    }

    @JsonProperty("gwpm_profile_photo")
    public Object getGwpmProfilePhoto() {
        return gwpmProfilePhoto;
    }

    @JsonProperty("gwpm_profile_photo")
    public void setGwpmProfilePhoto(Object gwpmProfilePhoto) {
        this.gwpmProfilePhoto = gwpmProfilePhoto;
    }

    @JsonProperty("containsDynamicField")
    public String getContainsDynamicField() {
        return containsDynamicField;
    }

    @JsonProperty("containsDynamicField")
    public void setContainsDynamicField(String containsDynamicField) {
        this.containsDynamicField = containsDynamicField;
    }

    @JsonProperty("dynamicFieldsValidation")
    public Object getDynamicFieldsValidation() {
        return dynamicFieldsValidation;
    }

    @JsonProperty("dynamicFieldsValidation")
    public void setDynamicFieldsValidation(Object dynamicFieldsValidation) {
        this.dynamicFieldsValidation = dynamicFieldsValidation;
    }

    @JsonProperty("dynamicFields")
    public List<String> getDynamicFields() {
        return dynamicFields;
    }

    @JsonProperty("dynamicFields")
    public void setDynamicFields(List<String> dynamicFields) {
        this.dynamicFields = dynamicFields;
    }

    @JsonProperty("user_login")
    public String getUserLogin() {
        return userLogin;
    }

    @JsonProperty("user_login")
    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    @JsonProperty("gender")
    public Object getGender() {
        return gender;
    }

    @JsonProperty("gender")
    public void setGender(Object gender) {
        this.gender = gender;
    }

    @JsonProperty("gwpm_dynamic_field_count")
    public String getGwpmDynamicFieldCount() {
        return gwpmDynamicFieldCount;
    }

    @JsonProperty("gwpm_dynamic_field_count")
    public void setGwpmDynamicFieldCount(String gwpmDynamicFieldCount) {
        this.gwpmDynamicFieldCount = gwpmDynamicFieldCount;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("GwpmProfileDTO [");
        if (userId != null) {
            builder.append("userId=");
            builder.append(userId);
            builder.append(", ");
        }
        if (userEmail != null) {
            builder.append("userEmail=");
            builder.append(userEmail);
            builder.append(", ");
        }
        if (firstName != null) {
            builder.append("firstName=");
            builder.append(firstName);
            builder.append(", ");
        }
        if (lastName != null) {
            builder.append("lastName=");
            builder.append(lastName);
            builder.append(", ");
        }
        if (gwpmGender != null) {
            builder.append("gwpmGender=");
            builder.append(gwpmGender);
            builder.append(", ");
        }
        if (gwpmDob != null) {
            builder.append("gwpmDob=");
            builder.append(gwpmDob);
            builder.append(", ");
        }
        if (description != null) {
            builder.append("description=");
            builder.append(description);
            builder.append(", ");
        }
        if (gwpmContactNo != null) {
            builder.append("gwpmContactNo=");
            builder.append(gwpmContactNo);
            builder.append(", ");
        }
        if (gwpmMartialStatus != null) {
            builder.append("gwpmMartialStatus=");
            builder.append(gwpmMartialStatus);
            builder.append(", ");
        }
        if (gwpmZodiac != null) {
            builder.append("gwpmZodiac=");
            builder.append(gwpmZodiac);
            builder.append(", ");
        }
        if (gwpmStarsign != null) {
            builder.append("gwpmStarsign=");
            builder.append(gwpmStarsign);
            builder.append(", ");
        }
        if (gwpmSevvaiDosham != null) {
            builder.append("gwpmSevvaiDosham=");
            builder.append(gwpmSevvaiDosham);
            builder.append(", ");
        }
        if (gwpmCaste != null) {
            builder.append("gwpmCaste=");
            builder.append(gwpmCaste);
            builder.append(", ");
        }
        if (gwpmReligion != null) {
            builder.append("gwpmReligion=");
            builder.append(gwpmReligion);
            builder.append(", ");
        }
        if (gwpmPhysical != null) {
            builder.append("gwpmPhysical=");
            builder.append(gwpmPhysical);
            builder.append(", ");
        }
        if (gwpmAddress != null) {
            builder.append("gwpmAddress=");
            builder.append(gwpmAddress);
            builder.append(", ");
        }
        if (gwpmEducation != null) {
            builder.append("gwpmEducation=");
            builder.append(gwpmEducation);
            builder.append(", ");
        }
        if (gwpmWork != null) {
            builder.append("gwpmWork=");
            builder.append(gwpmWork);
            builder.append(", ");
        }
        if (gwpmTmasvs != null) {
            builder.append("gwpmTmasvs=");
            builder.append(gwpmTmasvs);
            builder.append(", ");
        }
        if (gwpmProfilePhoto != null) {
            builder.append("gwpmProfilePhoto=");
            builder.append(gwpmProfilePhoto);
            builder.append(", ");
        }
        if (containsDynamicField != null) {
            builder.append("containsDynamicField=");
            builder.append(containsDynamicField);
            builder.append(", ");
        }
        if (dynamicFieldsValidation != null) {
            builder.append("dynamicFieldsValidation=");
            builder.append(dynamicFieldsValidation);
            builder.append(", ");
        }
        if (dynamicFields != null) {
            builder.append("dynamicFields=");
            builder.append(dynamicFields);
            builder.append(", ");
        }
        if (userLogin != null) {
            builder.append("userLogin=");
            builder.append(userLogin);
            builder.append(", ");
        }
        if (gender != null) {
            builder.append("gender=");
            builder.append(gender);
            builder.append(", ");
        }
        if (gwpmDynamicFieldCount != null) {
            builder.append("gwpmDynamicFieldCount=");
            builder.append(gwpmDynamicFieldCount);
            builder.append(", ");
        }
        if (additionalProperties != null) {
            builder.append("additionalProperties=");
            builder.append(additionalProperties);
        }
        builder.append("]");
        return builder.toString();
    }

    public String getHtmlString() {
        final StringBuffer sb = new StringBuffer("<h2>GwpmProfileDTO</h2><hr></br>");
        sb.append("<p><b> userId: </b>").append(userId).append("<br />");
        sb.append(" <b> userEmail: </b>").append(userEmail).append("<br />");
        sb.append(" <b> firstName: </b>").append(firstName).append("<br />");
        sb.append(" <b> lastName: </b>").append(lastName).append("<br />");
        sb.append(" <b> gwpmGender: </b>").append(gwpmGender).append("<br />");
        sb.append(" <b> gwpmDob: </b>").append(gwpmDob).append("<br />");
        sb.append(" <b> description: </b>").append(description).append("<br />");
        sb.append(" <b> gwpmContactNo: </b>").append(gwpmContactNo).append("<br />");
        sb.append(" <b> gwpmMartialStatus: </b>").append(gwpmMartialStatus).append("<br />");
        sb.append(" <b> gwpmZodiac: </b>").append(gwpmZodiac).append("<br />");
        sb.append(" <b> gwpmStarsign: </b>").append(gwpmStarsign).append("<br />");
        sb.append(" <b> gwpmSevvaiDosham: </b>").append(gwpmSevvaiDosham).append("<br />");
        sb.append(" <b> gwpmCaste: </b>").append(gwpmCaste).append("<br />");
        sb.append(" <b> gwpmReligion: </b>").append(gwpmReligion).append("<br />");
        sb.append(" <b> gwpmPhysical: </b>").append(gwpmPhysical).append("<br />");
        sb.append(" <b> gwpmAddress: </b>").append(gwpmAddress).append("<br />");
        sb.append(" <b> gwpmEducation: </b>").append(gwpmEducation).append("<br />");
        sb.append(" <b> gwpmWork: </b>").append(gwpmWork).append("<br />");
        sb.append(" <b> gwpmTmasvs: </b>").append(gwpmTmasvs).append("<br />");
        sb.append(" <b> gwpmProfilePhoto: </b>").append(gwpmProfilePhoto).append("<br />");
        sb.append(" <b> containsDynamicField: </b>").append(containsDynamicField).append("<br />");
        sb.append(" <b> dynamicFieldsValidation: </b>").append(dynamicFieldsValidation).append("<br />");
        sb.append(" <b> dynamicFields: </b>").append(dynamicFields).append("<br />");
        sb.append(" <b> userLogin: </b>").append(userLogin).append("<br />");
        sb.append(" <b> gender: </b>").append(gender).append("<br />");
        sb.append(" <b> gwpmDynamicFieldCount: </b>").append(gwpmDynamicFieldCount).append("<br />");
        sb.append(" <b> additionalProperties: </b>").append(additionalProperties).append("<br /></p>");
        return sb.toString();
    }

}
