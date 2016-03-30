package com.varela.mp.pojo;

import com.varela.pojo.BaseMsg;

/**
 * Created by lance on 2016/3/30.
 */
public enum MPMsg implements BaseMsg {
    SUCCESS(0, "ok"),
    PARAM_IS_EMPTY(1, "参数为空!"),
    PARAM_NOT_PATTERN(2, "参数不合规范!"),
    PARAM_VALUE_ERROR(3, "参数值不合法!"),
    PARAM_LENGTH_ERROR(4, "参数长度错误!"),
    PARAM_ERROR(5, "参数错误!"),
    PARAM_NOT_EXIST(6, "参数不存在!"),

    ERROR(9999, "服务器繁忙,请稍后再试!");


    private final int code;

    private final String msg;


    MPMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public int getCode() {
        return code;
    }
}
