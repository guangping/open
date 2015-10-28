package com.varela.unionpay.pojo;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by guangping.lance on 2015-06-16.
 */
public class RefundResponse implements Serializable {
	private static final long serialVersionUID = 1118848128252396308L;

	private boolean success = false;

	private Map<String, String> resp;

	public Map<String, String> getResp() {
		return resp;
	}

	public void setResp(Map<String, String> resp) {
		this.resp = resp;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}
