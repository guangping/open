package com.varela.api.pojo;

import java.io.Serializable;

/**
 * Created by lance on 12/18/2015.
 */
public class APIRequest implements Serializable {

    private String appKey;

    private String sign;

    private long timestamp;

    private String method;


    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return "APIRequest{" +
                "appKey='" + appKey + '\'' +
                ", sign='" + sign + '\'' +
                ", timestamp=" + timestamp +
                ", method='" + method + '\'' +
                '}';
    }
}
