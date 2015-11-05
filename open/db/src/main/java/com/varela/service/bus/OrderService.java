package com.varela.service.bus;


import com.varela.dao.api.impl.OrderDBService;
import com.varela.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lance on 11/4/2015.
 */
@Service
public class OrderService {

    @Autowired
    private OrderDBService orderDBService;


    public List<Order> query(Order order) {
        return this.orderDBService.queryList(order);
    }
}
