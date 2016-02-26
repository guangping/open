package com.varela.mongo.test;

import com.mongodb.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
        query.limit(10);
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "id")));
        List<Access> list = this.mongoTemplate.find(query, Access.class);
        System.out.println(list);
    }

    @Test
    public void remove() {
        Access access = new Access();
        access.setId(1);
        WriteResult writeResult = this.mongoTemplate.remove(access);
        System.out.println(writeResult);
    }

    @Test
    public void update() {
        Access access = new Access();
        access.setAccessId(UUID.randomUUID().toString());
        access.setSecret(String.valueOf(new Random(10000).nextLong()));
        access.setDateTime(new Date(System.currentTimeMillis()));
        access.setStatus(1);
        access.setId(2);
        this.mongoTemplate.save(access);
    }


}
