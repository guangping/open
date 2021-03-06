package com.varela.enumerate;

import com.varela.pojo.BaseMsg;

/**
 * Created by lance on 12/3/2015.
 */
public enum Msg implements BaseMsg {

    SUCCESS(0, "成功"),
    //TODO 基础错误
    PARAM_IS_EMPTY(1, "参数{0}为空!"),
    PARAM_NOT_PATTERN(2, "参数{0}不合规范!"),
    PARAM_VALUE_ERROR(3, "参数{0}值不合法!"),
    PARAM_LENGTH_ERROR(4, "参数{0}长度错误!"),
    PARAM_ERROR(5, "参数{0}错误!"),
    PARAM_NOT_EXIST(6, "参数{0}不存在!"),

    //TODO 接入相关错误
    APPKEY_IS_NULL(100, "appKey为空!"),
    APPKEY_NOT_EXISTS(101, "appKey非法!"),
    SIGN_IS_NULL(102, "签名为空!"),
    SIGN_ERROR(103, "签名错误!"),
    NOT_UNAUTHORIZED(104, "没有权限!"),
    OVERRUN(105, "调用次数超限!"),
    VALID_TIME(106, "请求时间戳不在有效期内!"),
    APPKEY_DISABLE(107, "接入者不可用!"),


    //TODO Content-Type 类型错误
    CONTENT_TYPE_ERROR(200, "Content-Type类型错误!"),


    ERROR(9999, "服务器繁忙,请稍后再试!");


    private final int code;

    private final String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


    Msg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
