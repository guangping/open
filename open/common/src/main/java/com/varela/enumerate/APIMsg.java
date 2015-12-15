package com.varela.enumerate;

/**
 * Created by lance on 12/3/2015.
 */
public enum APIMsg {

    Success(0, "成功"),
    PARAM_IS_EMPTY(1, "参数{0}为空!"),
    PARAM_NOT_PATTERN(2, "参数{0}不合规范!"),
    PARAM_VALUE_ERROR(3, "参数{0}值不合法!"),
    PARAM_LENGTH_ERROR(4, "参数{0}长度错误!"),
    PARAM_ERROR(5, "参数{0}错误!"),
    PARAM_NOT_EXIST(6, "参数{0}不存在!"),

    //TODO 接入相关错误
    APPKEY_IS_NULL(100, "appKey为空!"),
    APPKEY_NOT_EXISTS(101, "appKey不存在!"),
    SIGN_IS_NULL(102, "签名为空!"),
    SIGN_ERROR(103, "签名错误!"),
    NOT_UNAUTHORIZED(104, "没有相应权限!"),
    OVERRUN(105, "调用次数超限!"),
    VALID_TIME(106, "请求时间戳不在有效期内!"),


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
