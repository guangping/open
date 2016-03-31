package com.redis.test;

import com.varela.cache.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * Created by lance on 2016/3/31.
 */
@ContextConfiguration("classpath:applicationContext-sentinels.xml")
public class SentinelsTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private RedisCache redisCache;

    @Test
    public void set(){
        this.redisCache.set("222","ffff");

    }

    @Test
    public void get(){
        System.out.println(this.redisCache.get("mac"));
    }


}
