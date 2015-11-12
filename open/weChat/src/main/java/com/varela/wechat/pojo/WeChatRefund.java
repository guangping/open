package com.varela.wechat.pojo;

import java.io.Serializable;

/**
 * Created by lance on 11/3/2015.
 */
public class WeChatRefund implements Serializable {

    //微信订单号
    private String transactionId;

    //商户退款单号
    private String outRefundNo;

    //总金额 分
    private int totalFee;

    //退款金额 分
    private int refundFee;

    //操作员帐号 默认为商户号
    private String opUserId;

    //appId
    private String appId;

    private String mchId;


    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getOpUserId() {
        return opUserId;
    }

    public void setOpUserId(String opUserId) {
        this.opUserId = opUserId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getOutRefundNo() {
        return outRefundNo;
    }

    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;
    }

    public int getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(int totalFee) {
        this.totalFee = totalFee;
    }

    public int getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(int refundFee) {
        this.refundFee = refundFee;
    }
}
