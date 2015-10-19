package com.varela.rabbitmq.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * Created by lance on 10/14/2015.
 */

public class MQTestConsumer implements MessageListener {

    private Logger logger = LoggerFactory.getLogger(MQTestConsumer.class);

    public void onMessage(Message message) {
        byte[] msg = message.getBody();
        String str = new String(msg);
      //  System.out.println("consumer 消息"+str);
        logger.info("consumer 消息 {}", str);
    }
}
