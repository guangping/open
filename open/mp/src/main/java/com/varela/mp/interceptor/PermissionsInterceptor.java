package com.varela.mp.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lance on 2016/3/30.
 */
public class PermissionsInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(PermissionsInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("权限认证!");
        //映射实际地址
        String bestMatchingPattern = String.valueOf(request
                .getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE));

        return super.preHandle(request, response, handler);
    }
}
