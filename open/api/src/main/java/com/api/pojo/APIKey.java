package com.api.pojo;

/**
 * Created by lance on 12/8/2015.
 */
public class APIKey {


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


    }
}
