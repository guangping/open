package com.varela.wechat.service;

import com.alibaba.fastjson.JSONObject;
import com.varela.wechat.pojo.VoucherResult;
import com.varela.wechat.util.HttpWeChatUtils;
import com.varela.wechat.util.WeChatConstKey;
import com.varela.wechat.util.WeChatConstUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 微信凭证
 * <p>
 * Created by lance on 8/24/2015.
 */
@Component
public class WeChatVoucherService {

    private Logger logger = LoggerFactory.getLogger(WeChatVoucherService.class);



    /**
     * 获取token
     */
    public VoucherResult getAccessToken() {
        //&appid=APPID&secret=APPSECRET
        VoucherResult result = new VoucherResult();

        StringBuilder builder = new StringBuilder(200);
        builder.append(WeChatConstUtil.ACCESS_TOKEN_URL);
        builder.append("&appid=" + WeChatConstUtil.WECHAT_APP_APPID);
        builder.append("&secret=" + WeChatConstUtil.WECHAT_APP_SECRET);

        try {
            String rval = HttpWeChatUtils.get(builder.toString(), null);
            logger.info("获取微信token:" + rval);
            JSONObject json = JSONObject.parseObject(rval);

            String errcode = json.getString("errcode");
            String errmsg = json.getString("errmsg");
            String accessToken = json.getString("access_token");
            String expiresIn = json.getString("expires_in");

            result.setErrcode(errcode);
            result.setErrmsg(errmsg);
            if (StringUtils.isBlank(errcode)) {
                result.setSuccess(true);
                result.setAccessToken(accessToken);
                result.setExpiresIn(Integer.valueOf(expiresIn));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 获取 jsapi_ticket
     */
    public VoucherResult getJsapiTicket(String accessToken) {
        //&appid=APPID&secret=APPSECRET
        VoucherResult result = new VoucherResult();

        StringBuilder builder = new StringBuilder(200);
        builder.append(WeChatConstUtil.JSAPI_TICKET_URL);
        builder.append("&access_token=" + accessToken);
        builder.append("&type=" + WeChatConstKey.TRADE_TYPE_JSAPI);

        try {
            String rval = HttpWeChatUtils.get(builder.toString(), null);
            logger.info("获取微信jsapi_ticket:" + rval);
            JSONObject json = JSONObject.parseObject(rval);


            String errcode = json.getString("errcode");
            String errmsg = json.getString("errmsg");
            String ticket = json.getString("ticket");
            String expiresIn = json.getString("expires_in");

            result.setErrcode(errcode);
            result.setErrmsg(errmsg);
            if (StringUtils.isBlank(errcode) || errcode.equals("0")) {
                result.setSuccess(true);
                result.setTicket(ticket);
                result.setExpiresIn(Integer.valueOf(expiresIn));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 初始化 token jsapi
     * */
    @PostConstruct
    public void init() {
      /*  logger.info("初始化:accessToken,jsapiTicket");
        String accessKey = RedisKey.WECHAT_JS_ACCESS_TOKEN_KEY;
        String ticketKey = RedisKey.WECHAT_JS_JSAPI_TICKET_KEY;

        String accessToken = redisCache.get(accessKey);
        String ticket = redisCache.get(ticketKey);
        if (StringUtils.isBlank(accessToken)) {
            VoucherResult tokenResult = this.getAccessToken();
            if (tokenResult.isSuccess()) {
                accessToken = tokenResult.getAccessToken();

                if (StringUtils.isNotBlank(accessToken)) {
                    VoucherResult ticketResult = this.getJsapiTicket(accessToken);
                    if (ticketResult.isSuccess()) {
                        ticket = ticketResult.getTicket();
                        this.redisCache.set(ticketKey, ticket, RedisKey.REDIS_7000S_EXPIRING);
                    }
                    this.redisCache.set(accessKey, accessToken, RedisKey.REDIS_7000S_EXPIRING);
                }
            }
        }
        if (StringUtils.isBlank(ticket) && StringUtils.isNotBlank(accessToken)) {
            VoucherResult ticketResult = this.getJsapiTicket(accessToken);
            if (ticketResult.isSuccess()) {
                ticket = ticketResult.getTicket();
                this.redisCache.set(ticketKey, ticket, RedisKey.REDIS_7000S_EXPIRING);
            }
        }
        logger.info("初始化:accessToken={},jsapiTicket={}", accessToken, ticket);*/
    }

    /**
     * 退款
     * */



}
