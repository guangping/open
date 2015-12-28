package com.api.security;

import com.api.service.api.DeveloperApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by lance on 12/24/2015.
 */
@Component
public class DefaultInvokeTimesController implements InvokeTimesController {

    @Autowired
    private DeveloperApiService developerApiService;

    @Override
    public boolean checkMethod(String appKey, String method) {
        return developerApiService.query(appKey,method);
    }

    @Override
    public void caculateInvokeTimes(String appKey, String method) {

    }

    @Override
    public boolean isAppInvokeLimitExceed(String appKey, String method) {
        return false;
    }
}
