package com.api.security;

import org.springframework.stereotype.Component;

/**
 * Created by lance on 12/15/2015.
 */
@Component
public class DefaultAppSecretManager implements AppSecretManager {
    @Override
    public String getSecret(String appKey) {
        return null;
    }

    @Override
    public boolean isValidAppKey(String appKey) {
        return false;
    }
}
