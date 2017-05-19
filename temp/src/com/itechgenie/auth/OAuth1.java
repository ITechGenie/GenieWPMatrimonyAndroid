package com.itechgenie.auth;

//Java Libraries
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.HttpsURLConnection;

//Apache Commons Libraries used for the Nonce &amp; Base64
import org.apache.commons.lang3.RandomStringUtils;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
//Bouncy Castle Libraries used for CMAC encryption
import org.bouncycastle.crypto.engines.AESFastEngine;
import org.bouncycastle.crypto.macs.CMac;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Base64Encoder;

/**
 * Very basic sample code that demonstrates how to make an OAuth 1.0
 * System-to-System request to the LearningStudio API
 */
public class OAuth1 {

	public static void main(final String[] args) throws Exception {
		// Setup the variables necessary to create the OAuth 1.0 signature and
		// make the request
		String httpMethod = "GET";
		String URI = "http://114.1.1.9/wp473";
		String appID = "RestAPI";
		String consumerKey = "RbvBRIslnkuZ";
		String secret = "G6YIzMZvM1EUbl1Oc8aJXuYIleiDcXWvHrMOXtW4qOCP8GsW";
		String body = "{requestBody}";
		String signatureMethod = "HMAC-SHA1";
		byte[] requestBody = "{}".getBytes("UTF-8");
		HttpURLConnection request = null;
		BufferedReader in = null;
		URL url = new URL(String.format("http://114.1.1.9/wp473/oauth1/request", URI));

		// Set the Nonce and Timestamp parameters
		String nonce = getNonce();
		String timestamp = getTimestamp();

		// Set the request body if making a POST or PUT request
		if ("POST".equals(httpMethod) || "PUT".equals(httpMethod)  ) {
			requestBody = body.getBytes("UTF-8");
		}

		// Create the OAuth parameter name/value pair
		Map<String, String> oauthParams = new LinkedHashMap<String, String>();
		oauthParams.put("oauth_consumer_key", consumerKey);
		oauthParams.put("application_id", appID);
		oauthParams.put("oauth_signature_method", signatureMethod);
		oauthParams.put("oauth_timestamp", timestamp);
		oauthParams.put("oauth_nonce", nonce);

		// Get the OAuth 1.0 Signature
		String signature = generateSignature(httpMethod, url, oauthParams, requestBody, secret);
		System.out.println(String.format("OAuth 1.0 Signature = %s", signature));

		// Add the oauth_signature parameter to the set of OAuth Parameters
		oauthParams.put("oauth_signature", signature);

		// Generate a string of comma delimited: keyName="URL-encoded(value)"
		// pairs
		StringBuilder sb = new StringBuilder();
		String delimiter = "";
		for (String keyName : oauthParams.keySet()) {
			sb.append(delimiter);
			String value = oauthParams.get((String) keyName);
			sb.append(keyName).append("=\"").append(URLEncoder.encode(value, "UTF-8")).append("\"");
			delimiter = ",";
		}

		String urlString = url.toString();
		// omit the queryString from the url
		int startOfQueryString = urlString.indexOf('?');
		if (startOfQueryString != -1) {
			urlString = urlString.substring(0, startOfQueryString);
		}

		// Build the X-Authorization request header
		String xauth = String.format("OAuth realm=\"%s\",%s", urlString, sb.toString());
		System.out.println(String.format("X-Authorization request header = %s", xauth));

		try {
			// Setup the Request
			request = (HttpURLConnection) url.openConnection();
			request.setRequestMethod(httpMethod);
			request.addRequestProperty("Authorization", xauth);

			// Set the request body if making a POST or PUT request
			if ("POST".equals(httpMethod) || "PUT".equals(httpMethod) || "GET".equals(httpMethod) ) {
				request.setRequestProperty("Content-Length", "" + requestBody.length);
				request.setDoOutput(true);

				OutputStream postStream = request.getOutputStream();
				postStream.write(requestBody, 0, requestBody.length);
				postStream.close();
			}
			
			if (200 <= request.getResponseCode() && request.getResponseCode() <= 299) {
				in = new BufferedReader(new InputStreamReader((request.getInputStream())));
			} else {
				in = new BufferedReader(new InputStreamReader((request.getErrorStream())));
			}

			// Send Request &amp; Get Response
			//InputStreamReader reader = new InputStreamReader(request.getInputStream());
			//in = new BufferedReader(reader);

			// Get the response stream
			String response = in.readLine();
			System.out.println(String.format("Successful Response: \r\n%s", response));

		} catch (IOException e) {
			// This exception will be raised if the serve didn't return 200 - OK
			System.out.print(e.getMessage());
			e.printStackTrace();
		} finally {
			if (in != null)
				in.close();
			if (request != null)
				request.disconnect();
		}
	}

	/**
	 * Generates a random nonce
	 * 
	 * @return A unique identifier for the request
	 */
	private static String getNonce() {
		return RandomStringUtils.randomAlphanumeric(32);
	}

	/**
	 * Generates an integer representing the number of seconds since the unix
	 * epoch using the date/time the request is issued
	 * 
	 * @return A timestamp for the request
	 */
	private static String getTimestamp() {
		return Long.toString((System.currentTimeMillis() / 1000));
	}

