package com.itechgenie.apps.geniewpmatrimony.dtos;

/**
 * Created by Prakash-hp on 28-05-2017.
 */

import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class GwpmProfilePhoto implements Serializable {

    @JsonProperty("name")
    private String name;
    @JsonProperty("type")
    private String type;
    @JsonProperty("error")
    private Integer error;
    @JsonProperty("size")
    private Integer size;
    @JsonProperty("thumb_name")
    private String thumbName;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 5094485624457134493L;

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("error")
    public Integer getError() {
        return error;
    }

    @JsonProperty("error")
    public void setError(Integer error) {
        this.error = error;
    }

    @JsonProperty("size")
    public Integer getSize() {
        return size;
    }

    @JsonProperty("size")
    public void setSize(Integer size) {
        this.size = size;
    }

    @JsonProperty("thumb_name")
    public String getThumbName() {
        return thumbName;
    }

    @JsonProperty("thumb_name")
    public void setThumbName(String thumbName) {
        this.thumbName = thumbName;
    }


    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public String getHtmlString() {
        final StringBuffer sb = new StringBuffer("<h2>GwpmProfilePhoto</h2><hr />");
        sb.append("<p><b>name: </b> ").append(name).append("<br />");
        sb.append(" <b>type: </b> ").append(type).append("<br />");
        sb.append(" <b>error=").append(error).append("<br />");
        sb.append(" <b>size=").append(size).append("<br />");
        sb.append(" <b>thumbName: </b> ").append(thumbName).append("<br />");
        sb.append(" <b>additionalProperties: </b>").append(additionalProperties).append("</p><br />");
        return sb.toString();
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("GwpmProfilePhoto{");
        sb.append("name='").append(name).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", error=").append(error);
        sb.append(", size=").append(size);
        sb.append(", thumbName='").append(thumbName).append('\'');
        sb.append(", additionalProperties=").append(additionalProperties);
        sb.append('}');
        return sb.toString();
    }
}
