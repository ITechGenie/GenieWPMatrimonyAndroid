package com.itechgenie.apps.geniewpmatrimony.dtos;

/**
 * Created by Prakash-hp on 28-05-2017.
 */

public class GwpmRequestDTO<T> {

    public T data ;
    public String message ;
    public String page ;
    public String size ;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("GwpmRequestDTO{");
        sb.append("data=").append(data);
        sb.append(", message='").append(message).append('\'');
        sb.append(", page='").append(page).append('\'');
        sb.append(", size='").append(size).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
