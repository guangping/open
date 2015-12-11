package com.varela.alipay.pojo;

import java.io.Serializable;

/**
 * Created by lance on 8/20/2015.
 */
public class AliPay implements Serializable {

    private static final long serialVersionUID = -8924677480814206147L;

    private String outTradeNo;

    private double price;

    private String notifyUrl;// 通知url 可选

    private String returnUrl;// 返回商户页面url 可选

    private String subject;// 订单描述

    //TODO 支付超时
    private int payTimeOut = 15 * 24 * 60;

    public int getPayTimeOut() {
        return payTimeOut;
    }

    public void setPayTimeOut(int payTimeOut) {
        this.payTimeOut = payTimeOut;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }
}
