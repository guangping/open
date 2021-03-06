package com.varela.api.pojo;

/**
 * Created by lance on 12/8/2015.
 */
public class APIKey {

    public static final int TIME=500;

    public static final int TIME_OUT=600;

    /**
     * ContentType
     * */
    public static class ContentType {
        public static final String CONTENT_TYPE = "Content-Type";
        public static final String X_WWW_FORM_URLENCODED = "application/x-www-form-urlencoded;charset=utf-8";
        public static final String JSON = "application/json;charset=UTF-8";
    }


    /**
     * 验证参数key
     */
    public static class ValidateKey {

        public static final String FORMAT = "format";

        //TODO appKey 	应用的唯一标识符
        public static final String APPKEY = "appKey";
        //TODO 内容签名
        public static final String SIGN = "sign";
        //TODO 时间戳 本请求的unix时间戳，用于确认请求的有效期 默认情况下，请求时间戳与服务器时间（北京时间）偏差大于600秒则会被拒绝
        public static final String TIMESTAMP = "timestamp";
        //TODO 方法名
        public static final String METHOD = "method";

        public static final String TOKEN="token";
    }

    //TODO API状态 0 禁用  1 可用
    public static final int API_STATE_DISABLE = 0;
    public static final int API_STATE_NORMAL = 1;

    //TODO 接入者状态 0 禁用  1 可用
    public static final int DEVELOPER_STATE_DISABLE = 0;
    public static final int DEVELOPER__STATE_NORMAL = 1;

    //TODO 接入者类型 0 普通  1 自己
    public static final int DEVELOPER_TYPE_0 = 0;
    public static final int DEVELOPER__TYPE_1 = 1;

    //ip访问次数
    public static final int IP_COUNT=150;

}
