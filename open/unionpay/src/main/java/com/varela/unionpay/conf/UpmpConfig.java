package com.varela.unionpay.conf;

import com.varela.spring.SpringApplicationContext;
import com.varela.utils.properties.ResourceUtils;


/**
 * 类名：配置类 功能：配置类 类属性：公共类 版本：1.0 日期：2012-10-11 作者：中国银联UPMP团队 版权：中国银联
 * 说明：以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己的需要，按照技术文档编写,并非一定要使用该代码。该代码仅供参考。
 */
public class UpmpConfig {

    static class Key{
         static final String KEY_VERSION = "upmp.version";
         static final String KEY_CHARSET = "upmp.charset";
         static final String KEY_TRADE_URL = "upmp.trade.url";
         static final String KEY_QUERY_URL = "upmp.query.url";
         static final String KEY_MER_ID = "upmp.mer.id";
         static final String KEY_MER_BACK_END_URL = "upmp.mer.back.end.url";
         static final String KEY_MER_FRONT_END_URL = "upmp.mer.front.end.url";
         static final String KEY_SIGN_METHOD = "upmp.sign.method";// 签名方式
         static final String KEY_SECURITY_KEY = "upmp.security.key";// 商户秘钥
    }

    private static ResourceUtils resourceUtils = SpringApplicationContext.getBean(ResourceUtils.class);

    // 版本号
    public static String VERSION= resourceUtils.getStringValue(Key.KEY_VERSION);

    // 编码方式
    public static String CHARSET= resourceUtils.getStringValue(Key.KEY_CHARSET);

    // 交易网址
    public static String TRADE_URL=resourceUtils.getStringValue(Key.KEY_TRADE_URL);

    // 查询网址
    public static String QUERY_URL=resourceUtils.getStringValue(Key.KEY_QUERY_URL);

    // 商户代码
    public static String MER_ID=resourceUtils.getStringValue(Key.KEY_MER_ID);

    // 通知URL
    public static String MER_BACK_END_URL=resourceUtils.getStringValue(Key.KEY_MER_BACK_END_URL);

    // 前台通知URL
    public static String MER_FRONT_END_URL=resourceUtils.getStringValue(Key.KEY_MER_FRONT_END_URL);

    // 返回URL
    public static String MER_FRONT_RETURN_URL;

    // 加密方式
    public static String SIGN_TYPE=resourceUtils.getStringValue(Key.KEY_SIGN_METHOD);

    // 商城密匙
    public static String SECURITY_KEY=resourceUtils.getStringValue(Key.KEY_SECURITY_KEY);;

    // 成功应答码
    public static final String RESPONSE_CODE_SUCCESS = "00";

    // 签名
    public final static String SIGNATURE = "signature";

    // 签名方法
    public final static String SIGN_METHOD = "signMethod";

    // 应答码
    public final static String RESPONSE_CODE = "respCode";

    // 应答信息
    public final static String RESPONSE_MSG = "respMsg";


}
