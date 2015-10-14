package com.varela.open.session;

import com.varela.cache.RedisCache;
import com.varela.cache.RedisKey;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by lance on 9/29/2015.
 */
public class RedisSessionManager implements SessionManager {

    @Autowired
    private RedisCache redisCache;

    public void addSession(String sessionId, Session session) {
        String key = RedisKey.getSessionKey(sessionId);
        this.redisCache.set(key, session);
    }

    public Session getSession(String sessionId) {
        String key = RedisKey.getSessionKey(sessionId);
        return this.redisCache.getObj(key, Session.class);
    }

    public void removeSession(String sessionId) {
        String key = RedisKey.getSessionKey(sessionId);
        this.redisCache.delete(key);
    }
}
