package com.varela.log.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by lance on 11/9/2015.
 * 日志记录
 */
public class RecordLog implements Serializable {

    private Date createTime;

    private Serializable id;

    /**
     * 操作人编码
     */
    private Serializable userId;

    /**
     * 操作人
     */
    private String userName;

    /**
     * 事件
     **/
    private String event;

    /**
     * 参数
     */
    private String param;

    /**
     * 描述信息
     */
    private String description;

    private String ip;


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Serializable getId() {
        return id;
    }

    public void setId(Serializable id) {
        this.id = id;
    }

    public Serializable getUserId() {
        return userId;
    }

    public void setUserId(Serializable userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "RecordLog{" +
                "createTime=" + createTime +
                ", id=" + id +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", event='" + event + '\'' +
                ", param='" + param + '\'' +
                ", description='" + description + '\'' +
                ", ip='" + ip + '\'' +
                '}';
    }
}
