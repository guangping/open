package com.redis.test;

import com.varela.cache.JedisClusterFactory;
import com.varela.cache.RedisCache;
import com.varela.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * Created by lance on 2016/4/18.
 */
@ContextConfiguration({
        "classpath:applicationContext-test.xml",
        "classpath:spring/applicationContext-redis-cluster-new.xml"
})
public class ClusterTest extends AbstractTestNGSpringContextTests {


    @Autowired
    private RedisCache redisCache;

    @Test(invocationCount = 200)
    public void set(){
        this.redisCache.set(RandomUtil.getRandomStr(),RandomUtil.getRandomNum());
    }


}
