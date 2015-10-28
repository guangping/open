package com.varela.unionpay.pojo;

import java.io.Serializable;

/**
 * Created by lance on 10/28/2015.
 * 支付请求响应
 */
public class UnionPayResponse implements Serializable {

    public boolean success = false;

    //交易号
    private String tn;

    private String respCode;

    private String respMsg;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getTn() {
        return tn;
    }

    public void setTn(String tn) {
        this.tn = tn;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }
}
