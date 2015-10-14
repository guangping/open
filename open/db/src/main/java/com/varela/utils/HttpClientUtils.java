package com.varela.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lance on 9/28/2015.
 */
public class HttpClientUtils {
    private static final Logger logger = Logger.getLogger(HttpClientUtils.class);

    private static final String CHARSET = "UTF-8";
    private static final int TIME_OUT = 5000;
    private static final int CONN_REQ_TIME_OUT = 5000;


    /**
     * post form形式
     *
     * @param url    地址
     * @param params 提交参数
     */
    public static HttpResponse post(String url, Map<String, String> params) {
        HttpResponse rval = new HttpResponse();
        String rsp = null;
        // 创建默认的httpClient实例.
        CloseableHttpClient client = HttpClients.createDefault();
        // 创建httppost
        HttpPost post = new HttpPost(url);
        try {
            RequestConfig config = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).
                    setConnectTimeout(TIME_OUT).setConnectionRequestTimeout(CONN_REQ_TIME_OUT).build();
            post.setConfig(config);
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            if (null != params) {
                for (String key : params.keySet()) {
                    formparams.add(new BasicNameValuePair(key, params.get(key)));
                }
            }
            StringEntity requestEntity = new UrlEncodedFormEntity(formparams, CHARSET);
            post.setEntity(requestEntity);
            CloseableHttpResponse response = client.execute(post);
            try {
                logger.info("status line:" + response.getStatusLine());
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        rsp = EntityUtils.toString(entity, CHARSET);
                        rval.setSuccess(true);
                        rval.setResult(rsp);
                        logger.info("Response content: " + rsp);
                    }
                }
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return rval;
        }
    }


    /**
     * post 流形式
     *
     * @param url
     * @param json
     */
    public static HttpResponse post(String url, String json) {
        HttpResponse rval = new HttpResponse();
        if (StringUtils.isBlank(json)) {
            rval.setResult("json is null!");
            return rval;
        }
        String rsp = null;
        // 创建默认的httpClient实例.
        CloseableHttpClient client = HttpClients.createDefault();
        // 创建httppost
        HttpPost post = new HttpPost(url);
        try {
            RequestConfig config = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).
                    setConnectTimeout(TIME_OUT).setConnectionRequestTimeout(CONN_REQ_TIME_OUT).build();
            post.setConfig(config);
            StringEntity requestEntity = new StringEntity(json, ContentType.create("text/plain", CHARSET));
            post.setEntity(requestEntity);
            CloseableHttpResponse response = client.execute(post);
            try {
                logger.info("status line:" + response.getStatusLine());
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        rsp = EntityUtils.toString(entity, CHARSET);
                        rval.setSuccess(true);
                        rval.setResult(rsp);
                        logger.info("Response content: " + rsp);
                    }
                }
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return rval;
        }
    }

    /**
     * get
     *
     * @param url 地址
     */
    public static HttpResponse get(String url) {
        HttpResponse rval = new HttpResponse();
        String rsp = null;
        // 创建默认的httpClient实例.
        CloseableHttpClient client = HttpClients.createDefault();
        // 创建httppost
        HttpPost post = new HttpPost(url);
        try {
            RequestConfig config = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).
                    setConnectTimeout(TIME_OUT).setConnectionRequestTimeout(CONN_REQ_TIME_OUT).build();
            post.setConfig(config);
            CloseableHttpResponse response = client.execute(post);
            try {
                logger.info("status line:" + response.getStatusLine());
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        rsp = EntityUtils.toString(entity, CHARSET);
                        rval.setSuccess(true);
                        rval.setResult(rsp);
                        logger.info("Response content: " + rsp);
                    }
                }
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return rval;
        }
    }
}
