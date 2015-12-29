package com.api.entity;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by lance on 12/29/2015.
 */
@Alias("DeveloperApiTimeLog")
public class DeveloperApiTimeLog implements Serializable {
    private long id=-1;

    private long apiId=-1;

    private long developerId=-1;

    private Date createTime;

    private long time=0;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "DeveloperApiTimeLog{" +
                "id=" + id +
                ", apiId=" + apiId +
                ", developerId=" + developerId +
                ", createTime=" + createTime +
                ", time=" + time +
                '}';
    }
}
