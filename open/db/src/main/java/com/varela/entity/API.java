package com.varela.entity;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;

@Alias("API")
public class API implements Serializable {


    private String id;

    private String version;

    private String method;

    private String title;

    private String state;

    protected Date createTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "API{" +
                "id='" + id + '\'' +
                ", version='" + version + '\'' +
                ", method='" + method + '\'' +
                ", title='" + title + '\'' +
                ", state='" + state + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
