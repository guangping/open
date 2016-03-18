package com.varela.api.service;

import com.varela.api.entity.Developer;
import com.varela.api.pojo.RedisApiKey;
import com.varela.cache.RedisCache;
import com.varela.dao.DeveloperDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lance on 2016/3/15.
 */
@Service
public class DeveloperService implements CacheService<Developer> {

    @Autowired
    private DeveloperDao developerDao;

    @Autowired
    private RedisCache redisCache;


    @Transactional
    public long save(Developer developer) {
        this.developerDao.save(developer);
        return developer.getId();
    }

    public List<Developer> queryList(Developer developer) {
        return this.developerDao.queryList(developer);
    }

    public Developer queryById(Long id) {
        return this.developerDao.queryById(id);
    }

    public Developer queryByAppKey(String appKey) {
        return this.developerDao.queryByAppKey(appKey);
    }

    @Override
    public void setCache(Developer developer) {
        String idKey = null, appKey = null;
        if (null != developer) {
            idKey = RedisApiKey.getDeveloperId(String.valueOf(developer.getId()));
            appKey = RedisApiKey.getDeveloperAppkey(developer.getAppKey());

            this.redisCache.set(idKey, developer);
            this.redisCache.set(appKey, developer);
        }
    }
}
