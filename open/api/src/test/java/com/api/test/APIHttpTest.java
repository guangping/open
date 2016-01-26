package com.api.test;

import com.alibaba.fastjson.JSONObject;
import com.api.pojo.APIRequest;
import com.api.utils.APIMD5Utils;
import com.varela.utils.http.HttpClientUtils;
import com.varela.utils.http.HttpResponse;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Calendar;
import java.util.TreeMap;

/**
 * Created by lance on 12/18/2015.
 */
public class APIHttpTest {

    private String url = "http://localhost";
    private TreeMap<String, String> params;
    private String appKey = "00001";
    private String secret = "123";

    private int count = 0;

    @BeforeTest
    public void before() {
        params = new TreeMap();
    }


    @Test(invocationCount = 15)
    public void sessionGet() {
        url = "http://localhost/api/session/get";

        APIRequest apiRequest = new APIRequest();
        apiRequest.setTimestamp(System.currentTimeMillis() / 1000);
        apiRequest.setAppKey(appKey);
        apiRequest.setMethod("/api/session/get");

        params = JSONObject.parseObject(JSONObject.toJSONString(apiRequest), TreeMap.class);
        String sign = APIMD5Utils.sign(params, secret);
        params.put("sign", sign);

        HttpResponse response = HttpClientUtils.postForm(url, params);
        if (!response.isSuccess()) {
            count++;
            System.out.println("失败次数:" + count + "," + response);
        }
    }

    @Test
    public void time() {
        final Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        double s = (cal.getTimeInMillis() - System.currentTimeMillis()) / (1000 * 3600);
        System.out.println(s);
    }
}
