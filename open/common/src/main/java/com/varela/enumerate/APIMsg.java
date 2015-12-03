package com.varela.enumerate;

/**
 * Created by lance on 12/3/2015.
 */
public enum APIMsg {

    Success(0, "成功"),
    ERROR(9999, "未知错误!");


    private final int code;

    private final String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


    APIMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
