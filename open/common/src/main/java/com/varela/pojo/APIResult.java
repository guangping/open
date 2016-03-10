package com.varela.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.varela.enumerate.Msg;

import java.io.Serializable;

public class APIResult implements Serializable {

    private static final long serialVersionUID = -727491457719169035L;
    private int code = -1;
    private String message;
    private Object data;

    @JSONField(serialize = false)
    private boolean success = false;

    public boolean isSuccess() {
        if (code == 0) {
            success = true;
        }
        return success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(Msg msg) {
        this.code = msg.getCode();
        this.message = msg.getMsg();
    }

    public void setMsg(Msg msg, Object data) {
        this.code = msg.getCode();
        this.message = msg.getMsg();
        this.data = data;
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
                + data + "]";
    }
}
