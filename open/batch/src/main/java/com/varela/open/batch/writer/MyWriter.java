package com.varela.open.batch.writer;

import com.varela.open.batch.pojo.Order;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

/**
 * Created by lance on 2016/1/28.
 */
public class MyWriter<Order> implements ItemWriter<Order> {
    @Override
    public void write(List<? extends Order> list) throws Exception {
        //入库逻辑
    }
}
