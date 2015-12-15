package com.api.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lance on 12/15/2015.
 */
@Component
public class DefaultAppSecretManager implements AppSecretManager {

    private static Map<String, String> app = new HashMap<String, String>();

    static {
        app.put("00001", "123");
        app.put("00002", "123");
        app.put("00003", "123");
        app.put("00004", "123");
    }

    @Override
    public String getSecret(String appKey) {
        return app.get(appKey);
    }

    @Override
    public boolean isValidAppKey(String appKey) {
        String val = app.get(appKey);
        return StringUtils.isNotBlank(val) ? true : false;
    }
}
