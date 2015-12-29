package com.api.interceptors;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.api.pojo.APIKey;
import com.api.pojo.APIRequest;
import com.api.security.DefaultInvokeTimesController;
import com.api.security.DefaultSecurityManager;
import com.api.service.api.DeveloperApiTimeLogService;
import com.varela.enumerate.Msg;
import com.varela.pojo.APIResult;
import com.varela.utils.StringCommonUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    //计算耗时
    private NamedThreadLocal<Long> startTimeThreadLocal =
            new NamedThreadLocal("StopWatch-StartTime");

    //存储请求基础参数
    private NamedThreadLocal<APIRequest> requestThreadLocal =
            new NamedThreadLocal("access-request");

    @Autowired
    private DefaultSecurityManager securityManager;

    @Autowired
    private DefaultInvokeTimesController invokeTimesController;

    @Autowired
    private DeveloperApiTimeLogService apiTimeLogService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //TODO 开始时间
        long beginTime = System.currentTimeMillis();
        startTimeThreadLocal.set(beginTime);

        logger.info("请求地址:{},请求参数:{}", request.getRequestURI(), JSONObject.toJSONString(request.getParameterMap()));

        String contentType = request.getContentType();
        if (StringUtils.isBlank(contentType) || !contentType.startsWith(APIKey.ContentType.X_WWW_FORM_URLENCODED)) {
            APIResult apiResult = new APIResult();
            apiResult.setMsg(Msg.CONTENT_TYPE_ERROR);
            this.writeJson(apiResult, response);
            return false;
        }
        APIRequest apiRequest = this.getRequest(request);
        requestThreadLocal.set(apiRequest);
        //TODO 请求验证
        APIResult apiResult = this.securityManager.validateParams(apiRequest);
        if (!apiResult.isSuccess()) {
            this.writeJson(apiResult, response);
            return false;
        }
        //相关验证处理
        return true;
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

        APIRequest apiRequest = requestThreadLocal.get();
        logger.info("接口: {} 耗时:{}ms", request.getRequestURI(), consumeTime);
        if (consumeTime > APIKey.TIME) {
            //TODO 记录到日志文件
            //记录调用耗时过长日志
            this.apiTimeLogService.set(apiRequest.getAppKey(), apiRequest.getMethod(), consumeTime);
        }
        //TODO 调用次数存储
        this.invokeTimesController.caculateInvokeTimes(apiRequest.getAppKey(), apiRequest.getMethod());
        super.afterCompletion(request, response, handler, ex);
    }

    /**
     * 获取验证参数
     */
    private APIRequest getRequest(HttpServletRequest request) {
        String appKey = StringCommonUtils.getSafeString(request.getParameter(APIKey.ValidateKey.APPKEY));
        String sign = StringCommonUtils.getSafeString(request.getParameter(APIKey.ValidateKey.SIGN));
        long timestamp = StringCommonUtils.getSafeLong(request.getParameter(APIKey.ValidateKey.TIMESTAMP));
        //获取springmvc映射地址
        Object object = request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);

        APIRequest apiRequest = new APIRequest();
        apiRequest.setSign(sign);
        apiRequest.setAppKey(appKey);
        apiRequest.setTimestamp(timestamp);
        if (null != object) {
            apiRequest.setMethod(object.toString());
        }
        return apiRequest;
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
