package com.varela.open.params.base;

import com.varela.open.RopResponse;

/**
 * Created with IntelliJ IDEA.
 * User: guangping
 * Date: 2014-06-27 16:42
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseRopResponse implements RopResponse {

    private boolean result = false;

    private String code;

    private String msg;


    public boolean isResult() {
        return result;
    }


    public String getCode() {
        return code;
    }


    public String getMsg() {
        return msg;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
