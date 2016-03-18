package com.varela.api.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by lance on 12/29/2015.
 */

public class DeveloperApiLog implements Serializable {
    private long id=-1;

    private long apiId=-1;

    private long developerId=-1;

    private long count=0;

    private String dayTime;

    private Date createTime;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getApiId() {
        return apiId;
    }

    public void setApiId(long apiId) {
        this.apiId = apiId;
    }

    public long getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(long developerId) {
        this.developerId = developerId;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String getDayTime() {
        return dayTime;
    }

    public void setDayTime(String dayTime) {
        this.dayTime = dayTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "DeveloperApiLog{" +
                "id=" + id +
                ", apiId=" + apiId +
                ", developerId=" + developerId +
                ", count=" + count +
                ", dayTime='" + dayTime + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
