package com.varela.test;

import com.varela.wechat.pojo.VoucherResult;
import com.varela.wechat.service.WeChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * Created by lance on 10/27/2015.
 */
@ContextConfiguration("classpath:applicationContext.xml")
public class WeChatTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private WeChatService weChatService;


    @Test
    public void token(){
        String appId="wx78c2cbb272f17c65";
        VoucherResult result=this.weChatService.getAccessToken(appId);
        System.out.println(result);
    }




}
