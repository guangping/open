package com.api.security;

import com.api.pojo.APIKey;
import com.varela.enumerate.APIMsg;
import com.varela.pojo.APIResult;
import com.varela.utils.StringCommonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;

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
        Object object = request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        if (StringUtils.isBlank(sign)) {
            apiResult.setMsg(APIMsg.SIGN_IS_NULL);
            return apiResult;
        }
        if (StringUtils.isBlank(appKey)) {
            apiResult.setMsg(APIMsg.APPKEY_IS_NULL);
            return apiResult;
        }
        if (this.appSecretManager.isValidAppKey(appKey)) {
            apiResult.setMsg(APIMsg.APPKEY_NOT_EXISTS);
            return apiResult;
        }
        long dValue = System.currentTimeMillis() / 1000 - timestamp;
        if (dValue > 600 || dValue < -600) {
            apiResult.setMsg(APIMsg.VALID_TIME);
            return apiResult;
        }

        //检查签名
        boolean checkSign=this.checkSign();
        if(!checkSign){
            apiResult.setMsg(APIMsg.SIGN_ERROR);
            return apiResult;
        }


        apiResult.setMsg(APIMsg.Success);
        return apiResult;
    }

    private boolean checkSign(){
        return true;
    }

}
