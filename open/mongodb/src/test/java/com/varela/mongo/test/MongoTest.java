package com.varela.mongo.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.*;

/**
 * Created by lance on 12/9/2015.
 */
@ContextConfiguration("classpath:applicationContext-mongo-test.xml")
public class MongoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private MongoTemplate mongoTemplate;


    @Test
    public void insert() {
        Access access = null;
        for (int i = 0; i < 1000; i++) {
            access = new Access();
            access.setAccessId(UUID.randomUUID().toString());
            access.setSecret(String.valueOf(new Random(10000).nextLong()));
            access.setDateTime(new Date(System.currentTimeMillis()));
            access.setStatus(1);
            access.setId((i + 2));
            this.mongoTemplate.insert(access);
        }

    }

    @Test
    public void query() {
        List<Access> list = this.mongoTemplate.findAll(Access.class);
        System.out.println(list);
    }

    @Test
    public void findById() {
        Access access = this.mongoTemplate.findById("1", Access.class);
        System.out.println(access);

        Query query = new Query();
        this.mongoTemplate.find(query, Access.class);
    }


}
