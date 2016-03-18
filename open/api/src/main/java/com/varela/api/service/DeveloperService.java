package com.varela.api.service;

import com.varela.api.entity.Developer;
import com.varela.dao.DeveloperDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lance on 2016/3/15.
 */
@Service
public class DeveloperService {

    @Autowired
    private DeveloperDao developerDao;

    @Transactional
    public long save(Developer developer) {
        this.developerDao.save(developer);
        return developer.getId();
    }

    public Developer queryById(Long id) {
        return this.developerDao.queryById(id);
    }

    public Developer queryByAppKey(String appKey) {
        return this.developerDao.queryByAppKey(appKey);
    }
}
