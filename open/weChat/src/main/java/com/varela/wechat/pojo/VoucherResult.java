package com.varela.wechat.pojo;

import java.io.Serializable;

/**
 * Created by lance on 8/24/2015.
 */
public class VoucherResult implements Serializable {

    private boolean success = false;

    private String accessToken;// access_token

    private int expiresIn = 0;//秒

    private String errcode;

    private String errmsg;

    private String ticket;//调用微信JS接口的临时票据

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }


    @Override
    public String toString() {
        return "VoucherResult{" +
                "success=" + success +
                ", accessToken='" + accessToken + '\'' +
                ", expiresIn=" + expiresIn +
                ", errcode='" + errcode + '\'' +
                ", errmsg='" + errmsg + '\'' +
                ", ticket='" + ticket + '\'' +
                '}';
    }
}
