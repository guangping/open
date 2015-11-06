package com.varela.entity.sys;

import com.varela.entity.BaseEntity;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * Created by lance on 11/6/2015.
 */
@Alias("SysUser")
public class SysUser extends BaseEntity implements Serializable {

    private String name;

    private String mobile;

    private String password;

    private String email;

    private int role=0;

    private int sort=0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
