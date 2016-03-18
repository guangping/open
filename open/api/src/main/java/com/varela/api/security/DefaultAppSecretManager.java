package com.varela.api.security;

import com.varela.api.entity.Developer;
import com.varela.api.pojo.APIKey;
import com.varela.api.service.DeveloperService;
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
        Developer developer = this.developerService.queryByAppKey(appKey);
        if (null != developer) {
            return developer.getSecret();
        }
        return null;
    }

    @Override
    public boolean isValidAppKey(String appKey) {
        Developer developer = this.developerService.queryByAppKey(appKey);
        return (null == developer) ? false : true;
    }

    @Override
    public boolean isDisable(String appKey) {
        Developer developer = this.developerService.queryByAppKey(appKey);
        if (null != developer) {
            return developer.getState() == APIKey.DEVELOPER__STATE_NORMAL ? true : false;
        }
        return false;
    }
}
