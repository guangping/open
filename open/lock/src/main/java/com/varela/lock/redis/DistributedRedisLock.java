package com.varela.lock.redis;

import com.varela.lock.DistributedLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created by lance on 11/27/2015.
 */
public class DistributedRedisLock implements DistributedLock {

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public boolean getLock(String key) throws Exception {
        return false;
    }

    @Override
    public void releaseLock(String key) {

    }
}
