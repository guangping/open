package com.varela.unionpay.pojo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by guangping.lance on 2015-06-16.
 * 退款请求
 */
public class RefundRequest implements Serializable {

    //原订单支付成功后获取的流水号
    private String qn;

    //退款金额分
    private long orderAmount;

    //退款交易订单号
    private String refundNo;

    //额外透传参数
    private Map<String,String> extraParam=new HashMap<String, String>();


    public Map<String, String> getExtraParam() {
        return extraParam;
    }

    public void setExtraParam(Map<String, String> extraParam) {
        this.extraParam = extraParam;
    }

    public String getRefundNo() {
        return refundNo;
    }

    public void setRefundNo(String refundNo) {
        this.refundNo = refundNo;
    }

    public String getQn() {
        return qn;
    }

    public void setQn(String qn) {
        this.qn = qn;
    }

    public double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(long orderAmount) {
        this.orderAmount = orderAmount;
    }
}
