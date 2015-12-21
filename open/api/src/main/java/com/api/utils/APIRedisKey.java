package com.api.utils;

import java.text.MessageFormat;

/**
 * Created by lance on 12/21/2015.
 */
public class APIRedisKey {

    //TODO api
    private static final String API_KEY = "api:api:id:{0}";

    //TODO developer
    private static final String DEVELOPER_KEY = "api:developer:id:{0}";

    //TODO developer appKey
    private static final String DEVELOPER_APP_KEY = "api:developer:appKey:{0}";


    public static String getApiKey(String id) {
        return MessageFormat.format(API_KEY, id);
    }

    public static String getDeveloperKey(String id) {
        return MessageFormat.format(DEVELOPER_KEY, id);
    }

    public static String getDeveloperAppKey(String appKey) {
        return MessageFormat.format(DEVELOPER_APP_KEY, appKey);
    }

}
