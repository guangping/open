package com.varela.api.service;

import com.varela.api.entity.API;
import com.varela.api.utils.APIRedisKey;
import com.varela.cache.RedisCache;
import com.varela.dao.APIDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lance on 2016/3/15.
 */
@Service
public class APIService implements CacheService<API> {

    private Logger logger = LoggerFactory.getLogger(APIService.class);

    @Autowired
    private APIDao apiDao;

    @Autowired
    private RedisCache redisCache;

    @Transactional
    public long save(API api) {
        this.apiDao.save(api);
        long id = api.getId();
        return id;
    }

    public API queryByMethod(String method) {
        return this.apiDao.queryByMethod(method);
    }

    public List<API> queryList(API api) {
        return this.apiDao.queryList(api);
    }

    @Override
    public void setCache(API arg) {
        String idKey = null, methodKey = null;
        if (null != arg) {
            idKey = APIRedisKey.getApiKey(String.valueOf(arg.getId()));
            methodKey = APIRedisKey.getApiMethodKey(arg.getMethod());

            this.redisCache.set(idKey, arg);
            this.redisCache.set(methodKey, arg);
        }
    }
}
