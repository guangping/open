package com.varela.utils.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lance on 9/28/2015.
 */
public class HttpClientUtils {
    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

    private static final String CHARSET = "UTF-8";
    private static final int TIME_OUT = 5000;
    private static final int CONN_REQ_TIME_OUT = 5000;

    private static final String CONTENT_TYPE = "application/x-www-form-urlencoded;charset=utf-8;";

    private static final String KEEP_ALIVE = "Keep-Alive";


    /**
     * post form形式
     *
     * @param url    地址
     * @param params 提交参数
     */
    public static HttpResponse postForm(String url, String params) {
        HttpResponse rval = new HttpResponse();
        String rsp = null;
        // 创建默认的httpClient实例.
        CloseableHttpClient client = HttpClients.createDefault();
        // 创建httppost
        HttpPost post = new HttpPost(url);
        try {
            post.setHeader("Content-Type", CONTENT_TYPE);
            post.setHeader("Connection", KEEP_ALIVE);
            RequestConfig config = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).
                    setConnectTimeout(TIME_OUT).setConnectionRequestTimeout(CONN_REQ_TIME_OUT).setSocketTimeout(TIME_OUT).build();
            post.setConfig(config);

            StringEntity requestEntity = new StringEntity(params, CHARSET);
            post.setEntity(requestEntity);
            CloseableHttpResponse response = client.execute(post);
            try {
                logger.info("url:{},status line:{}", url, response.getStatusLine());
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        rsp = EntityUtils.toString(entity, CHARSET);
                        rval.setHttpMsg(HttpMsg.Success);
                        rval.setResult(rsp);
                        logger.info("Response content: " + rsp);
                    }
                }
            } finally {
                response.close();
            }
        } catch (IOException e) {
            rval.setHttpMsg(HttpMsg.Time_Out);
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
     * post form形式
     *
     * @param url    地址
     * @param params 提交参数
     */
    public static HttpResponse postForm(String url, Map<String, String> params) {
        HttpResponse rval = new HttpResponse();
        String rsp = null;
        // 创建默认的httpClient实例.
        CloseableHttpClient client = HttpClients.createDefault();
        // 创建httppost
        HttpPost post = new HttpPost(url);
        try {
            post.addHeader("Content-Type", CONTENT_TYPE);
            post.addHeader("Connection", KEEP_ALIVE);
            RequestConfig config = RequestConfig.custom()
                    .setCookieSpec(CookieSpecs.STANDARD_STRICT)
                    .setConnectTimeout(TIME_OUT).setSocketTimeout(TIME_OUT)
                    .setContentCompressionEnabled(true)
                    .setConnectionRequestTimeout(CONN_REQ_TIME_OUT).build();
            post.setConfig(config);
            List<NameValuePair> formparams = getParams(params);

            StringEntity requestEntity = new UrlEncodedFormEntity(formparams, CHARSET);
            post.setEntity(requestEntity);
            logger.info("url:{}", url);
            CloseableHttpResponse response = client.execute(post);
            try {
                logger.info("status line:{}", response.getStatusLine());
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        rsp = EntityUtils.toString(entity, CHARSET);
                        rval.setHttpMsg(HttpMsg.Success);
                        rval.setResult(rsp);
                        logger.info("Response content: " + rsp);
                    }
                }
            } finally {
                response.close();
            }
        } catch (IOException e) {
            rval.setHttpMsg(HttpMsg.Time_Out);
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

    private static List<NameValuePair> getParams(Map<String, String> params) {
        List<NameValuePair> formparams = new ArrayList();
        if (null != params && !params.isEmpty()) {
            for (String key : params.keySet()) {
                formparams.add(new BasicNameValuePair(key, String.valueOf(params.get(key))));
            }
        }
        return formparams;
    }


    /**
     * post 流形式
     *
     * @param url
     * @param json
     */
    public static HttpResponse postStream(String url, String json) {
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
            StringEntity requestEntity = new StringEntity(json, ContentType.create("text/plain", CHARSET));
            post.setEntity(requestEntity);
            CloseableHttpResponse response = client.execute(post);
            try {
                logger.info("url:{},status line:{}", url, response.getStatusLine());
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        rsp = EntityUtils.toString(entity, CHARSET);
                        rval.setHttpMsg(HttpMsg.Success);
                        rval.setResult(rsp);
                        logger.info("Response content: " + rsp);
                    }
                }
            } finally {
                response.close();
            }
        } catch (IOException e) {
            rval.setHttpMsg(HttpMsg.Time_Out);
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
    public static HttpResponse getStream(String url) {
        HttpResponse rval = new HttpResponse();
        String rsp = null;
        // 创建默认的httpClient实例.
        CloseableHttpClient client = HttpClients.createDefault();
        // 创建httppost
        HttpGet get = new HttpGet(url);
        try {
            RequestConfig config = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).
                    setConnectTimeout(TIME_OUT).setConnectionRequestTimeout(CONN_REQ_TIME_OUT).build();
            get.setConfig(config);
            CloseableHttpResponse response = client.execute(get);
            try {
                logger.info("url:{},status line:{}", url, response.getStatusLine());
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        rsp = EntityUtils.toString(entity, CHARSET);
                        rval.setHttpMsg(HttpMsg.Success);
                        rval.setResult(rsp);
                        logger.info("Response content: " + rsp);
                    }
                }
            } finally {
                response.close();
            }
        } catch (IOException e) {
            rval.setHttpMsg(HttpMsg.Time_Out);
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
