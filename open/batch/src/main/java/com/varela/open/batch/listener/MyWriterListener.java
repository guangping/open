package com.varela.open.batch.listener;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemWriteListener;

import java.util.List;

/**
 * Created by lance on 2016/1/29.
 */
public class MyWriterListener implements ItemWriteListener {

    private Logger logger = LoggerFactory.getLogger(MyWriterListener.class);

    @Override
    public void beforeWrite(List list) {

    }

    @Override
    public void afterWrite(List list) {

    }

    @Override
    public void onWriteError(Exception e, List list) {
        logger.error("写文件出错:{},数据:{}", e, JSON.toJSONString(list));
    }
}
