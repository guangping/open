package com.varela.open.impl;

import com.varela.cache.RedisCache;
import com.varela.open.security.ServiceAccessController;
import com.varela.open.session.Session;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by lance on 9/29/2015.
 */
public class RedisServiceAccessController implements ServiceAccessController {

    @Autowired
    private RedisCache redisCache;

    public boolean isAppGranted(String appKey, String method, String version) {

        return true;
    }

    public boolean isUserGranted(Session session, String method, String version) {
        return true;
    }
}
