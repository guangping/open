package com.varela.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by lance on 10/28/2015.
 */
public class BaseController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;
    private String bestMatchingPattern;//映射实际地址

    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.session = request.getSession();
        bestMatchingPattern = String.valueOf(request
                .getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE));
    }

}
