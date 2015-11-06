package com.varela.entity.sys;

import com.varela.entity.BaseEntity;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * Created by lance on 11/6/2015.
 */
@Alias("SysRole")
public class SysRole extends BaseEntity implements Serializable {

    private String name;

    private int sort = 0;

    private int state = 0;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "SysRole{" +
                "name='" + name + '\'' +
                ", sort=" + sort +
                ", state=" + state +
                '}';
    }
}
