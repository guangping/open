package com.varela.open.utils;

import com.varela.open.Constants;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: guangping
 * Date: 2014-06-30 16:52
 * To change this template use File | Settings | File Templates.
 */
public class HttpUtils {

    public static final int TIME_OUT = 30000;
    public static final int RETRY_COUNT = 3;


    public static String post(String url, Map<String, String> form) {
        int retry = RETRY_COUNT;
        boolean retrySign = false;
        String rval = null;
        for (int i = 0; i < retry; i++) {

            try {
                HttpURLConnection con = null;
                OutputStream out = null;
                try {
                    con = (HttpURLConnection) new URL(url).openConnection();
                    con.setReadTimeout(TIME_OUT);
                    con.setConnectTimeout(TIME_OUT);
                    con.setDoInput(true);
                    con.setDoOutput(true);
                    con.setRequestMethod("POST");
                    con.setRequestProperty("connection", "Keep-Alive");
                    con.setRequestProperty("Charsert", "UTF-8");
                    con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                    String postParam = encodeParameters(form);
                    byte[] bytes = postParam.getBytes("UTF-8");
                    con.setRequestProperty("Content-Length", Integer.toString(bytes.length));
                    out = con.getOutputStream();
                    out.write(bytes);
                    out.flush();

                    if (HttpURLConnection.HTTP_OK == con.getResponseCode()) {
                        rval = getMsg(con.getInputStream());
                        retrySign = false;
                    } else retrySign = true;
                } catch (IOException e) {
                    retrySign = true;
                    e.printStackTrace();
                } finally {
                    if (null != out) {
                        try {
                            out.close();
                        } catch (IOException e) {
                        }
                    }
                    if (null != con) {
                        con.disconnect();
                    }
                    if (!retrySign) {
                        break;
                    }
                }
            } catch (RuntimeException e) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ignore) {
                }
            }
        }
        return rval;
    }


    private static String encodeParameters(Map<String, String> params) {
        if ((null == params) || ((params != null) && (params.size() == 0))) {
            return "";
        }
        StringBuilder buf = new StringBuilder();
        Iterator it = params.entrySet().iterator();
        int j = 0;
        while (it.hasNext()) {
            Map.Entry e = (Map.Entry) it.next();
            if (j != 0) {
                buf.append("&");
            }
            buf.append(encode((String) e.getKey())).append("=").append(encode((String) e.getValue()));

            j++;
        }
        return buf.toString();
    }

    public static String encode(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException neverHappen) {
        }
        throw new AssertionError("will never happen");
    }


    public static String get(String url, Map<String, String> form) {
        return get(buildGetUrl(url, form));
    }

    /*
    *处理请求 get
    *默认重发3次
    * */
    public static String get(String url) {
        int retry = RETRY_COUNT;
        boolean retrySign = false;
        String rval = null;
        for (int i = 0; i < retry; i++) {
            HttpURLConnection con = null;
            try {
                try {
                    con = (HttpURLConnection) new URL(url).openConnection();
                    con.setReadTimeout(TIME_OUT);
                    con.setConnectTimeout(TIME_OUT);
                    con.setDoInput(true);
                    con.setDoOutput(true);
                    con.setRequestMethod("GET");
                    con.setRequestProperty("connection", "Keep-Alive");
                    con.setRequestProperty("Charsert", "UTF-8");
                    if (HttpURLConnection.HTTP_OK == con.getResponseCode()) {
                        rval = getMsg(con.getInputStream());
                        retrySign = false;
                    } else retrySign = true;
                } catch (IOException e) {
                    retrySign = true;
                    e.printStackTrace();
                } finally {
                    if (null != con) {
                        con.disconnect();
                    }
                    if (!retrySign) {
                        break;
                    }
                }
            } catch (RuntimeException e) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ignore) {
                }
            }
        }
        return rval;
    }

    private static String buildGetUrl(String serverUrl, Map<String, String> form) {
        StringBuilder requestUrl = new StringBuilder();
        requestUrl.append(serverUrl);
        requestUrl.append("?");
        String joinChar = "";
        for (Map.Entry<String, String> entry : form.entrySet()) {
            requestUrl.append(joinChar);
            requestUrl.append(entry.getKey());
            requestUrl.append("=");
            requestUrl.append(entry.getValue());
            joinChar = "&";
        }
        return requestUrl.toString();
    }

    public static String getMsg(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Constants.UTF8));
        StringBuffer buffer = new StringBuffer(3000);
        String line = reader.readLine();
        while (line != null) {
            buffer.append(line);
            line = reader.readLine();
        }
        reader.close();
        return buffer.toString();
    }
}
