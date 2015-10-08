package com.varela.open.client;

import com.varela.open.params.request.LoginRequest;
import com.varela.open.params.response.LoginResponse;
import org.testng.annotations.Test;


public class DefaultRopClientTest {

    private RopClient ropClient = new DefaultRopClient("http://localhost/gateway.do", "00001", "123");

    @Test
    public void login() {
        LoginRequest request = new LoginRequest();
        request.setUserName("admin");
        request.setPassword("123333");

        CompositeResponse response=ropClient.buildClientRequest().post(request, LoginResponse.class, request.getApiMethodName(), "1.0");
        System.out.printf(response.toString());
    }

}

