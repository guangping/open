package com.varela.open.security;

import com.varela.open.session.Session;

/**
 * Created by lance on 9/29/2015.
 */
public class RedisInvokeTimesController implements InvokeTimesController {
    public void caculateInvokeTimes(String appKey, Session session) {

    }

    public boolean isUserInvokeLimitExceed(String appKey, Session session) {
        return true;
    }

    public boolean isSessionInvokeLimitExceed(String appKey, String sessionId) {
        return true;
    }

    public boolean isAppInvokeLimitExceed(String appKey) {
        return true;
    }

    public boolean isAppInvokeFrequencyExceed(String appKey) {
        return true;
    }
}
