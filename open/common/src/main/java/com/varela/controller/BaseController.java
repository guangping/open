package com.varela.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by lance on 10/28/2015.
 */
public class BaseController {

    protected Logger logger = LoggerFactory.getLogger(getClass());


    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;
/*    protected String bestMatchingPattern;//映射实际地址
    bestMatchingPattern = String.valueOf(request
            .getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE));*/

    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.session = request.getSession();

    }

    /**
     * 拦截器中返回json数据
     */
    protected void writeJson(String json) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(json);
    }

    protected void writeJson(Object obj) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSONObject.toJSONString(obj, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullBooleanAsFalse));
    }

    protected String redirect(String url){
        return "redirect:"+url;
    }

    protected String forward(String url){
        return "forward:"+url;
    }


}