	/**
	 * Generates an OAuth 1.0 signature
	 * 
	 * @param httpMethod
	 *            The HTTP method of the request
	 * @param URL
	 *            The request URL
	 * @param oauthParams
	 *            The associative set of signable oAuth parameters
	 * @param requestBody
	 *            The serialized POST/PUT message body
	 * @param secret
	 *            Alphanumeric string used to validate the identity of the
	 *            education partner (Private Key)
	 * 
	 * @return A string containing the Base64-encoded signature digest
	 * 
	 * @throws UnsupportedEncodingException
	 */
	private static String generateSignature(String httpMethod, URL url, Map<String, String> oauthParams,
			byte[] requestBody, String secret) throws Exception {
		// Ensure the HTTP Method is upper-cased
		httpMethod = httpMethod.toUpperCase();

		// Construct the URL-encoded OAuth parameter portion of the signature
		// base string
		String encodedParams = normalizeParams(httpMethod, url, oauthParams, requestBody);

		// URL-encode the relative URL
		String encodedUri = URLEncoder.encode(url.getPath(), "UTF-8");

		// Build the signature base string to be signed with the Consumer Secret
		String baseString = String.format("%s&%s&%s", httpMethod, encodedUri, encodedParams);

		//return generateCmac(secret, baseString);
		// return generateHmacSHA256Signature(secret, baseString) ;
		return generateSHA_01(secret, baseString) ; 
	}

	/**
	 * Normalizes all OAuth signable parameters and url query parameters
	 * according to OAuth 1.0
	 * 
	 * @param httpMethod
	 *            The upper-cased HTTP method
	 * @param URL
	 *            The request URL
	 * @param oauthParams
	 *            The associative set of signable oAuth parameters
	 * @param requstBody
	 *            The serialized POST/PUT message body
	 * 
	 * @return A string containing normalized and encoded oAuth parameters
	 * 
	 * @throws UnsupportedEncodingException
	 */
	private static String normalizeParams(String httpMethod, URL url, Map<String, String> oauthParams,
			byte[] requestBody) throws UnsupportedEncodingException {

		// Sort the parameters in lexicographical order, 1st by Key then by
		// Value
		Map<String, String> kvpParams = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
		kvpParams.putAll(oauthParams);

		// Place any query string parameters into a key value pair using equals
		// ("=") to mark
		// the key/value relationship and join each parameter with an ampersand
		// ("&")
		if (url.getQuery() != null) {
			for (String keyValue : url.getQuery().split("&")) {
				String[] p = keyValue.split("=");
				kvpParams.put(p[0], p[1]);
			}

		}

		// Include the body parameter if dealing with a POST or PUT request
		if ("POST".equals(httpMethod) || "PUT".equals(httpMethod)) {
			String body = Base64.encode(requestBody).toString().replaceAll("\r\n", "");
			// url encode the body 2 times now before combining other params
			body = URLEncoder.encode(body, "UTF-8");
			body = URLEncoder.encode(body, "UTF-8");
			kvpParams.put("body", body);
		}

		// separate the key and values with a "="
		// separate the kvp with a "&"
		StringBuilder combinedParams = new StringBuilder();
		String delimiter = "";
		for (String key : kvpParams.keySet()) {
			combinedParams.append(delimiter);
			combinedParams.append(key);
			combinedParams.append("=");
			combinedParams.append(kvpParams.get(key));
			delimiter = "&";
		}

		// url encode the entire string again before returning
		return URLEncoder.encode(combinedParams.toString(), "UTF-8");
	}

	
	private static String generateSHA_01(String key, String msg) throws UnsupportedEncodingException {
		byte[] keyBytes = key.getBytes("UTF-8");
		byte[] data = msg.getBytes("UTF-8");

		//CMac macProvider = new CMac(new AESFastEngine());
		HMac macProvider = new HMac(new SHA1Digest()) ;
		macProvider.init(new KeyParameter(keyBytes));
		macProvider.reset();

		macProvider.update(data, 0, data.length);
		byte[] output = new byte[macProvider.getMacSize()];
		macProvider.doFinal(output, 0);

		// Convert the CMAC to a Base64 string and remove the new line the
		// Base64 library adds
		String cmac = Base64.encode(output).toString().replaceAll("\r\n", "");

		return cmac;
	}
	
	/**
	 * Generates a Base64-encoded CMAC-AES digest
	 * 
	 * @param key
	 *            The secret key used to sign the data
	 * @param msg
	 *            The data to be signed
	 * 
	 * @return A CMAC-AES hash
	 * 
	 * @throws UnsupportedEncodingException
	 */
	private static String generateCmac(String key, String msg) throws UnsupportedEncodingException {
		byte[] keyBytes = key.getBytes("UTF-8");
		byte[] data = msg.getBytes("UTF-8");

		CMac macProvider = new CMac(new AESFastEngine());
		macProvider.init(new KeyParameter(keyBytes));
		macProvider.reset();

		macProvider.update(data, 0, data.length);
		byte[] output = new byte[macProvider.getMacSize()];
		macProvider.doFinal(output, 0);

		// Convert the CMAC to a Base64 string and remove the new line the
		// Base64 library adds
		String cmac = Base64.encode(output).toString().replaceAll("\r\n", "");

		return cmac;
	}
	
	public static String generateHmacSHA256Signature(String key, String data)   throws Exception {
	    byte[] hmacData = null;

	    try {
	        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
	        Mac mac = Mac.getInstance("HmacSHA256");
	        mac.init(secretKey);
	        hmacData = mac.doFinal(data.getBytes("UTF-8"));
	        return Base64.encode(hmacData).toString().replaceAll("\r\n", "");
	    } catch (UnsupportedEncodingException e) {
	       e.printStackTrace();
	    }
	    return null ;
	}
}
