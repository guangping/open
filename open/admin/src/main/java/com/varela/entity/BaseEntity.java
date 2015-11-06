package com.varela.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by lance on 11/6/2015.
 */
public class BaseEntity implements Serializable {
    private Serializable id;

    private Date createTime;

    private Date modifyTime;

    public Serializable getId() {
        return id;
    }

    public void setId(Serializable id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
