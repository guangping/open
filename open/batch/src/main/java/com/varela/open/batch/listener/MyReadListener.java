package com.varela.open.batch.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemReadListener;

/**
 * Created by lance on 2016/1/29.
 */
public class MyReadListener implements ItemReadListener {

    private Logger logger = LoggerFactory.getLogger(MyReadListener.class);

    @Override
    public void beforeRead() {
        //logger.info(" read listener start");
    }

    @Override
    public void afterRead(Object o) {
       // logger.info(" read listener end");
    }

    @Override
    public void onReadError(Exception e) {
        logger.error(" read listener error：{}", e);
    }
}
