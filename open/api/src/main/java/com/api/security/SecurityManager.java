package com.api.security;

import com.api.pojo.APIRequest;
import com.varela.pojo.APIResult;

import javax.servlet.http.HttpServletRequest;

/**
 * 负责对请求数据、会话、业务安全、应用密钥安全进行检查并返回相应的错误
 */
public interface SecurityManager {

    APIResult validateParams(APIRequest apiRequest);
}
