package com.varela.api.service;

import com.varela.api.entity.Developer;
import com.varela.api.utils.APIRedisKey;
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


    /**
     * 添加开发者
     * @param developer
     * */
    @Transactional
    public long save(Developer developer) {
        this.developerDao.save(developer);
        return developer.getId();
    }

    public List<Developer> queryList(Developer developer) {
        return this.developerDao.queryList(developer);
    }

    /**
     * 根据id查询开发者信息
     * @param id
     * */
    public Developer queryById(Long id) {
        String key = APIRedisKey.getDeveloperKey(String.valueOf(id));
        Developer developer = this.redisCache.getObj(key, Developer.class);
        if (null == developer) {
            developer = this.developerDao.queryById(id);
            this.setCache(developer);
        }
        return developer;
    }

    /**
     * 根据appKey查询开发者信息
     * @param appKey
     * **/
    public Developer queryByAppKey(String appKey) {
        String key = APIRedisKey.getDeveloperAppKey(appKey);
        Developer developer = this.redisCache.getObj(key, Developer.class);
        if (null == developer) {
            developer = this.developerDao.queryByAppKey(appKey);
            this.setCache(developer);
        }
        return developer;
    }

    @Override
    public void setCache(Developer developer) {
        String idKey = null, appKey = null;
        if (null != developer) {
            idKey = APIRedisKey.getDeveloperKey(String.valueOf(developer.getId()));
            appKey = APIRedisKey.getDeveloperAppKey(developer.getAppKey());

            this.redisCache.set(idKey, developer);
            this.redisCache.set(appKey, developer);
        }
    }
}
