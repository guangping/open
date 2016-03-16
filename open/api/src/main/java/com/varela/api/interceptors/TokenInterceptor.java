package com.varela.api.interceptors;

import com.varela.api.pojo.APIKey;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lance on 2016/3/16.
 * token 验证
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token=request.getParameter(APIKey.ValidateKey.TOKEN);

        return super.preHandle(request, response, handler);
    }
}
