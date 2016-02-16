package com.varela.open.elasticsearch.test;

import com.varela.open.elasticsearch.pojo.Address;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.InetAddress;

/**
 * Created by lance on 2016/2/15.
 */

@ContextConfiguration("classpath:applicationContext-search-test.xml")
public class ESTest extends AbstractTestNGSpringContextTests {

    private String url = "http://localhost:9200";

    private String ip = "127.0.0.1";

    private int port=9300;

    private TransportClient client;


    @BeforeMethod
    public void before() {
        try {
            client = TransportClient.builder().build().
                    addTransportAddress(new InetSocketTransportAddress(InetAddress.getLocalHost(), port));

           // TransportClient.builder().build().addTransportAddress();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void run() {
        System.out.println(client);

        System.out.println();
    }


    @Test
    public void createIndex() {
        //
        Address address = new Address();
        address.setAddress("上海市长宁区中山西路/安顺路(路口)");
        address.setId("10000");
        address.setLat(31.206012);
        address.setLng(121.419751);

       /* IndexRequest request = new IndexRequest();
        request.refresh(true);
        request.type("address");
        request.opType(IndexRequest.OpType.INDEX);
        request.source(address);*/

        CreateIndexResponse response= client.admin().indices().create(new CreateIndexRequest("北京")).actionGet();
        System.out.println(response);



       /* IndexResponse response = client.index(request).actionGet();
        System.out.printf(response.getIndex());
*/
    }

    @Test
    public void createBatchIndex() {

    }

    @Test
    public void deleteIndex() {

    }


    @Test
    public void updateIndex() {

    }

    @Test
    public void queryIndex() {
        SearchResponse response = client.prepareSearch("index1", "index2")
                .setTypes("type1", "type2")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.termQuery("multi", "test"))                 // Query
                .setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(18))     // Filter
                .setFrom(0).setSize(60).setExplain(true)
                .execute()
                .actionGet();

        // MatchAll on the whole cluster with all default options
        //SearchResponse response = client.prepareSearch().execute().actionGet();
    }


    @AfterMethod
    public void close() {
        client.close();
    }


}
