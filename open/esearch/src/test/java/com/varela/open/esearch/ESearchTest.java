package com.varela.open.esearch;

import com.varela.open.esearch.pojo.Address;
import com.varela.open.esearch.repositories.AddressElasticsearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.AliasQuery;
import org.springframework.data.elasticsearch.repository.support.SimpleElasticsearchRepository;
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


    @Autowired
    private AddressElasticsearchRepository addressElasticsearchRepository;


    @Test
    public void run() {
        System.out.println(this.elasticsearchTemplate);

    }

    @Test
    public void createIndex() {
        Address address = new Address();
        address.setAddress("上海市长宁区中山西路/安顺路(路口)");
        address.setId("10000");
        address.setLat(31.206012);
        address.setLng(121.419751);
        Object obj = this.addressElasticsearchRepository.index(address);
        System.out.println(obj);
    }

    @Test
    public void updateIndex() {
        String id = "10000";
        this.addressElasticsearchRepository.delete(id);
    }

    @Test
    public void deleteIndex() {

    }

}
