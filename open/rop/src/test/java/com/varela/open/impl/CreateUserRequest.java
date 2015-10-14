/**
 * 版权声明：中图一购网络科技有限公司 版权所有 违者必究 2012
 * 日    期：12-2-29
 */
package com.varela.open.impl;

import com.varela.open.annotation.IgnoreSign;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;


public class CreateUserRequest {

    @Pattern(regexp = "\\w{4,30}")
    private String userName;

    @Valid
    private Addresss address;

    @IgnoreSign
    private String remark;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Addresss getAddress() {
        return address;
    }

    public void setAddress(Addresss address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

