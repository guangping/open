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


    @Transactional
    public long save(API api) {
        return this.apiDBService.save(api);
    }

    public API queryObj(long id){
        return this.apiDBService.queryObj(id);
    }

    public API queryObj(String method){
        API api=new API();
        api.setMethod(method);
        return this.apiDBService.queryObj(api);
    }
}
