/**
 * 
 */
package com.fatlamb.fattt.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

/**
 * @author nicolas
 * 
 */
public class HttpClientUtil {
	private static final PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();

	private static CloseableHttpClient httpClient = null;
	static {
		connectionManager.setMaxTotal(30);
		connectionManager.setDefaultMaxPerRoute(10);
		httpClient = HttpClients.custom().setConnectionManager(connectionManager).build();
	}

	public static CloseableHttpClient getHttpClient() {
		return httpClient;
	}

	public static String post(String url, List<NameValuePair> nameValuePairList) throws Exception {
		return post(url, "utf-8", null, nameValuePairList, 5 * 1000, 5 * 1000);
	}

	public static String post(String url, String charset, List<Header> headerList, List<NameValuePair> nameValuePairList, int connTimeOut,
			int waitTimeOut) throws Exception {
		CloseableHttpResponse response = null;
		HttpPost httpPost = new HttpPost(url);
		try {
			if (charset == null)
				charset = "utf-8";
			if (headerList != null)
				httpPost.setHeaders(headerList.toArray(new Header[0]));
			if (nameValuePairList != null)
				httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairList, charset));
			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connTimeOut).setSocketTimeout(waitTimeOut).build();
			httpPost.setConfig(requestConfig);
			response = httpClient.execute(httpPost);
			return EntityUtils.toString(response.getEntity(), charset);
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			if (httpPost != null) {
				try {
					httpPost.releaseConnection();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	}

	public static String get(String url) throws Exception {
		return get(url, null, null, 5 * 1000, 5 * 1000);
	}

	public static CloseableHttpResponse get(String url, RequestConfig config) throws Exception {
		HttpGet httpGet = new HttpGet(url);
		httpGet.setConfig(config);
		return httpClient.execute(httpGet);
	}

	public static String get(String url, String charset, List<Header> headerList, int connTimeOut, int waitTimeOut) throws Exception {
		CloseableHttpResponse response = null;
		HttpGet httpGet = new HttpGet(url);
		try {
			if (charset == null)
				charset = "utf-8";
			if (headerList != null)
				httpGet.setHeaders(headerList.toArray(new Header[0]));
			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connTimeOut).setSocketTimeout(waitTimeOut).build();
			httpGet.setConfig(requestConfig);
			response = httpClient.execute(httpGet);
			return EntityUtils.toString(response.getEntity(), charset);
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			if (httpGet != null) {
				try {
					httpGet.releaseConnection();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	}

	

	public static String doPostSSL(String apiUrl, Map<String, Object> params, int connTimeOut, int waitTimeOut) {
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connTimeOut).setSocketTimeout(waitTimeOut).build();
		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).setConnectionManager(connectionManager).setDefaultRequestConfig(requestConfig).build();
		HttpPost httpPost = new HttpPost(apiUrl);
		CloseableHttpResponse response = null;
		String httpStr = null;
		try {
			httpPost.setConfig(requestConfig);
			List<NameValuePair> pairList = new ArrayList<NameValuePair>(params.size());
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry
						.getValue().toString());
				pairList.add(pair);
			}
			httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("utf-8")));
			response = httpClient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				return null;
			}
			HttpEntity entity = response.getEntity();
			if (entity == null) {
				return null;
			}
			httpStr = EntityUtils.toString(entity, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					EntityUtils.consume(response.getEntity());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return httpStr;
	}

	public static String doPostSSL(String apiUrl,String params, int connTimeOut, int waitTimeOut) {
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connTimeOut).setSocketTimeout(waitTimeOut).build();
		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).setConnectionManager(connectionManager).setDefaultRequestConfig(requestConfig).build();
		HttpPost httpPost = new HttpPost(apiUrl);
		CloseableHttpResponse response = null;
		String httpStr = null;
		try {
			httpPost.setConfig(requestConfig);
			StringEntity postEntity = new StringEntity(params, "UTF-8");
			httpPost.addHeader("Content-Type", "text/xml");
			httpPost.setEntity(postEntity);
			response = httpClient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				return null;
			}
			HttpEntity entity = response.getEntity();
			if (entity == null) {
				return null;
			}
			httpStr = EntityUtils.toString(entity, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					EntityUtils.consume(response.getEntity());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return httpStr;
	}
	
	private static SSLConnectionSocketFactory createSSLConnSocketFactory() {
		SSLConnectionSocketFactory sslsf = null;
		try {
			SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, new TrustStrategy() {
				public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
					return true;
				}
			}).build();

			sslsf = new SSLConnectionSocketFactory(sslContext);
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		}
		return sslsf;
	}
}
