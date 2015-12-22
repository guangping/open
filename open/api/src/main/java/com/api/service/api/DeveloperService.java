package com.api.service.api;


import com.api.dao.api.impl.DeveloperDBService;
import com.api.entity.Developer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lance on 9/29/2015.
 */
@Service
public class DeveloperService {

    @Autowired
    private DeveloperDBService developerDBService;


    @Transactional
    public long save(Developer developer) {
        return this.developerDBService.save(developer);
    }

    public Developer queryObj(long id){
        return this.developerDBService.queryObj(id);
    }

    public Developer queryObj(String appKey){
        Developer developer=new Developer();
        developer.setAppKey(appKey);
        return this.developerDBService.queryObj(developer);
    }
}
