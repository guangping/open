package com.varela.alipay.config;

import com.varela.spring.SpringApplicationContext;
import com.varela.utils.properties.ResourceUtils;

/**
 * 支付宝相关
 */
public class AlipayConfig {


    private static ResourceUtils resourceUtils= SpringApplicationContext.getBean(ResourceUtils.class);


    /**
     * 支付宝提供给商户的服务接入网关URL(新)
     */
    public static final String ALIPAY_GATEWAY_NEW = "https://mapi.alipay.com/gateway.do?";

    // 合作身份者ID，以2088开头由16位纯数字组成的字符串
    public static String PARTNER = resourceUtils.getStringValue(AliPayKey.ALIPAY_PARTNER);

    public static String SELLER_EMAIL = resourceUtils.getStringValue(AliPayKey.ALIPAY_SELLER_EMAIL);

    // 字符编码格式 目前支持 gbk 或 utf-8
    public static String INPUT_CHARSET = "UTF-8";

    // 签名方式 不需修改
    public static String MD5 = "MD5";

    public static String MD5_KEY = resourceUtils.getStringValue(AliPayKey.ALIPAY_MD5_KEY);

    public static final String SIGN_TYPE = "sign_type";

    public static final String SIGN = "sign";

    public static String NOTIFY_URL = resourceUtils.getStringValue(AliPayKey.ALIPAY_NOTIFY_URL);

    /**
     * 通知类型
     */
    public static String TRADE_STATUS_SYNC = "trade_status_sync";// 交易状态

    public static String BATCH_REFUND_NOTIFY = "batch_refund_notify";// 批量退款

    /**
     * 支付宝服务
     */
    public static final String REFUND_FASTPAY_BY_PLATFORM_PWD = "refund_fastpay_by_platform_pwd";//退款服务
    public static final String MOBILE_SERVICE = "alipay.wap.create.direct.pay.by.user";//手机网站支付服务
    public static final String CREATE_DIRECT_PAY_BY_USER = "create_direct_pay_by_user";//即时到账


    //交易状态
    public static class TradeStatus{
        //交易创建，等待买家付款。
        public static final String WAIT_BUYER_PAY="WAIT_BUYER_PAY";

        //在指定时间段内未支付时关闭的交易；在交易完成全额退款成功时关闭的交易。
        public static final String TRADE_CLOSED ="TRADE_CLOSED";

        //交易成功，且可对该交易做操作，如：多级分润、退款等
        public static final String TRADE_SUCCESS="TRADE_SUCCESS";

        //等待卖家收款（买家付款后，如果卖家账号被冻结）
        public static final String TRADE_PENDING="TRADE_PENDING";

        //交易成功且结束，即不可再做任何操作
        public static final String TRADE_FINISHED="TRADE_FINISHED";
    }
}
