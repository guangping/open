package com.varela.lock.redis;

import com.varela.lock.DistributedLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * Created by lance on 11/27/2015.
 */
public class DistributedRedisLock implements DistributedLock {

    @Autowired
    private RedisTemplate redisTemplate;


    /**
     *
     * */
    @Override
    public boolean getLock(String key) throws Exception {
        boolean sign = false;
        try {
            sign = tryLock(key);
        } catch (Exception e) {
            sign = true;
            e.printStackTrace();
        }
        return sign;
    }

    @Override
    public void releaseLock(String key) {
        this.redisTemplate.delete(key);
    }

    /**
     * 获取锁
     */
    private boolean tryLock(String key) {
        final RedisSerializer<String> serializer = this.redisTemplate.getStringSerializer();
        Boolean sign = (Boolean) this.redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                boolean val = redisConnection.setNX(serializer.serialize(key), serializer.serialize("lock"));
                return val;
            }
        });
        return sign;
    }
}
