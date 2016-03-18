package com.varela.api.pojo;

import java.text.MessageFormat;

/**
 * Created by lance on 2016/3/18.
 */
public class RedisApiKey {

    private RedisApiKey(){}


    //TODO 开发者
    private static final String DEVELOPER_ID = "developer:id:{0}";
    private static final String DEVELOPER_APPKEY = "developer:appKey:{0}";

    //TODO API
    private static final String API_ID = "api:id:{0}";
    private static final String API_METHOD = "api:method:{0}";

    //TODO developerApi
    private static final String DEVELOPER_API = "developerApi:developer:{0}:api:{1}";


    public static String getDeveloperId(String id) {
        return MessageFormat.format(DEVELOPER_ID, id);
    }

    public static String getDeveloperAppkey(String appKey) {
        return MessageFormat.format(DEVELOPER_APPKEY, appKey);
    }

    public static String getApiId(String id) {
        return MessageFormat.format(API_ID, id);
    }

    public static String getApiMethod(String method) {
        return MessageFormat.format(API_METHOD, method);
    }

    public static String getDeveloperApi(String appKey, String method) {
        return MessageFormat.format(DEVELOPER_API, appKey, method);
    }
}
