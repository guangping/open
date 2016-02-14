package com.varela.open.esearch.repositories;

import com.varela.open.esearch.pojo.Address;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by lance on 2016/2/14.
 */
public interface AddressElasticsearchRepository extends ElasticsearchRepository<Address,String> {



}
