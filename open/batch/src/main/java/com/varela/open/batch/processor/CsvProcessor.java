package com.varela.open.batch.processor;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

/**
 * Created by lance on 2016/1/28.
 */
public class CsvProcessor<Order> implements ItemProcessor<Order, Order> {
    private Logger logger = LoggerFactory.getLogger(CsvProcessor.class);


    @Override
    public Order process(Order order) throws Exception {
        logger.info("线程:{},处理中:{}", Thread.currentThread().getId(),JSON.toJSONString(order));

        return order;
    }
}
