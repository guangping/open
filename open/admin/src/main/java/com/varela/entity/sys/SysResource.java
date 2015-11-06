package com.varela.entity.sys;

import com.varela.entity.BaseEntity;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * Created by lance on 11/6/2015.
 */
@Alias("SysResource")
public class SysResource extends BaseEntity implements Serializable {

    private String name;

    private String url;

    private int sort = 0;

    private Serializable parentId;

    private String identity;

    private int isShow = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public Serializable getParentId() {
        return parentId;
    }

    public void setParentId(Serializable parentId) {
        this.parentId = parentId;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public int getIsShow() {
        return isShow;
    }

    public void setIsShow(int isShow) {
        this.isShow = isShow;
    }
}
