package com.api.security;

import com.api.pojo.APIKey;
import com.api.utils.APIMD5Utils;
import com.varela.enumerate.Msg;
import com.varela.pojo.APIResult;
import com.varela.utils.StringCommonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.TreeMap;

/**
 * Created by lance on 12/15/2015.
 */
@Component
public class DefaultSecurityManager implements SecurityManager {
    @Autowired
    private DefaultAppSecretManager appSecretManager;

    @Override
    public APIResult validateParams(HttpServletRequest request) {
        APIResult apiResult = new APIResult();
        String appKey = StringCommonUtils.getSafeString(request.getParameter(APIKey.ValidateKey.APPKEY));
        String sign = StringCommonUtils.getSafeString(request.getParameter(APIKey.ValidateKey.SIGN));
        long timestamp = StringCommonUtils.getSafeLong(request.getParameter(APIKey.ValidateKey.TIMESTAMP));
        //获取springmvc映射地址
        Object object = request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        if (StringUtils.isBlank(sign)) {
            apiResult.setMsg(Msg.SIGN_IS_NULL);
            return apiResult;
        }
        String method = null;
        if (null != object) {
            method = object.toString();
        }
        if (StringUtils.isBlank(appKey)) {
            apiResult.setMsg(Msg.APPKEY_IS_NULL);
            return apiResult;
        }
        //检查appKey是否存在
        boolean appKeySign=this.appSecretManager.isValidAppKey(appKey);
        if (!appKeySign) {
            apiResult.setMsg(Msg.APPKEY_NOT_EXISTS);
            return apiResult;
        }
        //检查有效期
        long dValue = System.currentTimeMillis() / 1000 - timestamp;
        if (dValue > 600 || dValue < -600) {
            apiResult.setMsg(Msg.VALID_TIME);
            return apiResult;
        }

        //检查签名
        boolean checkSign = this.checkSign(appKey, timestamp, method, sign);
        if (!checkSign) {
            apiResult.setMsg(Msg.SIGN_ERROR);
            return apiResult;
        }

        //检查方法调用权限
        if (!this.checkMethod(appKey, method)) {
            apiResult.setMsg(Msg.NOT_UNAUTHORIZED);
            return apiResult;
        }


        //检查方法调用次数


        apiResult.setMsg(Msg.Success);
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
        return true;
    }


}
