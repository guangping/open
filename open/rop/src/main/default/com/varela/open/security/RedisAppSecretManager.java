package com.varela.open.security;

import com.varela.cache.RedisCache;
import com.varela.cache.RedisKey;
import com.varela.entity.Developer;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by lance on 9/29/2015.
 */
public class RedisAppSecretManager implements AppSecretManager {

    @Autowired
    private RedisCache redisCache;

    public String getSecret(String appKey) {
        String key = RedisKey.getAccessAppKey(appKey);
        Developer developer = this.redisCache.getObj(key, Developer.class);
        return (null != developer) ? developer.getAccessSecret() : null;
    }

    public boolean isValidAppKey(String appKey) {
        String key = RedisKey.getAccessAppKey(appKey);
        Developer developer = this.redisCache.getObj(key, Developer.class);
        return (null != developer) ? true : false;
    }
}
