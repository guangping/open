package com.varela.pojo;

import com.varela.enumerate.APIMsg;

import java.io.Serializable;

public class APIResult implements Serializable {

    private static final long serialVersionUID = -727491457719169035L;
    private int code = -1;
    private String message;
    private Object result;

    private boolean success = false;

    public boolean isSuccess() {
        if (code == 0) {
            success = true;
        }
        return success;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(APIMsg msg) {
        this.code = msg.getCode();
        this.message = msg.getMsg();
    }

    public void setMsg(APIMsg msg, Object result) {
        this.code = msg.getCode();
        this.message = msg.getMsg();
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }


    @Override
    public String toString() {
        return "APIResult [code=" + code + ", message=" + message + ", result="
                + result + "]";
    }
}