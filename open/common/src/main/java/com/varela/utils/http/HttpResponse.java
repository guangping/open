package com.varela.utils.http;

import java.io.Serializable;

/**
 * Created by lance on 9/17/2015.
 */
public class HttpResponse implements Serializable {

    private static final long serialVersionUID = 6024295049070570089L;

    private boolean success = false;

    private String result;

    private int code = -1;

    private String msg;

    public void setHttpMsg(HttpMsg httpMsg) {
        code = httpMsg.getCode();
        msg = httpMsg.getMsg();
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        if (code == 0) {
            success = true;
        }
        return success;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
