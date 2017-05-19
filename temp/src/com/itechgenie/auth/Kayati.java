package com.itechgenie.auth;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class Kayati {

	public static void main(String[] args) throws ClientProtocolException, IOException, URISyntaxException {
		HttpClient client = new DefaultHttpClient();

		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		qparams.add(new BasicNameValuePair("apikey", "f165dc40-ce3f-6864-7d5e-27a7188b2e62"));
		qparams.add(new BasicNameValuePair("salt", "0123456789"));
		qparams.add(new BasicNameValuePair("signature", "mbrhpXkP0LzNMNDygHAorqMx%2FDGovl%2FauMTOMB6RNMA%3D"));

		HttpPost httpget = new HttpPost("http://aruntest.kayako.com/api/index.php?e=/Core/Test");

		HttpResponse response = client.execute(httpget);
		System.out.println(response.getProtocolVersion());
		System.out.println(response.getStatusLine().getStatusCode());
		System.out.println(response.getStatusLine().getReasonPhrase());
		System.out.println(response.getStatusLine().toString());
	}

	public static void maincc(String[] args) throws Exception, IOException {

		// String secretKey = "secretKey";
		// String salt = "0123456789";

		String secretKey = "RbvBRIslnkuZ";
		String salt = "G6YIzMZvM1EUbl1Oc8aJXuYIleiDcXWvHrMOXtW4qOCP8GsW";

		String generateHmacSHA256Signature = generateHmacSHA256Signature(salt, secretKey);
		System.out.println("Signature: " + generateHmacSHA256Signature);

		String urlEncodedSign = URLEncoder.encode(generateHmacSHA256Signature, "UTF-8");

		System.out.println("Url encoded value: " + urlEncodedSign);
	}

	public static String generateHmacSHA256Signature(String data, String key) throws Exception {
		byte[] hmacData = null;

		try {
			SecretKeySpec secretKey = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
			Mac mac = Mac.getInstance("HmacSHA256");
			mac.init(secretKey);
			hmacData = mac.doFinal(data.getBytes("UTF-8"));
			return org.bouncycastle.util.encoders.Base64.encode(hmacData).toString().replaceAll("\r\n", "");
			// return (new Base64().encode(hmacData.toString())).toString() ;
		} catch (UnsupportedEncodingException e) {
			throw new Exception(e);
		}
	}
}