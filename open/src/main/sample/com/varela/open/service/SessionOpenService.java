package com.varela.open.service;

import com.varela.open.Constants;
import com.varela.open.annotation.NeedInSessionType;
import com.varela.open.annotation.ServiceMethod;
import com.varela.open.annotation.ServiceMethodBean;
import com.varela.open.params.request.LoginRequest;
import com.varela.open.params.request.SessionRequest;
import com.varela.open.params.response.LoginResponse;
import com.varela.open.params.response.SessionResponse;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: guangping
 * Date: 2014-06-27 15:24
 * To change this template use File | Settings | File Templates.
 */
@ServiceMethodBean(version = Constants.VERSION)
public class SessionOpenService {


    @ServiceMethod(method = "user.getSession", version = Constants.VERSION, needInSession = NeedInSessionType.NO, title = "获取session")
    SessionResponse getSession(SessionRequest request) {
        SessionResponse response = new SessionResponse();
        response.setResult(true);
        response.setCode("0");
        response.setMsg("success");
        response.setSessionId(UUID.randomUUID().toString());

        return response;
    }

    @ServiceMethod(method = "user.login", version = Constants.VERSION, needInSession = NeedInSessionType.NO, title = "登陆方法")
    LoginResponse login(LoginRequest request) {
        LoginResponse response = new LoginResponse();
        response.setResult(true);
        response.setCode("0");
        response.setMsg("success");
        response.setPwd(System.currentTimeMillis() + "");

        return response;
    }
}
