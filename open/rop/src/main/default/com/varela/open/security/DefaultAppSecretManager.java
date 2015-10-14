package com.varela.open.security;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: guangping
 * Date: 2014-06-27 11:08
 * To change this template use File | Settings | File Templates.
 */
public class DefaultAppSecretManager implements AppSecretManager {
    private static Map<String, String> appKeySecretMap = new HashMap<String, String>();

    static {
        appKeySecretMap.put("00001", "123");
        appKeySecretMap.put("00002", "123");
        appKeySecretMap.put("00003", "123");
    }


    public String getSecret(String appKey) {
        return appKeySecretMap.get(appKey);
    }


    public boolean isValidAppKey(String appKey) {
        return getSecret(appKey) != null;
    }
}
