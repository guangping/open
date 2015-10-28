package com.varela.unionpay.pojo;

import java.io.Serializable;

/**
 * Created by guangping.lance on 2015-06-16.
 */
public class RefundRequest implements Serializable {


	private String qn;

	//分
	private long orderAmount;

	private String orderId;


	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
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
