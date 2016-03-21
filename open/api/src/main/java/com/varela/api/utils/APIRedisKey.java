package com.varela.api.utils;

import java.text.MessageFormat;

/**
 * Created by lance on 12/21/2015.
 */
public class APIRedisKey {


    //一分钟调用次数
    public static final int MINUTES_COUNT = 100;

    //TODO api
    private static final String API_KEY = "api:api:id:{0}";

    private static final String API_METHOD_KEY = "api:api:method:{0}";

    //TODO developer
    private static final String DEVELOPER_KEY = "api:developer:id:{0}";

    //TODO developer appKey
    private static final String DEVELOPER_APP_KEY = "api:developer:appKey:{0}";

    private static final String DEVELOPERID_APPID_KEY = "api:developerApi:appId:{0}:developerId:{1}";

    private static final String APPKEY_METHOD_KEY = "api:developerApi:appKey:{0}:method:{1}";

    private static final String API_LIMIT = "api:limit:appKey:{0}";

    private static final String API_METHOD_LIMIT = "api:limit:appKey:{0}:method:{1}";

    // ip访问次数
    private static final String IP_COUNT = "ip:count:{0}";


    public static String getApiKey(String id) {
        return MessageFormat.format(API_KEY, id);
    }

    public static String getDeveloperKey(String id) {
        return MessageFormat.format(DEVELOPER_KEY, id);
    }

    public static String getDeveloperAppKey(String appKey) {
        return MessageFormat.format(DEVELOPER_APP_KEY, appKey);
    }

    public static String getApiMethodKey(String method) {
        return MessageFormat.format(API_METHOD_KEY, method);
    }

    public static String getDeveloperidAppidKey(String appId, String developerId) {
        return MessageFormat.format(DEVELOPERID_APPID_KEY, appId, developerId);
    }

    public static String getApiLimit(String appKey) {
        return MessageFormat.format(API_LIMIT, appKey);
    }

    public static String getApiMethodLimit(String appKey, String method) {
        return MessageFormat.format(API_METHOD_LIMIT, appKey, method);
    }

    public static String getAppkeyMethodKey(String appKey, String method) {
        return MessageFormat.format(APPKEY_METHOD_KEY, appKey, method);
    }

    public static String getIpCount(String ip) {
        return MessageFormat.format(IP_COUNT, ip);
    }
}
