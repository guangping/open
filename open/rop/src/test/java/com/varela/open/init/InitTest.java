package com.varela.open.init;

import com.varela.dao.impl.LevelDBService;
import com.varela.service.bus.TestService;
import com.varela.spring.SpringApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * Created by lance on 9/30/2015.
 */
@ContextConfiguration("classpath:applicationContext.xml")
public class InitTest extends AbstractTestNGSpringContextTests {


    @Autowired
    private TestService testService;


    @Test
    public void init() {
        this.testService.init();
    }


    @Test
    public void db() {
        LevelDBService levelDBService = SpringApplicationContext.getBean(LevelDBService.class);
        System.out.println(levelDBService);
    }

  /*  @Test
    public void save() {
        API api = new API();
        api.setMethod("user.getSession");
        api.setVersion(null);
        api.setTitle("获取session");

        Developer developer = new Developer();
        developer.setAccessId("00001");
        developer.setAccessSecret("123");
        developer.setLevelId("1");
        developer.setContacts("Marry");
        this.apiService.save(api, developer);
    }*/





}
