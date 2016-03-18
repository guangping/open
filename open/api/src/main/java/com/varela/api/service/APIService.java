package com.varela.api.service;

import com.varela.api.entity.API;
import com.varela.dao.APIDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lance on 2016/3/15.
 */
@Service
public class APIService  {

    private Logger logger= LoggerFactory.getLogger(APIService.class);

    @Autowired
    private APIDao apiDao;

    @Transactional
    public long save(API api){
        this.apiDao.save(api);
        long id=api.getId();
        return id;
    }
}
