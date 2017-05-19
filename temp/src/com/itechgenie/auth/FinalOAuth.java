package com.itechgenie.auth;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.google.api.client.auth.oauth.OAuthHmacSigner;
import com.google.api.client.auth.oauth.OAuthParameters;
import com.google.api.client.http.GenericUrl;

/**
 * a simple program to get flickr token and token secret.
 * 
 * @author Mark Zang
 * 
 */
public class FinalOAuth {

	private static String webHost = "localhost";
	private static String webProtocol = "http";
	private static String webBaseUrl = null;
	private static String webContext = "/wp474";
	private static String oAuth1AuthorizeUri = "/oauth1/authorize";
	private static String oAuth1RequestUri = "/oauth1/request";
	private static String oAuth1AccessUri = "/oauth1/access";

	private static String oauth_verifier = "r4mWnBW9ZkJYr0kvUnzsCq4i";

	private static String COLON = ":";
	private static String SLASH = "/";

	private static String key = "MskWwW3OYUgE";
	private static String secret = "cqBrfDeK9kPsreAqeiKuhKaUyNdnRVrSGn375bNgv9yE4lnJ";

	private static String oToken = "jVwih85Dr1ebykXfy2S369OI";
	private static String oTokenSecret = "NmrWv3lCqUHvJiwbZhmEdV4ERJKs09TFGphcFWU81ZYVQG4S";

	private static String UNREGISTERED_TOKEN = "Token is not registered for the given consumer";

	private static final String HMAC_SHA1 = "HmacSHA1";

	private static final String ENC = "UTF-8";

	private static Base64 base64 = new Base64();

	private static String getWebBaseURL() {
		return (webBaseUrl == null ? (webProtocol + COLON + SLASH + SLASH + webHost + webContext) : webBaseUrl);
	}

	/**
	 * 
	 * @param url
	 *            the url for "request_token" URLEncoded.
	 * @param params
	 *            parameters string, URLEncoded.
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 */
	private static String getSignature(String method, String url, String params, boolean verifiedOps)
			throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
		/**
		 * base has three parts, they are connected by "&": 1) protocol 2) URL
		 * (need to be URLEncoded) 3) Parameter List (need to be URLEncoded).
		 */
		StringBuilder base = new StringBuilder();
		base.append(method);
		base.append("&");
		base.append(url);
		base.append("&");
		base.append(params);
		System.out.println("Stirng for oauth_signature generation:" + base);
		// yea, don't ask me why, it is needed to append a "&" to the end of
		// secret key. -- Only for the Temprovary token
		// Need to append the temp_token_secret to add it the key
		byte[] keyBytes = null;
		if (verifiedOps)
			keyBytes = (secret + "&" + oTokenSecret).getBytes(ENC);
		else
			keyBytes = (secret + "&").getBytes(ENC);

		SecretKey key = new SecretKeySpec(keyBytes, HMAC_SHA1);

		Mac mac = Mac.getInstance(HMAC_SHA1);
		mac.init(key);

		byte ssdd[] = mac.doFinal(base.toString().getBytes(ENC));

		System.out.println("######################## " + new String(ssdd));

