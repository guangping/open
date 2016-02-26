package com.varela.mongo.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by lance on 12/9/2015.
 */
@ContextConfiguration("classpath:applicationContext-mongo-test.xml")
public class MongoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private MongoTemplate mongoTemplate;



    @Test
    public void run(){
        Access access=new Access();
        access.setAccessId(UUID.randomUUID().toString());
        access.setSecret("123");
        access.setDateTime(new Date(System.currentTimeMillis()));
        access.setStatus(1);
        access.setId(1);
        this.mongoTemplate.insert(access);
    }




}
