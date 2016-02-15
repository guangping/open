package com.varela.open.esearch;

import com.varela.open.esearch.pojo.Address;
import com.varela.open.esearch.repositories.AddressElasticsearchRepository;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.common.bytes.BytesReference;
import org.elasticsearch.common.xcontent.ToXContent;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.io.IOException;

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
        System.out.println(this.elasticsearchTemplate + ":" + this.addressElasticsearchRepository);

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

        address = new Address();
        address.setAddress("川沙镇川大路319号三一科技公司");
        address.setId("10001");
        address.setLat(31.17302);
        address.setLng(121.709482);
        obj = this.addressElasticsearchRepository.index(address);
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

    @Test
    public void query(){



    }

}
