package com.varela.entity;

import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lance 2015-07-24 14:39
 */
@Alias("Level")
public class Level implements Serializable {

    private String id;

    @NotEmpty(message = "名称不能为空!")
    private String name;

    private long dayCallCount = 0;

    protected Date createTime;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getDayCallCount() {
        return dayCallCount;
    }

    public void setDayCallCount(long dayCallCount) {
        this.dayCallCount = dayCallCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Level{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", dayCallCount=" + dayCallCount +
                ", createTime=" + createTime +
                '}';
    }
}
