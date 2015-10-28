package com.varela.test;

import com.alibaba.fastjson.JSON;
import com.varela.unionpay.pojo.UnionPayRequest;
import com.varela.unionpay.pojo.UnionPayResponse;
import com.varela.unionpay.util.UnionPayUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * Created by lance on 10/28/2015.
 */
@ContextConfiguration("classpath:applicationContext.xml")
public class UnionPayTest extends AbstractTestNGSpringContextTests {

    @Test
    public void getPayId() {
        UnionPayRequest request = new UnionPayRequest();
        request.setOrderAmount(100);
        request.setOrderNumber("" + System.currentTimeMillis());
        request.setOrderDescription("订单支付");
        UnionPayResponse response = UnionPayUtils.getTradNumber(request);
        System.out.println(JSON.toJSONString(response));
    }
}
