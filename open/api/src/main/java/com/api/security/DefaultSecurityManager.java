package com.api.security;

import com.api.pojo.APIKey;
import com.api.pojo.APIRequest;
import com.api.utils.APIMD5Utils;
import com.varela.enumerate.Msg;
import com.varela.pojo.APIResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.TreeMap;

/**
 * Created by lance on 12/15/2015.
 */
@Component
public class DefaultSecurityManager implements SecurityManager {
    @Autowired
    private DefaultAppSecretManager appSecretManager;

    @Autowired
    private DefaultInvokeTimesController invokeTimesController;


    @Override
    public APIResult validateParams(APIRequest apiRequest) {
        APIResult apiResult = new APIResult();
        String appKey = apiRequest.getAppKey();
        String sign = apiRequest.getSign();
        long timestamp = apiRequest.getTimestamp();
        //获取springmvc映射地址
        String method = apiRequest.getMethod();

        if (StringUtils.isBlank(appKey)) {
            apiResult.setMsg(Msg.APPKEY_IS_NULL);
            return apiResult;
        }
        if (StringUtils.isBlank(sign)) {
            apiResult.setMsg(Msg.SIGN_IS_NULL);
            return apiResult;
        }
        //检查有效期
        long dValue = System.currentTimeMillis() / 1000 - timestamp;
        if (dValue > 600 || dValue < -600) {
            apiResult.setMsg(Msg.VALID_TIME);
            return apiResult;
        }

        //检查appKey是否存在
        boolean appKeySign = this.appSecretManager.isValidAppKey(appKey);
        if (!appKeySign) {
            apiResult.setMsg(Msg.APPKEY_NOT_EXISTS);
            return apiResult;
        }

        //检查是否可用
        boolean disableSign = this.appSecretManager.isDisable(appKey);
        if (disableSign) {
            apiResult.setMsg(Msg.APPKEY_DISABLE);
            return apiResult;
        }

        //检查签名
        boolean checkSign = this.checkSign(appKey, timestamp, method, sign);
        if (!checkSign) {
            apiResult.setMsg(Msg.SIGN_ERROR);
            return apiResult;
        }

        //检查方法调用权限
        boolean checkMethodSign = this.checkMethod(appKey, method);
        if (!checkMethodSign) {
            apiResult.setMsg(Msg.NOT_UNAUTHORIZED);
            return apiResult;
        }

        //检查方法是否可用

        //检查调用频率
        boolean frequencyExceed = this.invokeTimesController.isAppInvokeFrequencyExceed(appKey);
        if (!frequencyExceed) {
            apiResult.setMsg(Msg.OVERRUN);
            return apiResult;
        }

        //检查方法调用次数


        apiResult.setMsg(Msg.SUCCESS);
        return apiResult;
    }

    private boolean checkSign(String appKey, long timestamp, String method, String sign) {
        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put(APIKey.ValidateKey.APPKEY, appKey);
        params.put(APIKey.ValidateKey.TIMESTAMP, String.valueOf(timestamp));
        params.put(APIKey.ValidateKey.METHOD, method);

        String key = this.appSecretManager.getSecret(appKey);

        String val = APIMD5Utils.sign(params, key);
        if (sign.equals(val)) {
            return true;
        }
        return false;
    }

    private boolean checkMethod(String appKey, String method) {
        return this.invokeTimesController.checkMethod(appKey, method);
    }


}
