package com.varela.open.esearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * Created by lance on 2016/2/14.
 */
@ContextConfiguration("classpath:applicationContext-esearch-test.xml")
public class ESearchTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void run() {
        System.out.println(this.elasticsearchTemplate);

    }

    @Test
    public void createIndex() {



    }

    @Test
    public void updateIndex() {

    }

    @Test
    public void deleteIndex() {

    }

}