		// encode it, base64 it, change it to string and return.
		return new String(base64.encode(ssdd), ENC).trim();
	}

	private static String getSignature(String url, String params)
			throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
		return getSignature("GET", url, params, false);
	}

	public static void oauthVerifier() throws Exception {
		HttpClient httpclient = new DefaultHttpClient();
		List<NameValuePair> qparams = getHeadersForVerifier();

		// generate URI which lead to access_token and token_secret.

		String cn = URLEncodedUtils.format(qparams, ENC);

		System.out.println("%%%%:  " + cn);

		URI uri = URIUtils.createURI(webProtocol, webHost, -1, webContext + oAuth1AccessUri, cn, null);

		System.out.println("Tokenized URI:" + uri.toString());

		HttpPost httpget = new HttpPost(uri);

		HttpResponse response = httpclient.execute(httpget);
		HttpEntity entity = response.getEntity();

		StringBuffer responseBuff = new StringBuffer("");
		if (entity != null) {
			InputStream instream = entity.getContent();
			int len;
			byte[] tmp = new byte[2048];
			while ((len = instream.read(tmp)) != -1) {
				String str = new String(tmp, 0, len, ENC);
				System.out.println("Resp: " + str);
				responseBuff.append(str);
			}
		}

		System.out.println("My Resp: " + responseBuff);

		oToken = null;
		oTokenSecret = null;

		if (!responseBuff.toString().equals("")) {
			String tokenArr[] = responseBuff.toString().split("&");
			for (String tokKey : tokenArr) {
				System.out.println("Itr: " + tokKey);
				if (tokKey.contains("oauth_token=")) {
					oToken = getKeyPairValue(tokKey, "oauth_token");
				}
				if (tokKey.contains("oauth_token_secret=")) {
					oTokenSecret = getKeyPairValue(tokKey, "oauth_token_secret");
				}
			}
		}

		System.out.println("New oToken: " + oToken);
		System.out.println("New oTokenSecret: " + oTokenSecret);
	}

	public static void createPost() throws Exception {

		String url = "/wp-json/wp/v2/posts";

		HttpClient httpclient = new DefaultHttpClient();
		List<NameValuePair> qparams = getHeadersForOperations("POST", url);

		// generate URI which lead to access_token and token_secret.
		URI uri = URIUtils.createURI(webProtocol, webHost, -1, webContext + url, URLEncodedUtils.format(qparams, ENC),
				null);

		System.out.println("Tokenized URI:" + uri.toString());

		Map<String, String> requestBody = new HashMap<String, String>();
		requestBody.put("title", "Sample Title");
		requestBody.put("body", "Post body content");

		String jsonObj = "{\"title\":\"Sample Title\", \"body\": \"Post body content\"}";

		HttpPost httpPost = new HttpPost(uri);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-type", "application/json");
		StringEntity xmlEntity = new StringEntity(jsonObj);

		httpPost.setEntity(xmlEntity);

		HttpResponse response = httpclient.execute(httpPost);
		HttpEntity entity = response.getEntity();
		System.out.println("Headers: " + response.getAllHeaders());

		System.out.println("Params: " + response.getParams());

		System.out.println("HTTP Code: " + response.getStatusLine().getStatusCode());

		String repEnt = EntityUtils.toString(entity);

		System.out.println("Entitiy Response: " + repEnt);

		if (repEnt != null && repEnt.contains(UNREGISTERED_TOKEN)) {
			System.out.println("Authorize the Token for the user by visiting: " + getWebBaseURL() + oAuth1AuthorizeUri
					+ "?oauth_token=" + oToken + "&oauth_token_secret=" + oTokenSecret);
		}

	}

	public static List<NameValuePair> getHeadersForToken() throws Exception {
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		// These params should ordered in key
		// qparams.add(new BasicNameValuePair("oauth_callback",
		// "testcallback"));
		qparams.add(new BasicNameValuePair("oauth_consumer_key", key));
		qparams.add(new BasicNameValuePair("oauth_nonce", "" + (int) (Math.random() * 100000000)));
		qparams.add(new BasicNameValuePair("oauth_signature_method", "HMAC-SHA1"));
		qparams.add(new BasicNameValuePair("oauth_timestamp", "" + (System.currentTimeMillis() / 1000)));
		qparams.add(new BasicNameValuePair("oauth_version", "1.0"));

		String uri = getWebBaseURL() + oAuth1RequestUri;

		System.out.println("URL: " + uri);

		// generate the oauth_signature
		String signature = getSignature(URLEncoder.encode(uri, ENC),
				URLEncoder.encode(URLEncodedUtils.format(qparams, ENC), ENC));

		// add it to params list
		qparams.add(new BasicNameValuePair("oauth_signature", signature));

		return qparams;

	}

	public static List<NameValuePair> getHeadersForVerifier() throws Exception {
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		// These params should ordered in key
		// qparams.add(new BasicNameValuePair("oauth_callback",
		// "testcallback"));

		qparams.add(new BasicNameValuePair("oauth_consumer_key", key));
		// qparams.add(new BasicNameValuePair("oauth_nonce", "" + (int)
		// (Math.random() * 100000000)));
		qparams.add(new BasicNameValuePair("oauth_nonce", "f9be4b618daf1b9e5d7f66c317d76252"));
		qparams.add(new BasicNameValuePair("oauth_signature_method", "HMAC-SHA1"));
		// qparams.add(new BasicNameValuePair("oauth_timestamp", "" +
		// (System.currentTimeMillis() / 1000)));
		qparams.add(new BasicNameValuePair("oauth_timestamp", "1492945081"));
		qparams.add(new BasicNameValuePair("oauth_token", oToken));
		qparams.add(new BasicNameValuePair("oauth_verifier", oauth_verifier));
		qparams.add(new BasicNameValuePair("oauth_version", "1.0"));

		String uri = getWebBaseURL() + oAuth1AccessUri;

		System.out.println("URL: " + uri);

		// generate the oauth_signature
		String signature = getSignature("POST", URLEncoder.encode(uri, ENC),
				URLEncoder.encode(URLEncodedUtils.format(qparams, ENC), ENC), true);

		System.out.println("%%%%%%%%%%%%%%%%");
		System.out.println("%%%%%%%%%%%%%%%%");
		System.out.println(signature);
		System.out.println("%%%%%%%%%%%%%%%%");
		System.out.println("%%%%%%%%%%%%%%%%");

		// add it to params list
		qparams.add(new BasicNameValuePair("oauth_signature", signature));

		return qparams;

	}

	public static List<NameValuePair> getHeadersForOperations(String method, String uriPath) throws Exception {
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		// These params should ordered in key
		// qparams.add(new BasicNameValuePair("oauth_callback",
		// "testcallback"));

		qparams.add(new BasicNameValuePair("oauth_consumer_key", key));
		qparams.add(new BasicNameValuePair("oauth_nonce", "" + (int) (Math.random() * 100000000)));
		qparams.add(new BasicNameValuePair("oauth_signature_method", "HMAC-SHA1"));
		qparams.add(new BasicNameValuePair("oauth_timestamp", "" + (System.currentTimeMillis() / 1000)));
		qparams.add(new BasicNameValuePair("oauth_token", oToken));
		qparams.add(new BasicNameValuePair("oauth_version", "1.0"));

		String uri = getWebBaseURL() + uriPath;

		System.out.println("URL: " + uri);

		// generate the oauth_signature
		String signature = getSignature(method, URLEncoder.encode(uri, ENC),
				URLEncoder.encode(URLEncodedUtils.format(qparams, ENC), ENC), true);

		System.out.println("%%%%%%%%%%%%%%%%");
		System.out.println("%%%%%%%%%%%%%%%%");
		System.out.println(signature);
		System.out.println("%%%%%%%%%%%%%%%%");
		System.out.println("%%%%%%%%%%%%%%%%");

		// add it to params list
		qparams.add(new BasicNameValuePair("oauth_signature", signature));

		return qparams;

	}

	public static void generateTokenAndSecret() throws ClientProtocolException, IOException, URISyntaxException,
			InvalidKeyException, NoSuchAlgorithmException, Exception {

		HttpClient httpclient = new DefaultHttpClient();
		List<NameValuePair> qparams = getHeadersForToken();

		// generate URI which lead to access_token and token_secret.
		URI uri = URIUtils.createURI(webProtocol, webHost, -1, webContext + oAuth1RequestUri,
				URLEncodedUtils.format(qparams, ENC), null);

		System.out.println("Tokenized URI:" + uri.toString());

		HttpGet httpget = new HttpGet(uri);
		// output the response content.
		System.out.println("Token and Token Secrect:");

		HttpResponse response = httpclient.execute(httpget);
		HttpEntity entity = response.getEntity();

		StringBuffer responseBuff = new StringBuffer("");
		if (entity != null) {
			InputStream instream = entity.getContent();
			int len;
			byte[] tmp = new byte[2048];
			while ((len = instream.read(tmp)) != -1) {
				String str = new String(tmp, 0, len, ENC);
				// System.out.println("Resp: " + str);
				responseBuff.append(str);
			}
		}

		System.out.println("My Resp: " + responseBuff);

		if (!responseBuff.toString().equals("")) {
			String tokenArr[] = responseBuff.toString().split("&");
			for (String tokKey : tokenArr) {
				System.out.println("Itr: " + tokKey);
				if (tokKey.contains("oauth_token=")) {
					oToken = getKeyPairValue(tokKey, "oauth_token");
				}
				if (tokKey.contains("oauth_token_secret=")) {
					oTokenSecret = getKeyPairValue(tokKey, "oauth_token_secret");
				}
			}
		}

		System.out.println("oToken: " + oToken);
		System.out.println("oTokenSecret: " + oTokenSecret);

	}

	private static String getKeyPairValue(String tokKey, String key) {
		String valArr[] = tokKey.split("=");
		if (valArr != null && valArr.length == 2) {
			String valArrKey = valArr[0];
			String valArrVal = valArr[1];
			if (key.equals(valArrKey)) {
				return valArrVal;
			}
		}
		return null;
	}

	public static void editUser() throws Exception {

		String url = "/wp-json/wp/v2/users/me";

		HttpClient httpclient = new DefaultHttpClient();
		List<NameValuePair> qparams = getHeadersForOperations("GET", url);

		OAuthHmacSigner signer = new OAuthHmacSigner();
		signer.clientSharedSecret = secret;
		signer.tokenSharedSecret = oTokenSecret;

		OAuthParameters parameters = new OAuthParameters();

		parameters.consumerKey = key;
		parameters.nonce = "" + (int) (Math.random() * 100000000);
		parameters.signatureMethod = "HMAC-SHA1";
		parameters.timestamp = "" + (System.currentTimeMillis() / 1000);
		parameters.token = oToken;
		parameters.version = "1.0";
		parameters.signer = signer;

		GenericUrl arg1 = new GenericUrl("http://114.1.1.9/wp473/wp-json/wp/v2/users/me");

		parameters.computeSignature("GET", arg1);

		String authHeaders = parameters.getAuthorizationHeader();

		System.out.println("Auth Headers: " + authHeaders);

		// qparams.add(new BasicNameValuePair("context", "edit"));

		/*
		 * URI uri = URIUtils.createURI(webProtocol, webHost, -1, webContext +
		 * url , URLEncodedUtils.format(qparams, ENC), null);
		 */

		URI uri = URIUtils.createURI(webProtocol, webHost, -1, webContext + url, null, null);

		System.out.println("Tokenized URI:" + uri.toString());

		HttpGet httpPost = new HttpGet(uri);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Authorization", authHeaders);
		httpPost.setHeader("Content-type", "application/json");
		httpPost.setHeader("context", "edit");
		// StringEntity xmlEntity = new StringEntity(jsonObj);

		// httpPost.setEntity(xmlEntity );

		HttpResponse response = httpclient.execute(httpPost);
		HttpEntity entity = response.getEntity();
		System.out.println("Headers: " + response.getAllHeaders());

		System.out.println("Params: " + response.getParams());

		System.out.println("HTTP Code: " + response.getStatusLine().getStatusCode());

		String repEnt = EntityUtils.toString(entity);

		System.out.println("Entitiy Response: " + repEnt);

	}

	public static void createPostNew() throws Exception {

		String url = "/wp-json/wp/v2/posts";

		HttpClient httpclient = new DefaultHttpClient();
		List<NameValuePair> qparams = getHeadersForOperations("POST", url);
		
		URI uri = URIUtils.createURI(webProtocol, webHost, -1, webContext + url, null, null);
		
		HttpPost httpPost = new HttpPost(uri) ;
		
		OAuthHmacSigner signer = new OAuthHmacSigner();
		signer.clientSharedSecret = secret;
		signer.tokenSharedSecret = oTokenSecret;

		OAuthParameters parameters = new OAuthParameters();

		parameters.consumerKey = key;
		parameters.nonce = "" + (int) (Math.random() * 100000000);
		parameters.signatureMethod = "HMAC-SHA1";
		parameters.timestamp = "" + (System.currentTimeMillis() / 1000);
		parameters.token = oToken;
		parameters.version = "1.0";
		parameters.signer = signer;
		
		GenericUrl arg1 = new GenericUrl("http://114.1.1.9/wp473/wp-json/wp/v2/posts");
		
		parameters.computeSignature("POST", arg1);

		String jsonObj = "{\"title\":\"Sample Title\", \"body\": \"Post body content\"}";

		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-type", "application/json");
		httpPost.setHeader("Authorization", parameters.getAuthorizationHeader());
		StringEntity xmlEntity = new StringEntity(jsonObj);

		httpPost.setEntity(xmlEntity);

		HttpResponse response = httpclient.execute(httpPost);
		HttpEntity entity = response.getEntity();
		System.out.println("Headers: " + response.getAllHeaders());

		System.out.println("Params: " + response.getParams());

		System.out.println("HTTP Code: " + response.getStatusLine().getStatusCode());

		String repEnt = EntityUtils.toString(entity);

		System.out.println("Entitiy Response: " + repEnt);
		
		if (repEnt != null && repEnt.contains(UNREGISTERED_TOKEN)) {
			System.out.println("Authorize the Token for the user by visiting: " + getWebBaseURL() + oAuth1AuthorizeUri
					+ "?oauth_token=" + oToken + "&oauth_token_secret=" + oTokenSecret);
		}
		
	}
	
	public static void checkAuthHeaders() throws Exception {
		//OAuth oauth_consumer_key="u1w7AtFRVVhR", oauth_nonce="13c5392ba4f2ebba2e32e04b7cb240e5", 
		// oauth_signature="It4ib5x%252FBU%252FPpc6CJTbykxAW%252Fxs%253D", oauth_signature_method="HMAC-SHA1",
		// oauth_timestamp="1493575452", oauth_token="2dEio0Zv52mP9101t0F8kc04", oauth_version="1.0"

				OAuthHmacSigner signer = new OAuthHmacSigner();
				signer.clientSharedSecret = "cqBrfDeK9kPsreAqeiKuhKaUyNdnRVrSGn375bNgv9yE4lnJ";
		signer.tokenSharedSecret = "NmrWv3lCqUHvJiwbZhmEdV4ERJKs09TFGphcFWU81ZYVQG4S";

		OAuthParameters parameters = new OAuthParameters();

		parameters.consumerKey = "MskWwW3OYUgE";
		parameters.nonce = "d77a8062d6300b8362cd5cd6cf1f5f6d"  ;
		parameters.signatureMethod = "HMAC-SHA1";
		parameters.timestamp = "1493577665" ;
		parameters.token = "jVwih85Dr1ebykXfy2S369OI";
		parameters.version = "1.0";
		parameters.signer = signer;

		GenericUrl arg1 = new GenericUrl("http://localhost/wp474/wp-json/wp/v2/users/me");

		parameters.computeSignature("GET", arg1);

		String authHeaders = parameters.getAuthorizationHeader();
		
		System.out.println(authHeaders);
		
	}

	public static void main(String[] args) {
		try {

			// checkAuthHeaders () ;
			// generateTokenAndSecret();
			
			// oauthVerifier() ;

			// createPost() ;

			// editUser() ;
			
			// createPostNew() ;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
