package com.varela.wechat.service;

import com.alibaba.fastjson.JSON;
import com.varela.utils.properties.ResourceUtils;
import com.varela.wechat.pojo.WeChatSettings;
import com.varela.wechat.util.WeChatConstKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lance on 11/12/2015.
 */
@Service
public class WeChatSettingsService {

    private Logger logger = LoggerFactory.getLogger(WeChatSettingsService.class);


    @Autowired
    private ResourceUtils resourceUtils;

    private Map<String, WeChatSettings> settingsMap;

    @PostConstruct
    public void init() {
        logger.info("init wechat settings!");
        WeChatSettings settings = new WeChatSettings();
        settings.setAppid(this.resourceUtils.getStringValue(WeChatConstKey.WECHAT_PUBLIC_APPID));
        settings.setApiSecret(this.resourceUtils.getStringValue(WeChatConstKey.WECHAT_PUBLIC_API_SECRET));

        settings.setAppSecret(this.resourceUtils.getStringValue(WeChatConstKey.WECHAT_PUBLIC_SECRET));
        settings.setMchId(this.resourceUtils.getStringValue(WeChatConstKey.WECHAT_PUBLIC_MCH_ID));

        settingsMap = new HashMap<String, WeChatSettings>();
        settingsMap.put(settings.getAppid(), settings);

        logger.info("wechat settings==>{}", JSON.toJSONString(settingsMap));
    }


    /**
     * 获取微信配置信息
     */
    public WeChatSettings getWeChatSettings(String appid) {
        return this.settingsMap.get(appid);
    }


}
