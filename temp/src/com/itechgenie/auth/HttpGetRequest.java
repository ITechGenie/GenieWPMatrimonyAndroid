package com.itechgenie.auth;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.client.methods.*; //HttpHead, HttpPut, HttpGet, etc...
import org.apache.http.util.EntityUtils;

public class HttpGetRequest {

	static String consumer_key = "RbvBRIslnkuZ";
	static String consumer_secret = "WmiiUJXmtOfIC8ZKPacO965fMm4%3D";
	
	public static void demo() throws IOException {
		
		String access_token = "rst";
		String access_secret = "xzy";

		OAuthConsumer consumer = new CommonsHttpOAuthConsumer(consumer_key, consumer_secret);
		consumer.setTokenWithSecret(access_token, access_secret);

		String uri = "http://114.1.1.9/wp473/oauth1/access";
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(uri);
		try {
			consumer.sign(httpget);
		} catch (OAuthMessageSignerException ex) {
			Logger.getLogger(HttpRequest.class.getName()).log(Level.SEVERE, null, ex);
		} catch (OAuthExpectationFailedException ex) {
			Logger.getLogger(HttpRequest.class.getName()).log(Level.SEVERE, null, ex);
		} catch (OAuthCommunicationException ex) {
			Logger.getLogger(HttpRequest.class.getName()).log(Level.SEVERE, null, ex);
		}
		HttpResponse response = httpclient.execute(httpget);
		System.out.println(response.getStatusLine().toString());
		HttpEntity entity = response.getEntity();
		System.out.println();
		System.out.println(EntityUtils.toString(entity));
	}
	
	public static void getTokens() throws Exception {
		String uri = "http://114.1.1.9/wp473/oauth1/request";
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(uri);
		HttpResponse response = httpclient.execute(httpget);
		System.out.println(response.getStatusLine().toString());
		HttpEntity entity = response.getEntity();
		System.out.println("Entity obtained: " + entity);
	}

	public static void main(String[] args) {
		try {
			
			getTokens() ;
			
			// demo();
		} catch (Exception ioe) {
			System.out.println(ioe);
			ioe.printStackTrace();
		}
	}
}