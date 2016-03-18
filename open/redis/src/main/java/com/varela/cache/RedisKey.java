package com.varela.cache;

import java.text.MessageFormat;

public class RedisKey {

    private RedisKey() {

    }

    public static final int REDIS_1S_EXPIRING = 1; // 1秒超时
    public static final int REDIS_5S_EXPIRING = 5; // 5秒超时
    public static final int REDIS_1M_EXPIRING = 60; // 1分钟超时
    public static final int REDIS_5M_EXPIRING = 300; // 5分钟超时
    public static final int REDIS_1H_EXPIRING = 3600; // 1小时超时
    public static final int REDIS_7000S_EXPIRING = 7000; // 7000秒
    public static final int REDIS_3H_EXPIRING = 10800; // 3小时过期
    public static final int REDIS_12H_EXPIRING = 43200; // 12小时超时
    public static final int REDIS_1D_EXPIRING = 86400; // 1天超时
    public static final int REDIS_1W_EXPIRING = 604800; // 1星期超时


    private static final String SESSION_KEY = "session:{0}";


    /**
     * sessionKey
     */
    public static String getSessionKey(String sessionId) {
        return MessageFormat.format(SESSION_KEY, sessionId);
    }


}
