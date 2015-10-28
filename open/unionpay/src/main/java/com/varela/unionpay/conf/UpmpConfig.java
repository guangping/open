package com.varela.unionpay.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 类名：配置类 功能：配置类 类属性：公共类 版本：1.0 日期：2012-10-11 作者：中国银联UPMP团队 版权：中国银联
 * 说明：以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己的需要，按照技术文档编写,并非一定要使用该代码。该代码仅供参考。
 */
public class UpmpConfig {

    // 版本号
    public static String VERSION;

    // 编码方式
    public static String CHARSET;

    // 交易网址
    public static String TRADE_URL;

    // 查询网址
    public static String QUERY_URL;

    // 商户代码
    public static String MER_ID;

    // 通知URL
    public static String MER_BACK_END_URL;

    // 前台通知URL
    public static String MER_FRONT_END_URL;

    // 返回URL
    public static String MER_FRONT_RETURN_URL;

    // 加密方式
    public static String SIGN_TYPE;

    // 商城密匙
    public static String SECURITY_KEY;

    private static final String KEY_VERSION = "version";
    private static final String KEY_CHARSET = "charset";
    private static final String KEY_TRADE_URL = "upmp.trade.url";
    private static final String KEY_QUERY_URL = "upmp.query.url";
    private static final String KEY_MER_ID = "mer.id";
    private static final String KEY_MER_BACK_END_URL = "mer.back.end.url";
    private static final String KEY_MER_FRONT_END_URL = "mer.front.end.url";
    private static final String KEY_SIGN_METHOD = "sign.method";// 签名方式
    private static final String KEY_SECURITY_KEY = "security.key";// 商户秘钥

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

    private static final String CONF_FILE_NAME = "upmp";

    private static final Logger logger = LoggerFactory
            .getLogger(UpmpConfig.class);

    static {

        try {
            //读取配置文件参数
        /*	VERSION = ConstantUtil
                    .getPropertiesStr(CONF_FILE_NAME, KEY_VERSION);
			CHARSET = ConstantUtil
					.getPropertiesStr(CONF_FILE_NAME, KEY_CHARSET);
			TRADE_URL = ConstantUtil.getPropertiesStr(CONF_FILE_NAME,
					KEY_TRADE_URL);
			QUERY_URL = ConstantUtil.getPropertiesStr(CONF_FILE_NAME,
					KEY_QUERY_URL);
			MER_ID = ConstantUtil.getPropertiesStr(CONF_FILE_NAME, KEY_MER_ID);
			MER_BACK_END_URL = ConstantUtil.getPropertiesStr(CONF_FILE_NAME,
					KEY_MER_BACK_END_URL);
			MER_FRONT_END_URL = ConstantUtil.getPropertiesStr(CONF_FILE_NAME,
					KEY_MER_FRONT_END_URL);
			SIGN_TYPE = ConstantUtil.getPropertiesStr(CONF_FILE_NAME,
					KEY_SIGN_METHOD);
			SECURITY_KEY = ConstantUtil.getPropertiesStr(CONF_FILE_NAME,
					KEY_SECURITY_KEY);*/
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }


    public static final String SUCCESS="00";

}
