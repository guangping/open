package com.api.test;

import com.alibaba.fastjson.JSONObject;
import com.api.pojo.APIRequest;
import com.api.utils.APIMD5Utils;
import com.varela.utils.http.HttpClientUtils;
import com.varela.utils.http.HttpResponse;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.TreeMap;

/**
 * Created by lance on 12/18/2015.
 */
public class APIHttpTest {

    private String url = "http://magp.varela.com.cn";
    private TreeMap<String, String> params;
    private String appKey = "00001";
    private String secret = "123";

    @BeforeTest
    public void before() {
        params = new TreeMap();
    }


    @Test
    public void sessionGet() {
        url += "/api/session/get";

        APIRequest apiRequest = new APIRequest();
        apiRequest.setTimestamp(System.currentTimeMillis() / 1000);
        apiRequest.setAppKey(appKey);
        apiRequest.setMethod("/api/session/get");
        String sign = APIMD5Utils.sign(params, secret);
        apiRequest.setSign(sign);

        params = JSONObject.parseObject(JSONObject.toJSONString(apiRequest), TreeMap.class);

        HttpResponse response = HttpClientUtils.postForm(url, params);
        System.out.println(response);

    }
}
