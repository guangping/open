package com.varela.unionpay.pojo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lance on 10/28/2015.
 * 支付请求
 */
public class UnionPayRequest implements Serializable {

    //订单编码
    private String orderNumber;

    //描述
    private String orderDescription;

    private long orderAmount=0;

    //额外透传参数
    private Map<String,String> extraParam=new HashMap<String, String>();


    public Map<String, String> getExtraParam() {
        return extraParam;
    }

    public void setExtraParam(Map<String, String> extraParam) {
        this.extraParam = extraParam;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }

    public long getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(long orderAmount) {
        this.orderAmount = orderAmount;
    }
}
