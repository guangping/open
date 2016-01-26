package com.api.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;

/**
 * Created by lance on 2016/1/25.
 */
public class ResourceInterceptor extends HandlerInterceptorAdapter {
    private Logger logger = LoggerFactory.getLogger(ResourceInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("ctx", request.getContextPath());

        InetAddress addr = InetAddress.getLocalHost();
        String ip = addr.getHostAddress();
        MDC.put("ip", ip);

        int port = request.getLocalPort();
        MDC.put("port", String.valueOf(port));

        MDC.put("user","lance");

        return super.preHandle(request, response, handler);
    }
}
