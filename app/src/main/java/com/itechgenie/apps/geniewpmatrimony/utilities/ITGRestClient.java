package com.itechgenie.apps.geniewpmatrimony.utilities;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import android.widget.Toast;

/**
 * Created by Prakash-hp on 15-04-2017.
 */

public class ITGRestClient {

    final static String TAG = "ITGRestClient" ;

    JSONObject data = new JSONObject();
    String url;
    String headerName;
    String headerValue;

    public ITGRestClient(String s){

        url = s;
    }


    public void addHeader(String name, String value){

        headerName = name;
        headerValue = value;

    }

    public void addParam(String key, String value){

        try {
            data.put(key, value);
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage(), e) ;
        }


    }

    public String executePost(){  // If you want to use post method to hit server

        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader(headerName, headerValue);
        HttpResponse response = null;
        String result = null;
        try {
            StringEntity entity = new StringEntity(data.toString(), HTTP.UTF_8);
            httpPost.setEntity(entity);
            response = httpClient.execute(httpPost);
            HttpEntity entity1 = response.getEntity();
            result = EntityUtils.toString(entity1);
            return result;
            //Toast.makeText(MainPage.this, result, Toast.LENGTH_LONG).show();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            Log.e(TAG, e.getMessage(), e) ;
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e) ;
        }
        return result;



    }

    public String executeGet(){ //If you want to use get method to hit server

        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(url);
        String result = null;
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        try {
            result = httpClient.execute(httpget, responseHandler);
        } catch (ClientProtocolException e) {
            Log.e(TAG, e.getMessage(), e) ;
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e) ;
        }

        return result;
    }
}
