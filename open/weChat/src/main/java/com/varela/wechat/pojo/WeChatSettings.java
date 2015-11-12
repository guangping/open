package com.varela.wechat.pojo;

import java.io.Serializable;

/**
 * Created by lance on 11/12/2015.
 */
public class WeChatSettings implements Serializable {

    /**
     * api秘钥
     *
     */
    private String apiSecret;

    /**
     * 公众号
     */
    private String appid;

    /**
     * app secret
     *
     */
    private String appSecret;

    /**
     * 微信支付商户号
     */
    private String mchId;


    public String getApiSecret() {
        return apiSecret;
    }

    public void setApiSecret(String apiSecret) {
        this.apiSecret = apiSecret;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    @Override
    public String toString() {
        return "WeChatSettings{" +
                "apiSecret='" + apiSecret + '\'' +
                ", appid='" + appid + '\'' +
                ", appSecret='" + appSecret + '\'' +
                ", mchId='" + mchId + '\'' +
                '}';
    }
}
