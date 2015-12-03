package com.varela.wechat.pojo;

import java.io.Serializable;

public class PayResult implements Serializable {

	private static final long serialVersionUID = 6082360505306131929L;
	
	private int errorCode;
	private String message;
	private Object result;

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public void setMsg(WeChatMsg msg) {
		this.errorCode = msg.getErrorCode();
		this.message = msg.getMsg();
	}

	public void setMsg(WeChatMsg msg, Object result) {
		this.errorCode = msg.getErrorCode();
		this.message = msg.getMsg();
		this.result = result;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "APIResult [errorCode=" + errorCode + ", message=" + message
				+ ", result=" + result + "]";
	}
}
