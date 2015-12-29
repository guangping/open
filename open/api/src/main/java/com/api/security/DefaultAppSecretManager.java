package com.api.security;

import com.api.entity.Developer;
import com.api.pojo.APIKey;
import com.api.service.api.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by lance on 12/15/2015.
 */
@Component
public class DefaultAppSecretManager implements AppSecretManager {

    @Autowired
    private DeveloperService developerService;


    @Override
    public String getSecret(String appKey) {
        Developer developer = this.developerService.queryObj(appKey);
        return developer.getSecret();
    }

    @Override
    public boolean isValidAppKey(String appKey) {
        Developer developer = this.developerService.queryObj(appKey);

        return (null == developer) ? false : true;
    }

    @Override
    public boolean isDisable(String appKey) {
        Developer developer = this.developerService.queryObj(appKey);
        if (null == developer) {
            return true;
        }
        if (developer.getState() == APIKey.StateKey.DEVELOPER_STATE_DISABLE) {
            return true;
        }
        return false;
    }
}
