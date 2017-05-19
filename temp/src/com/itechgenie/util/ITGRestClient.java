package com.itechgenie.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.UnsupportedHttpVersionException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class ITGRestClient {

	private static Gson gson = new Gson();
	private static ObjectMapper mapper = new ObjectMapper();

	private static String HTTPS = "https";
	private static String HTTP = "http";
	private static String POST = "POST";
	private static String GET = "GET";

	private static void logInfo(Object input) {
		System.out.println("ITGRestClient.log: " + input);
	}

	public static <T> T jsonStrToObject_gson(String json, Class<T> responseType) {
		return gson.fromJson(json, responseType);
	}

	public static String objectToJsonStr_json(Object inputObj) {
		return gson.toJson(inputObj);
	}

	public static <T> T jsonStrToObject(String json, Class<T> responseType)
			throws JsonParseException, JsonMappingException, IOException {
		return mapper.readValue(json, responseType);
	}

	public static String objectToJsonStr(Object inputObj)
			throws JsonGenerationException, JsonMappingException, IOException {
		return mapper.writeValueAsString(inputObj);
	}

	public static <T> T get(String url, Map<String, Object> headers, Object requestBody, Class<T> responseType)
			throws IOException, UnsupportedHttpVersionException {
		return doRequest(url, GET, headers, requestBody, responseType);
	}

	public static <T> T post(String url, Map<String, Object> headers, Object requestBody, Class<T> responseType)
			throws IOException, UnsupportedHttpVersionException {
		return doRequest(url, POST, headers, requestBody, responseType);
	}

	public static <T> T doRequest(String url, String method, Map<String, Object> headers, Object requestBody,
			Class<T> responseType) throws IOException, UnsupportedHttpVersionException {
		if (url != null && url.toLowerCase().startsWith(HTTPS)) {
			return doRequest_HTTPS(url, method, headers, requestBody, responseType);
		} else if (url != null && url.toLowerCase().startsWith(HTTP)) {
			return doRequest_HTTP(url, method, headers, requestBody, responseType);
		} else {
			throw new UnsupportedHttpVersionException("Unknown protocol in URL");
		}
	}

	@Deprecated
	public static <T> T doRequest_COMMON_OLD(String url, String method, Map<String, Object> headers, Object requestBody,
			Class<T> responseType) throws IOException, UnsupportedHttpVersionException {

		URLConnection conn = null;
		URL urlObj = new URL(url);

		if (url != null && url.toLowerCase().startsWith(HTTPS)) {
			conn = (HttpsURLConnection) urlObj.openConnection();
			if (POST.equalsIgnoreCase(method))
				;
			((HttpsURLConnection) conn).setRequestMethod("POST");
		} else if (url != null && url.toLowerCase().startsWith(HTTP)) {
			conn = (HttpURLConnection) urlObj.openConnection();
			if (POST.equalsIgnoreCase(method))
				;
			((HttpURLConnection) conn).setRequestMethod("POST");
		} else {
			throw new UnsupportedHttpVersionException("Unknown protocol in URL");
		}

		conn.setDoOutput(true);

		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Accept", "application/json");

		if (headers != null) {
			Iterator<String> itr = headers.keySet().iterator();
			while (itr.hasNext()) {
				String key = itr.next();
				String value = (String) headers.get(key);
				conn.setRequestProperty(key, value);
			}
		}

		if (requestBody != null) {
			String inputStr = objectToJsonStr(requestBody);

			OutputStream outputStream = conn.getOutputStream();
			outputStream.write(inputStr.getBytes());
			outputStream.flush();
		}

		Integer httpErrorCode = -1;

		if (conn instanceof HttpsURLConnection) {
			httpErrorCode = ((HttpsURLConnection) conn).getResponseCode();
		} else {
			httpErrorCode = ((HttpURLConnection) conn).getResponseCode();
		}

		BufferedReader responseBuffer = null;

		if (200 <= httpErrorCode && httpErrorCode <= 299) {
			responseBuffer = new BufferedReader(new InputStreamReader((conn.getInputStream())));
		} else {
			if (conn instanceof HttpsURLConnection) {
				responseBuffer = new BufferedReader(
						new InputStreamReader(((HttpsURLConnection) conn).getErrorStream()));
			} else {
				responseBuffer = new BufferedReader(new InputStreamReader(((HttpURLConnection) conn).getErrorStream()));
			}
		}

		String output;
		StringBuffer respone = new StringBuffer();
		while ((output = responseBuffer.readLine()) != null) {
			respone.append(output);
		}

		if (conn instanceof HttpsURLConnection) {
			((HttpsURLConnection) conn).disconnect();
		} else {
			((HttpURLConnection) conn).disconnect();
		}

		return jsonStrToObject(respone.toString(), responseType);
	}

	public static <T> T doRequest_HTTPS(String url, String method, Map<String, Object> headers, Object requestBody,
			Class<T> responseType) throws IOException, UnsupportedHttpVersionException {

		logInfo("ITGRestClient.doRequest_HTTPS: url: " + url + " - method: " + method + " - headers: " + headers
				+ " - requestBody: " + requestBody + " - responseType: " + responseType.getName());

		HttpsURLConnection conn = null;
		URL urlObj = new URL(url);

		conn = (HttpsURLConnection) urlObj.openConnection();
		if (POST.equalsIgnoreCase(method))
			conn.setRequestMethod("POST");

		conn.setDoOutput(true);

		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Accept", "application/json");

		if (headers != null) {
			Iterator<String> itr = headers.keySet().iterator();
			while (itr.hasNext()) {
				String key = itr.next();
				String value = (String) headers.get(key);
				conn.setRequestProperty(key, value);
			}
		}

		if (requestBody != null) {
			String inputStr = objectToJsonStr(requestBody);

			OutputStream outputStream = conn.getOutputStream();
			outputStream.write(inputStr.getBytes());
			outputStream.flush();
		}

		Integer httpErrorCode = -1;

		httpErrorCode = conn.getResponseCode();

		BufferedReader responseBuffer = null;

		if (200 <= httpErrorCode && httpErrorCode <= 299) {
			responseBuffer = new BufferedReader(new InputStreamReader((conn.getInputStream())));
		} else {
			responseBuffer = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}

		String output;
		StringBuffer respone = new StringBuffer();
		while ((output = responseBuffer.readLine()) != null) {
			respone.append(output);
		}

		conn.disconnect();

		return jsonStrToObject(respone.toString(), responseType);
	}

	public static <T> T doRequest_HTTP(String url, String method, Map<String, Object> headers, Object requestBody,
			Class<T> responseType) throws IOException, UnsupportedHttpVersionException {
		
		logInfo("ITGRestClient.doRequest_HTTP: url: " + url + " - method: " + method + " - headers: " + headers
				+ " - requestBody: " + requestBody + " - responseType: " + responseType.getName());

		HttpURLConnection conn = null;
		URL urlObj = new URL(url);

		conn = (HttpURLConnection) urlObj.openConnection();
		conn.setRequestMethod(method);

		conn.setDoOutput(true);

		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Accept", "application/json");

		if (headers != null) {
			Iterator<String> itr = headers.keySet().iterator();
			while (itr.hasNext()) {
				String key = itr.next();
				String value = (String) headers.get(key);
				conn.setRequestProperty(key, value);
			}
		}

		if (!GET.equalsIgnoreCase(method)) {
			if (requestBody != null) {
				String inputStr = objectToJsonStr(requestBody);

				OutputStream outputStream = conn.getOutputStream();
				outputStream.write(inputStr.getBytes());
				outputStream.flush();
			}
		}

		Integer httpErrorCode = -1;

		httpErrorCode = conn.getResponseCode();

		BufferedReader responseBuffer = null;

		if (200 <= httpErrorCode && httpErrorCode <= 299) {
			responseBuffer = new BufferedReader(new InputStreamReader((conn.getInputStream())));
		} else {
			responseBuffer = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}

		String output;
		StringBuffer respone = new StringBuffer();
		while ((output = responseBuffer.readLine()) != null) {
			respone.append(output);
		}

		conn.disconnect();

		return

		jsonStrToObject(respone.toString(), responseType);
	}

}