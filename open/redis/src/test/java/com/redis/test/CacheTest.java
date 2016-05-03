package com.redis.test;

import com.alibaba.fastjson.JSON;
import com.varela.cache.RedisCache;
import com.varela.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

/**
 * Created by lance on 10/14/2015.
 */
@ContextConfiguration({
        "classpath:applicationContext-test.xml",
        "classpath:spring/applicationContext-redis.xml"
})
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

    /**
     * zset
     * */
    @Test
    public void setZSet(){
        String zSet="myZSet";
        for(int i=0;i<10;i++){
            this.redisCache.setZSet(zSet, RandomUtil.getRandomStr());
        }
    }

    @Test
    public void setZSetScore(){
        String zSet="myZSet";
        this.redisCache.setZSet(zSet,"XHIX5HUO8MQK9MI18XAFMLLVKHA8MONJ",2);
    }

    @Test
    public void getZSetSize(){
        String zSet="myZSet";
        System.out.println(this.redisCache.getZSetSize(zSet));
    }

    @Test
    public void getReverseZSetSize(){
        String zSet="myZSet";
        Set<String> set=this.redisCache.getReverseZSet(zSet,0,-1);
        for(String obj:set){
            System.out.println(obj);
        }
    }
}
