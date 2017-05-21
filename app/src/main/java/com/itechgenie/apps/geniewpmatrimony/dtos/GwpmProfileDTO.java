package com.itechgenie.apps.geniewpmatrimony.dtos;

import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
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
    private Boolean gwpmAddress;
    @JsonProperty("gwpm_education")
    private Boolean gwpmEducation;
    @JsonProperty("gwpm_work")
    private Boolean gwpmWork;
    @JsonProperty("gwpm_tmasvs")
    private Boolean gwpmTmasvs;
    @JsonProperty("gwpm_profile_photo")
    private Boolean gwpmProfilePhoto;
    @JsonProperty("containsDynamicField")
    private Boolean containsDynamicField;
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
    public Boolean getGwpmAddress() {
        return gwpmAddress;
    }

    @JsonProperty("gwpm_address")
    public void setGwpmAddress(Boolean gwpmAddress) {
        this.gwpmAddress = gwpmAddress;
    }

    @JsonProperty("gwpm_education")
    public Boolean getGwpmEducation() {
        return gwpmEducation;
    }

    @JsonProperty("gwpm_education")
    public void setGwpmEducation(Boolean gwpmEducation) {
        this.gwpmEducation = gwpmEducation;
    }

    @JsonProperty("gwpm_work")
    public Boolean getGwpmWork() {
        return gwpmWork;
    }

    @JsonProperty("gwpm_work")
    public void setGwpmWork(Boolean gwpmWork) {
        this.gwpmWork = gwpmWork;
    }

    @JsonProperty("gwpm_tmasvs")
    public Boolean getGwpmTmasvs() {
        return gwpmTmasvs;
    }

    @JsonProperty("gwpm_tmasvs")
    public void setGwpmTmasvs(Boolean gwpmTmasvs) {
        this.gwpmTmasvs = gwpmTmasvs;
    }

    @JsonProperty("gwpm_profile_photo")
    public Boolean getGwpmProfilePhoto() {
        return gwpmProfilePhoto;
    }

    @JsonProperty("gwpm_profile_photo")
    public void setGwpmProfilePhoto(Boolean gwpmProfilePhoto) {
        this.gwpmProfilePhoto = gwpmProfilePhoto;
    }

    @JsonProperty("containsDynamicField")
    public Boolean getContainsDynamicField() {
        return containsDynamicField;
    }

    @JsonProperty("containsDynamicField")
    public void setContainsDynamicField(Boolean containsDynamicField) {
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

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("GwpmProfileDTO{");
        sb.append("userId=").append(userId);
        sb.append(", userEmail='").append(userEmail).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", gwpmGender=").append(gwpmGender);
        sb.append(", gwpmDob=").append(gwpmDob);
        sb.append(", description='").append(description).append('\'');
        sb.append(", gwpmContactNo=").append(gwpmContactNo);
        sb.append(", gwpmMartialStatus=").append(gwpmMartialStatus);
        sb.append(", gwpmZodiac=").append(gwpmZodiac);
        sb.append(", gwpmStarsign=").append(gwpmStarsign);
        sb.append(", gwpmSevvaiDosham=").append(gwpmSevvaiDosham);
        sb.append(", gwpmCaste=").append(gwpmCaste);
        sb.append(", gwpmReligion=").append(gwpmReligion);
        sb.append(", gwpmPhysical=").append(gwpmPhysical);
        sb.append(", gwpmAddress=").append(gwpmAddress);
        sb.append(", gwpmEducation=").append(gwpmEducation);
        sb.append(", gwpmWork=").append(gwpmWork);
        sb.append(", gwpmTmasvs=").append(gwpmTmasvs);
        sb.append(", gwpmProfilePhoto=").append(gwpmProfilePhoto);
        sb.append(", containsDynamicField=").append(containsDynamicField);
        sb.append(", dynamicFieldsValidation=").append(dynamicFieldsValidation);
        sb.append(", dynamicFields=").append(dynamicFields);
        sb.append(", userLogin='").append(userLogin).append('\'');
        sb.append(", gender=").append(gender);
        sb.append(", gwpmDynamicFieldCount='").append(gwpmDynamicFieldCount).append('\'');
        sb.append(", additionalProperties=").append(additionalProperties);
        sb.append('}');
        return sb.toString();
    }
}
