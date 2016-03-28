package com.varela.api.service;

import com.varela.api.entity.API;
import com.varela.api.entity.Developer;
import com.varela.api.entity.DeveloperApi;
import com.varela.api.utils.APIRedisKey;
import com.varela.cache.RedisCache;
import com.varela.cache.RedisKey;
import com.varela.dao.DeveloperApiDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lance on 2016/3/18.
 */
@Service
public class DeveloperApiService implements CacheService<DeveloperApi> {

    @Autowired
    private DeveloperApiDao developerApiDao;

    @Autowired
    private DeveloperService developerService;

    @Autowired
    private RedisCache redisCache;


    public List<DeveloperApi> queryList(DeveloperApi developerApi) {
        return this.developerApiDao.queryList(developerApi);
    }

    public List<DeveloperApi> queryListByApiId(Long apiId) {
        DeveloperApi developerApi = new DeveloperApi();
        developerApi.setApiId(apiId);
        return this.developerApiDao.queryList(developerApi);
    }

    public List<DeveloperApi> queryListByDeveloperId(Long developerId) {
        DeveloperApi developerApi = new DeveloperApi();
        developerApi.setDeveloperId(developerId);
        return this.developerApiDao.queryList(developerApi);
    }

    public List<DeveloperApi> queryListByAppKey(String appKey) {
        Developer developer = this.developerService.queryByAppKey(appKey);
        if (null == developer) {
            return null;
        }
        return this.queryListByDeveloperId(developer.getId());
    }

    @Override
    public void setCache(DeveloperApi arg) {
        if (null != arg) {
            String developerKey = APIRedisKey.getDeveloperKey(String.valueOf(arg.getDeveloperId()));
            String apiKey = APIRedisKey.getApiKey(String.valueOf(arg.getApiId()));

            Developer developer = this.redisCache.getObj(developerKey, Developer.class);
            API api = this.redisCache.getObj(apiKey, API.class);
            if (null != developer && null != api) {
                String key = APIRedisKey.getAppkeyMethodKey(developer.getAppKey(), api.getMethod());
                this.redisCache.set(key, arg, RedisKey.REDIS_1H_EXPIRING);
            }
        }
    }
}
