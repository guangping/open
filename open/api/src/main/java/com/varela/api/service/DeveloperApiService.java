package com.varela.api.service;

import com.varela.api.entity.Developer;
import com.varela.api.entity.DeveloperApi;
import com.varela.dao.DeveloperApiDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lance on 2016/3/18.
 */
@Service
public class DeveloperApiService {

    @Autowired
    private DeveloperApiDao developerApiDao;
    

    @Autowired
    private DeveloperService developerService;


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
}
