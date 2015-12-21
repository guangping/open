package com.api.service.api;


import com.api.dao.api.impl.DeveloperDBService;
import com.api.entity.Developer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lance on 9/29/2015.
 */
@Service
public class DeveloperService {

    @Autowired
    private DeveloperDBService developerDBService;

    public List<Developer> queryList() {
        return this.developerDBService.queryList(new Developer());
    }


    @Transactional
    public long save(Developer developer) {
        return this.developerDBService.save(developer);
    }
}
