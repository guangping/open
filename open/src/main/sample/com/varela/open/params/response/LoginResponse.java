package com.varela.open.params.response;

import com.varela.open.params.base.BaseRopResponse;

/**
 * Created with IntelliJ IDEA.
 * User: guangping
 * Date: 2014-07-01 13:43
 * To change this template use File | Settings | File Templates.
 */
public class LoginResponse extends BaseRopResponse {

    private String pwd;

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
