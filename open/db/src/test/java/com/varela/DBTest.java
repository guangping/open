package com.varela;

import com.varela.entity.User;
import com.varela.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * Created by lance on 10/14/2015.
 */
@ContextConfiguration("classpath:applicationContext-db.xml")
public class DBTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private UserService userService;


    @Test
    public void insert() {
        User user = new User();
        user.setName("东海11");
        this.userService.save(user);
        System.out.println("id==>" + user.getId());
    }


}
