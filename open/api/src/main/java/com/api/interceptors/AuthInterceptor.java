package com.api.interceptors;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.api.pojo.APIKey;
import com.varela.utils.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lance on 12/8/2015.
 * 访问记录及权限验证
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {
    private Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

    private NamedThreadLocal<Long> startTimeThreadLocal =
            new NamedThreadLocal<Long>("StopWatch-StartTime");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //TODO 开始时间
        long beginTime = System.currentTimeMillis();
        startTimeThreadLocal.set(beginTime);

        //获取路径
        Object object = request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        if (null != object) {
            logger.info("请求路径:{}", object);
        }
        //相关验证处理
        return super.preHandle(request, response, handler);
    }

    /**
     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //TODO 结束时间
        long endTime = System.currentTimeMillis();
        long beginTime = startTimeThreadLocal.get();
        long consumeTime = endTime - beginTime;
        if (consumeTime > 500) {
            //TODO 记录到日志文件
            logger.error("{}耗时:{} ms", request.getRequestURI(), consumeTime);
        }


        super.afterCompletion(request, response, handler, ex);
    }


    /**
     * 获取验证参数
     */
    public void getValidateParams(HttpServletRequest request) {
        String appKey = WebUtils.getParams(request, APIKey.ValidateKey.APPKEY, "");
        String sign = WebUtils.getParams(request, APIKey.ValidateKey.SIGN, "");
        String timestamp = WebUtils.getParams(request, APIKey.ValidateKey.TIMESTAMP, "");
        Object object = request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        if (null != object) {
            String method = object.toString();
        }


    }

    /**
     * 拦截器中返回json数据
     */
    public void writeJson(String json, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(json);
    }

    public void writeJson(Object obj, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSONObject.toJSONString(obj, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullBooleanAsFalse));
    }
}
