package com.varela.open.params.request;

import com.varela.open.annotation.IgnoreSign;
import com.varela.open.params.base.BaseRopRequest;
import com.varela.open.params.response.SessionResponse;

import javax.validation.constraints.NotNull;


public class SessionRequest extends BaseRopRequest<SessionResponse> {

    @NotNull
    private String userName;


    @IgnoreSign
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getApiMethodName() {
        return "user.getSession";
    }
}

