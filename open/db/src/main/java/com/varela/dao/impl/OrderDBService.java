package com.varela.dao.impl;

import com.varela.dao.BaseDaoImpl;
import com.varela.entity.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lance on 11/4/2015.
 */
@Repository
public class OrderDBService extends BaseDaoImpl<Order> {

    public List<Order> queryList(Order order) {
        return this.getSqlSession().selectList("Order.queryList", order);
    }

}
