package com.varela.unionpay.pojo;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by guangping.lance on 2015-06-16.
 * 退款响应
 */
public class RefundResponse implements Serializable {

    private boolean success = false;

    private Map<String, String> resp;//返回值

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
