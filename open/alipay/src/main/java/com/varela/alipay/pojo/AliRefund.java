package com.varela.alipay.pojo;

import java.io.Serializable;

/**
 * Created by lance on 10/27/2015.
 * 退款
 */
public class AliRefund implements Serializable {

    /**
     * 批次号
     * 格式:退款日期(8位)+流水号(3-24位)
     */
    private String batchNo;

    //支付宝交易号
    private String tradeNo;

    //退款金额
    private double price = 0;

    //退款原因
    private String reason;

    // 通知url 可选
    private String notifyUrl;

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }


}
