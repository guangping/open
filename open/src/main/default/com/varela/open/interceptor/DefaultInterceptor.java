package com.varela.open.interceptor;

import com.varela.open.AbstractInterceptor;
import com.varela.open.RopRequestContext;
import com.varela.open.converter.DateUtils;

/**
 * Created with IntelliJ IDEA.
 * User: guangping
 * Date: 2014-06-26 17:23
 * To change this template use File | Settings | File Templates.
 */
public class DefaultInterceptor extends AbstractInterceptor {

    public void beforeService(RopRequestContext ropRequestContext) {
        if (logger.isDebugEnabled()) {
            logger.debug("{}服务调用拦截器:{}", new String[]{DateUtils.getCurrDatetime(), "beforeService"});
        }
    }


    public void beforeResponse(RopRequestContext ropRequestContext) {
        if (logger.isDebugEnabled()) {
            logger.debug("{}服务调用拦截器:{}", new String[]{DateUtils.getCurrDatetime(), "beforeResponse"});
        }
    }
}
