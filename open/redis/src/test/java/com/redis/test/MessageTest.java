package com.redis.test;

import com.varela.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * Created by lance on 2016/4/19.
 */
@ContextConfiguration({
        "classpath:applicationContext-test.xml",
        "classpath:spring/applicationContext-redis-message.xml"
})
public class MessageTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test(invocationCount = 20)
    public void send() {
        try {
            this.redisTemplate.convertAndSend("chatroom", RandomUtil.getRandomStr());
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
