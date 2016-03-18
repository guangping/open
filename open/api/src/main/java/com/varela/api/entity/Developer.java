package com.varela.api.entity;

import java.io.Serializable;
import java.util.Date;


public class Developer implements Serializable {


    private long id=-1;

    private String appKey;// 接入ID

    private String contacts;// 联系人

    private String mobile;

    private String description;

    private String secret;// 接入秘钥

    private int state ;

    private int type;

    private Date createTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "id=" + id +
                ", appKey='" + appKey + '\'' +
                ", contacts='" + contacts + '\'' +
                ", mobile='" + mobile + '\'' +
                ", description='" + description + '\'' +
                ", secret='" + secret + '\'' +
                ", state=" + state +
                ", type=" + type +
                ", createTime=" + createTime +
                '}';
    }
}
