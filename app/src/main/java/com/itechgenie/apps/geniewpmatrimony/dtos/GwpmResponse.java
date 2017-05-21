package com.itechgenie.apps.geniewpmatrimony.dtos;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Created by Prakash-hp on 22-05-2017.
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class GwpmResponse<T> {

    public T data ;
    public String message ;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("GwpmResponse{");
        sb.append("data=").append(data);
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
