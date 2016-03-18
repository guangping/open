package com.varela.api.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * Created by lance on 2016/3/18.
 */
public class AccessLog implements Serializable {

    private String appKey;

    private String method;

    //参数
    private Map param;

    private long consumeTime;//耗时ms

    private Date createTime=new Date(System.currentTimeMillis());

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Map getParam() {
        return param;
    }

    public void setParam(Map param) {
        this.param = param;
    }

    public long getConsumeTime() {
        return consumeTime;
    }

    public void setConsumeTime(long consumeTime) {
        this.consumeTime = consumeTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "AccessLog{" +
                "appKey='" + appKey + '\'' +
                ", method='" + method + '\'' +
                ", param=" + param +
                ", consumeTime=" + consumeTime +
                ", createTime=" + createTime +
                '}';
    }
}
