package com.varela.wechat.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Map;
import java.util.Map.Entry;

public class HttpWeChatUtils {

	private static final Logger logger = LoggerFactory
			.getLogger(HttpWeChatUtils.class);

	private static final String MCHID= WeChatConstUtil.WECHAT_PAY_MCH_ID;


	private HttpWeChatUtils() {

	}

	public static String post(String url, String data) throws Exception {
		HttpPost httppost = new HttpPost(url);
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		HttpClient httpClient = httpClientBuilder.build();
		httppost.setHeader("Content-Type", "application/xml; charset=utf-8");
		HttpEntity requestEntity = new StringEntity(data, "utf-8");
		httppost.setEntity(requestEntity);
		HttpResponse response = httpClient.execute(httppost);
		int resStatu = response.getStatusLine().getStatusCode();

		logger.info("status code :" + resStatu);
		String html = EntityUtils.toString(response.getEntity(), "UTF-8");

		return html;
	}

	public static String get(String url, Map<String, String> headparameters)
			throws Exception {

		HttpGet httpget = new HttpGet(url);
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		HttpClient httpClient = httpClientBuilder.build();

		if (headparameters != null) {
			for (Entry<String, String> headparameter : headparameters
					.entrySet()) {
				httpget.setHeader(headparameter.getKey(),
						headparameter.getValue());
			}
		}

		HttpResponse response = httpClient.execute(httpget);
		HttpEntity entity = response.getEntity();
		int resStatu = response.getStatusLine().getStatusCode();
		logger.info("status code :" + resStatu);

		String html = EntityUtils.toString(entity);
		html = new String(html.getBytes("ISO-8859-1"), "UTF8");
		return html;
	}




	/**
	 * 获取携带证书的HttpClient实例
	 * 
	 * @return
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws IOException
	 * @throws KeyManagementException
	 * @throws UnrecoverableKeyException
	 */
	public static HttpClient getCertHttpClientIntance()
			throws KeyStoreException, NoSuchAlgorithmException,
			CertificateException, IOException, KeyManagementException,
			UnrecoverableKeyException {
		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		InputStream in = HttpWeChatUtils.class.getClassLoader()
				.getResourceAsStream("cert/apiclient_cert.p12");
		try {
			keyStore.load(in, MCHID.toCharArray());
		} finally {
			in.close();
		}
		SSLContext sslcontext = SSLContexts.custom()
				.loadKeyMaterial(keyStore, MCHID.toCharArray()).build();
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
				sslcontext, new String[] { "TLSv1" }, null,
				SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
		CloseableHttpClient httpclient = HttpClients.custom()
				.setSSLSocketFactory(sslsf).build();
		return httpclient;
	}

	/**
	 * 携带安全证书的post请求
	 * 
	 * @param url
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String postWithCert(String url, String data) throws Exception {
		HttpPost httppost = new HttpPost(url);
		httppost.setHeader("Content-Type", "application/xml; charset=utf-8");
		HttpEntity requestEntity = new StringEntity(data, "utf-8");
		httppost.setEntity(requestEntity);
		HttpResponse response = getCertHttpClientIntance().execute(httppost);
		int resStatu = response.getStatusLine().getStatusCode();
		logger.info("status code :" + resStatu);
		
		return EntityUtils.toString(response.getEntity(), "UTF-8");
	}

}
