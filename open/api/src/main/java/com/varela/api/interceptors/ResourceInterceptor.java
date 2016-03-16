package com.varela.api.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lance on 2016/1/25.
 */
public class ResourceInterceptor extends HandlerInterceptorAdapter {
    private Logger logger = LoggerFactory.getLogger(ResourceInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("ctx", request.getContextPath());
        return super.preHandle(request, response, handler);
    }
}
