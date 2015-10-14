package com.varela.entity;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;

@Alias("Developer")
public class Developer implements Serializable {


    private String id;
    private String accessId;// 接入ID
    private String contacts;// 联系人
    private String mobile;
    private String description;
    private String accessSecret;// 接入秘钥
    private String state ;
    private String levelId;// 等级ID
    private Date createTime;

    private Level level;

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccessId() {
        return accessId;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId;
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

    public String getAccessSecret() {
        return accessSecret;
    }

    public void setAccessSecret(String accessSecret) {
        this.accessSecret = accessSecret;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "id='" + id + '\'' +
                ", accessId='" + accessId + '\'' +
                ", contacts='" + contacts + '\'' +
                ", mobile='" + mobile + '\'' +
                ", description='" + description + '\'' +
                ", accessSecret='" + accessSecret + '\'' +
                ", state='" + state + '\'' +
                ", levelId='" + levelId + '\'' +
                ", createTime=" + createTime +
                ", level=" + level +
                '}';
    }
}
