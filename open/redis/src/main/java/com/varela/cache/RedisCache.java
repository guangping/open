package com.varela.cache;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by guangping.lance on 2015-04-30.
 */
@Component
public class RedisCache {

    private static final Logger logger = LoggerFactory
            .getLogger(RedisCache.class);

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    public boolean set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
        return true;
    }

    /**
     * 存在返回false,否则返回true
     */
    public boolean setNx(String key, String value) {
        final RedisSerializer<String> serializer = this.redisTemplate.getStringSerializer();
        Boolean sign = (Boolean) this.redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                boolean val = redisConnection.setNX(serializer.serialize(key), serializer.serialize(value));
                return val;
            }
        });
        return sign;
    }


    public boolean set(final String key, Object value) {
        String str = JSON.toJSONString(value);
        this.set(key, str);
        return true;
    }

    public boolean set(final String key, Object value, int timeout) {
        String str = JSON.toJSONString(value);
        return this.set(key, str, timeout);
    }


    public boolean set(String key, String value, int timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
        return true;
    }


    public long incre(String key, long incre) {
        return redisTemplate.opsForValue().increment(key, incre);
    }


    public String get(final String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public <T> T getObj(String key, TypeReference<T> type) {
        String str = this.get(key);
        if (StringUtils.isBlank(str)) {
            return null;
        }
        return this.json2Object(str, type);
    }

    @SuppressWarnings("unchecked")
    public <T> T getObj(String key, Class<T> clazz) {
        if (clazz == null)
            return null;
        String str = this.get(key);
        if (clazz.getName().equals("java.util.List")) {
            return json2Object(str, new TypeReference<T>() {
            });
        } else if (clazz.getName().equals("java.lang.String")) {
            return (T) str;
        } else {
            return json2Object(str, clazz);
        }
    }

    private <T> T json2Object(String jsonString, Class<T> clazz) {
        try {
            return JSON.toJavaObject(JSON.parseObject((jsonString)), clazz);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * JSON串转换为Java泛型对象，可以是各种类型，此方法最为强大。用法看测试用例。
     *
     * @param <T>
     * @param jsonString JSON字符串
     * @param tr         TypeReference,例如: new TypeReference< List<FamousUser> >(){}
     * @return List对象列表
     */
    private <T> T json2Object(String jsonString, TypeReference<T> tr) {
        try {
            return JSON.parseObject(jsonString, tr);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }


    public boolean delete(String key) {
        redisTemplate.delete(key);
        return true;
    }


    public void flushAll() {
        redisTemplate.execute(new RedisCallback<Integer>() {
            public Integer doInRedis(RedisConnection redisConnection)
                    throws DataAccessException {
                redisConnection.flushDb();
                return 0;
            }
        });
    }


    public List<String> keys() {
        Set<String> set = redisTemplate.keys("*");
        return new ArrayList<String>(set);
    }

    public List<String> keys(String key) {
        Set<String> set = redisTemplate.keys(key + "*");
        return new ArrayList<String>(set);
    }

    public long setSet(String key, long seconds, String... values) {
        redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
        return redisTemplate.opsForSet().add(key, values);
    }

    public Set<String> getSet(String key) {
        return redisTemplate.opsForSet().members(key);
    }

}
