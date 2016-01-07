package cn.com.kanjian.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClientUtils {

	public static String httpGet(String url, Map<String, String> headers) throws IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet httpGet = new HttpGet(url);
			if(headers != null && !headers.isEmpty()) {
				for (Entry<String, String> e : headers.entrySet()) {
					httpGet.setHeader(e.getKey(), e.getValue());
				}
			}
			CloseableHttpResponse response = httpclient.execute(httpGet);
			try {
				HttpEntity entity = response.getEntity();
				return EntityUtils.toString(entity);
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}
	}
	
	public static String httpGet(String url) throws IOException {
		return httpGet(url, null);
	}

	public static String httpPost(String url, Map<String, String> body, Map<String, String> headers) throws IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpPost httpPost = new HttpPost(url);
			List <NameValuePair> nvps = new ArrayList <NameValuePair>();
			for (Entry<String, String> e : body.entrySet()) {
				 nvps.add(new BasicNameValuePair(e.getKey(), e.getValue()));
			}
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			if(headers != null && !headers.isEmpty()) {
				for (Entry<String, String> e : headers.entrySet()) {
					httpPost.setHeader(e.getKey(), e.getValue());
				}
			}
			CloseableHttpResponse response = httpclient.execute(httpPost);
			try {
				HttpEntity entity = response.getEntity();
				return EntityUtils.toString(entity);
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}
	}
	
	public static String httpPost(String url, String body, Map<String, String> headers, String contentType) throws IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpPost httpPost = new HttpPost(url);
			httpPost.setEntity(new StringEntity(body));
			if(contentType != null && !"".equalsIgnoreCase(contentType.trim())) {
				httpPost.setHeader("content-type", contentType);
			}
			if(headers != null && !headers.isEmpty()) {
				for (Entry<String, String> e : headers.entrySet()) {
					httpPost.setHeader(e.getKey(), e.getValue());
				}
			}
			CloseableHttpResponse response = httpclient.execute(httpPost);
			try {
				HttpEntity entity = response.getEntity();
				return EntityUtils.toString(entity);
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}
	}
	
	public static String httpPostJson(String url, String body, Map<String, String> headers) throws IOException {
		return httpPost(url, body, headers, "application/json");
	}

	
	public static String httpPost(String url, Map<String, String> params) throws IOException {
		return httpPost(url, params, null);
	}
	
}
