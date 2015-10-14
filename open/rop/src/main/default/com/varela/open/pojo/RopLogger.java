package com.varela.open.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: guangping
 * Date: 2014-06-27 11:14
 * To change this template use File | Settings | File Templates.
 * <p/>
 * 请求响应日志实体类
 */
public class RopLogger implements Serializable {
    private String method;

    private String version;

    private String appKey;

    private String ip;

    private Date serviceBeginTime = new Date(System.currentTimeMillis());

    private Date serviceEndTime = new Date(System.currentTimeMillis());

    private String requestContent;

    private String responseContent;

    private String sessionId;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getServiceBeginTime() {
        return serviceBeginTime;
    }

    public void setServiceBeginTime(Date serviceBeginTime) {
        this.serviceBeginTime = serviceBeginTime;
    }

    public Date getServiceEndTime() {
        return serviceEndTime;
    }

    public void setServiceEndTime(Date serviceEndTime) {
        this.serviceEndTime = serviceEndTime;
    }

    public String getRequestContent() {
        return requestContent;
    }

    public void setRequestContent(String requestContent) {
        this.requestContent = requestContent;
    }

    public String getResponseContent() {
        return responseContent;
    }

    public void setResponseContent(String responseContent) {
        this.responseContent = responseContent;
    }
}
