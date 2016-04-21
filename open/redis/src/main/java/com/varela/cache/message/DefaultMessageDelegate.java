package com.varela.cache.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by lance on 2016/4/19.
 * 消息处理
 */
public class DefaultMessageDelegate implements MessageDelegate {
    private Logger logger= LoggerFactory.getLogger(DefaultMessageDelegate.class);

    @Override
    public void handleMessage(String message) {
         logger.info("msg:{}",message);
    }

    @Override
    public void handleMessage(Map message) {
        logger.info("msg:{}",message);
    }

    @Override
    public void handleMessage(byte[] message) {
        logger.info("msg:{}",message);
    }

    @Override
    public void handleMessage(Serializable message) {
        System.out.println("msg:"+message);
        logger.info("msg:{}",message);
    }

    @Override
    public void handleMessage(Serializable message, String channel) {
        logger.info("msg:{},channel:{}",message,channel);
    }
}
