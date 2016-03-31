package com.redis.test;

import com.alibaba.fastjson.JSON;
import com.varela.cache.RedisCache;
import com.varela.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by lance on 10/14/2015.
 */
@ContextConfiguration("classpath:applicationContext-test.xml")
public class CacheTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private RedisCache redisCache;

    @Test
    public void keys() {
        List<String> list = this.redisCache.keys();
        System.out.println(JSON.toJSONString(list));

        list = this.redisCache.keys("insurance:cargo:type");
        System.out.println(JSON.toJSONString(list));
    }

    @Test
    public void setList() {
        for (int i = 0; i < 100; i++) {
            this.redisCache.set(RandomUtil.getRandomStr(), RandomUtil.getRandomNum());
        }

    }

    @Test(threadPoolSize = 3,invocationCount = 100)
    public void get(){
        System.out.println(this.redisCache.get("aa"));
    }


    @Test
    public void push() {
        String key = "city:list";
        long s = 0;
        for (int i = 0; i < 200; i++) {
            s = this.redisCache.lPush(key, RandomUtil.getRandomStr());
            System.out.println(s);
        }
    }
}
