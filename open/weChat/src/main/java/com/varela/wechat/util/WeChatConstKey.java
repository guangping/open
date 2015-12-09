package com.varela.wechat.util;

/**
 * Created by lance on 8/24/2015.
 */
public class WeChatConstKey {

    /**
     * 公众号相关
     **/
    public static final String WECHAT_PUBLIC_API_SECRET = "wechat.public.api.secret";

    public static final String WECHAT_PUBLIC_APPID = "wechat.public.appid";

    public static final String WECHAT_PUBLIC_SECRET = "wechat.public.secret";

    public static final String WECHAT_PUBLIC_MCH_ID = "wechat.public.mchId";

    public static final String SCAN_QRCODE_UNIFIEDORDER = "wechat.scan.qrcode.unifiedorder";

    public static final String SCAN_QRCODE_QUERYORDER = "wechat.scan.qrcode.queryorder";

    public static final String SCAN_QRCODE_CLOSEORDER = "wechat.scan.qrcode.closeorder";

    public static final String SCAN_QRCODE_REFUNDORDER = "wechat.scan.qrcode.refundorder";

    public static final String SCAN_QRCODE_QUERYREFUND = "wechat.scan.qrcode.queryrefund";

    public static final String SCAN_QRCODE_2SHORTURL = "wechat.scan.qrcode.2shorturl";

    public static final String ACCESS_TOKEN_URL = "wechat.access.token.url";

    public static final String JSAPI_TICKET_URL = "wechat.jsapi.ticket.url";

    public static final String WECHAT_NOTIFY_URL = "wechat.notify.url";

    public static final String WECHAT_AUTHORIZE="wechat.authorize";

    public static final String WECHAT_AUTH = "wechat.auth";//微信获取用户网页授权下单

    public static final String WECHAT_REDIRECT_URL="wechat.redirect.url";

    public static final String SUCCESS = "SUCCESS";

    public static final String FAIL = "FAIL";

    public static final String DEVICE_INFO = "WEB";//设备号

    public static final String SIGN = "sign";

    public static final String OK = "OK";

    //交易类型
    public static final String TRADE_TYPE_JSAPI = "JSAPI";

    public static final String TRADE_TYPE_NATIVE = "NATIVE";

    public static final String TRADE_TYPE_APP = "APP";

    //用户取消授权
    public static final String AUTHDENY = "authdeny";

    public static final String WX_GRANT_TYPE = "authorization_code";// 微信授权相关类型

    public static final String MD5 = "MD5";


    /**
     * 微信授权 scope取值
     * snsapi_userinfo
     * snsapi_base
     * 1、以snsapi_base为scope发起的网页授权，是用来获取进入页面的用户的openid的，并且是静默授权并自动跳转到回调页的。用户感知的就是直接进入了回调页（往往是业务页面）
     * 2、以snsapi_userinfo为scope发起的网页授权，是用来获取用户的基本信息的。但这种授权需要用户手动同意，并且由于用户同意过，所以无须关注，就可在授权后获取该用户的基本信息。
     * */
    public static final String SNSAPI_USERINFO="snsapi_userinfo";
    public static final String SNSAPI_BASE="snsapi_base";

}
