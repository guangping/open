package com.varela.open;

import com.varela.entity.User;
import com.varela.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * Created by lance on 2016/3/28.
 */
@ContextConfiguration("classpath:applicationContext.xml")
public class DBTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private UserService userService;


    @Test
    public void queryById() {
        User user = this.userService.selectByPrimaryKey(30l);
        System.out.println(user);
    }

}
