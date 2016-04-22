package com.varela.utils.http;

/**
 * Created by lance on 12/3/2015.
 */
public enum HttpMsg {

    SUCCESS(0, "成功"),
    TIME_OUT(999999, "请求超时");


    private final int code;

    private final String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


    HttpMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
