package com.varela.wechat.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

public class WeChatConstUtil {

    private static final String FILE = "config/wechat";

    public static  String WECHAT_API_SECRET;

    public static  String WECHAT_APP_APPID;

    public static  String WECHAT_APP_SECRET;

    public static  String WECHAT_PAY_MCH_ID;

    public static  String SCAN_QRCODE_UNIFIEDORDER;

    public static  String SCAN_QRCODE_QUERYORDER;

    public static  String SCAN_QRCODE_CLOSEORDER;

    public static  String SCAN_QRCODE_REFUNDORDER;

    public static  String SCAN_QRCODE_QUERYREFUND;

    public static  String SCAN_QRCODE_2SHORTURL;

    public static String ACCESS_TOKEN_URL;

    public static String JSAPI_TICKET_URL;

    public static String WECHAT_NOTIFY_URL;



    private static final Logger logger = LoggerFactory
            .getLogger(WeChatConstUtil.class);


    private static Map<String, String> consts = null;


    static {
        consts = new HashMap<String, String>();
        ResourceBundle rs = ResourceBundle.getBundle(FILE);
        Set<String> keys = rs.keySet();
        for (String key : keys) {
            consts.put(key, rs.getString(key));
        }
        WECHAT_API_SECRET=consts.get(WeChatConstKey.WECHAT_API_SECRET);
        WECHAT_APP_APPID=consts.get(WeChatConstKey.WECHAT_APP_APPID);
        WECHAT_APP_SECRET=consts.get(WeChatConstKey.WECHAT_APP_SECRET);
        WECHAT_PAY_MCH_ID=consts.get(WeChatConstKey.WECHAT_PAY_MCH_ID);
        SCAN_QRCODE_UNIFIEDORDER=consts.get(WeChatConstKey.SCAN_QRCODE_UNIFIEDORDER);
        SCAN_QRCODE_QUERYORDER=consts.get(WeChatConstKey.SCAN_QRCODE_QUERYORDER);
        SCAN_QRCODE_CLOSEORDER=consts.get(WeChatConstKey.SCAN_QRCODE_CLOSEORDER);
        SCAN_QRCODE_REFUNDORDER=consts.get(WeChatConstKey.SCAN_QRCODE_REFUNDORDER);
        SCAN_QRCODE_QUERYREFUND=consts.get(WeChatConstKey.SCAN_QRCODE_QUERYREFUND);
        SCAN_QRCODE_2SHORTURL=consts.get(WeChatConstKey.SCAN_QRCODE_2SHORTURL);
        ACCESS_TOKEN_URL=consts.get(WeChatConstKey.ACCESS_TOKEN_URL);
        JSAPI_TICKET_URL=consts.get(WeChatConstKey.JSAPI_TICKET_URL);
        WECHAT_NOTIFY_URL=consts.get(WeChatConstKey.WECHAT_NOTIFY_URL);
    }

    /**
     * @param propertiesName
     * @param key
     * @return
     */
    public static String getProp(String key) {
        return consts.get(key);
    }
}
