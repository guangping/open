package com.api.service.api;

import com.api.dao.api.impl.DeveloperApiDBService;
import com.api.entity.DeveloperApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lance on 12/21/2015.
 */
@Service
public class DeveloperApiService {

    @Autowired
    private DeveloperApiDBService developerApiDBService;

    @Transactional
    public long save(DeveloperApi developerApi) {
        return this.developerApiDBService.save(developerApi);
    }

    public DeveloperApi queryObj(long appId,long developerId){
        DeveloperApi developerApi=new DeveloperApi();
        developerApi.setApiId(appId);
        developerApi.setDeveloperId(developerId);
        return this.developerApiDBService.queryObj(developerId);
    }


}
