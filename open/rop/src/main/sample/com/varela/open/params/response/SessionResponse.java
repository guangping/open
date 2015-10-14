package com.varela.open.params.response;


import com.varela.open.params.base.BaseRopResponse;


public class SessionResponse extends BaseRopResponse {


    private String sessionId;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}

