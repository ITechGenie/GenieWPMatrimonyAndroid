package com.itechgenie.apps.geniewpmatrimony.dtos;

import com.itechgenie.apps.geniewpmatrimony.utilities.ITGUtility;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.IOException;

/**
 * Created by Prakash-hp on 22-05-2017.
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GwpmResponseDTO  {

    public String data ;
    public String message ;

    public <T> T getData(Class<T> responseType) throws JsonParseException, JsonMappingException, IOException {
        return (T) ITGUtility.jsonToObject(this.data, responseType);
    }

    public void setData(Object data) throws JsonProcessingException, IOException {
        this.data = ITGUtility.objectToJson(data) ;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("GwpmResponseDTO{");
        sb.append("data=").append(data);
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
