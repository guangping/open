package com.api.service.api;


import com.api.dao.api.impl.APIDBService;
import com.api.entity.API;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lance on 9/29/2015.
 */
@Service
public class APIService {

    @Autowired
    private APIDBService apiDBService;

    public API queryById(String id) {
        API search = new API();
        search.setId(id);
        return this.apiDBService.queryObject(search);
    }

    @Transactional
    public String save(API api) {
        return this.apiDBService.save(api);
    }

}
