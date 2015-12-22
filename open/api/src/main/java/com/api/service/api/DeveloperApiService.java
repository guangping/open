package com.api.service.api;

import com.api.dao.api.impl.DeveloperApiDBService;
import com.api.entity.API;
import com.api.entity.Developer;
import com.api.entity.DeveloperApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lance on 12/21/2015.
 */
@Service
public class DeveloperApiService {

    @Autowired
    private DeveloperApiDBService developerApiDBService;

    @Autowired
    private APIService apiService;

    @Autowired
    private DeveloperService developerService;

    @Transactional
    public long save(DeveloperApi developerApi) {
        return this.developerApiDBService.save(developerApi);
    }

    public DeveloperApi queryObj(long appId, long developerId) {
        DeveloperApi developerApi = new DeveloperApi();
        developerApi.setApiId(appId);
        developerApi.setDeveloperId(developerId);
        return this.developerApiDBService.queryObj(developerApi);
    }

    public List<DeveloperApi> query(long appId) {
        DeveloperApi arg = new DeveloperApi();
        arg.setApiId(appId);
        return this.developerApiDBService.query(arg);
    }

    public boolean query(String appKey, String method) {
        boolean sign = false;
        API api = this.apiService.queryObj(method);
        Developer developer = this.developerService.queryObj(appKey);

        if (null != api && null != developer) {
            DeveloperApi developerApi = this.queryObj(api.getId(), developer.getId());
            if (null != developerApi) {
                sign = true;
            }
        }

        return sign;
    }


}
