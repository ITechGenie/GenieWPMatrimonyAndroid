package com.itechgenie.auth;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

import com.google.api.client.auth.oauth.OAuthHmacSigner;
import com.google.api.client.auth.oauth.OAuthParameters;
import com.google.api.client.http.GenericUrl;
import com.itechgenie.util.ITGRestClient;

public class ITGRestSample {

	private static String key = "u1w7AtFRVVhR";
	private static String secret = "0ZWiFiAv35m8oTLbpowJFP5h3PmppFwPBIflckKI0yWlcW1R";

	private static String oToken = "lA6hqpH1EqiomGfLL0iWPC3C";
	private static String oTokenSecret = "RimeLCU61EH17IUpg43akWyC7Yu5zhKvrtCbu8a1pafJF7Gj";

	private static String oauth_verifier = "OOq1HYf7JpRunlzcuosGtXmp";

	public static void testPost() throws Exception {

		String url = "http://114.1.1.9/wp473/wp-json/wp/v2/posts";

		OAuthHmacSigner signer = new OAuthHmacSigner();
		signer.clientSharedSecret = secret;
		signer.tokenSharedSecret = oTokenSecret;

		OAuthParameters parameters = new OAuthParameters();

		parameters.consumerKey = key;
		parameters.nonce = "49342a6ecafaa10f70ced80a1a8e0941" ;// + (int) (Math.random() * 100000000); // getNonce(); 
		parameters.signatureMethod = "HMAC-SHA1";
		parameters.timestamp = "1493539262" ;// + (System.currentTimeMillis() / 1000);
		parameters.token = oToken;
		parameters.version = "1.0";
		parameters.signer = signer;

		GenericUrl arg1 = new GenericUrl(url);

		parameters.computeSignature("POST", arg1);

		Map<String, Object> headers = new HashMap<String, Object>();
		
		String authHeaders = parameters.getAuthorizationHeader() ;
		
		System.out.println("Auth Headers: " + authHeaders);

		headers.put("Accept", "application/json");
		headers.put("Content-type", "application/json");
		headers.put("Authorization", authHeaders);

		Map<String, Object> body = new HashMap<String, Object>();
		body.put("title", "Sample Title");
		body.put("body", "Post body content");

	//	Map<String, Object> resp = (Map<String, Object>) ITGRestClient.post(url, headers, body, Map.class);
	//	System.out.println("Resp: " + resp);

	}
	
	public static void testGet() throws Exception  {


		String url = "http://114.1.1.9/wp473/wp-json/wp/v2/users/me";

		OAuthHmacSigner signer = new OAuthHmacSigner();
		signer.clientSharedSecret = secret;
		signer.tokenSharedSecret = oTokenSecret;

		OAuthParameters parameters = new OAuthParameters();

		parameters.consumerKey = key;
		parameters.nonce = "49342a6ecafaa10f70ced80a1a8e0941" ;// + (int) (Math.random() * 100000000); // getNonce(); 
		parameters.signatureMethod = "HMAC-SHA1";
		parameters.timestamp = "1493539262" ;// + (System.currentTimeMillis() / 1000);
		parameters.token = oToken;
		parameters.version = "1.0";
		parameters.signer = signer;

		GenericUrl arg1 = new GenericUrl(url);

		parameters.computeSignature("GET", arg1);

		Map<String, Object> headers = new HashMap<String, Object>();

		String authHeaders = parameters.getAuthorizationHeader() ;
		
		System.out.println("Auth Headers: " + authHeaders);

		headers.put("Accept", "application/json");
		headers.put("Content-type", "application/json");
		headers.put("Authorization", authHeaders);

		Map<String, Object> resp = (Map<String, Object>) ITGRestClient.get(url, headers, null, Map.class);
		System.out.println("Resp: " + resp);

	}
	
	public static String getNonce() {
		String nonce ;
		try {
			String nonseString = "" + (System.currentTimeMillis() / 1000) ;
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        md.update(nonseString.getBytes());
	        nonce = String.valueOf(md.digest() ) ;
		} catch (Exception e) {
			// ignore the exception
			nonce = "" ;
		}
		return nonce ; 
	}

	public static void main(String[] args) throws Exception {
		 // testPost();
		  testGet(); 
	}

}
