package com.varela.alipay.util;

import com.varela.alipay.config.AlipayConfig;
import com.varela.alipay.pojo.AliPay;
import com.varela.alipay.pojo.AliRefund;
import com.varela.alipay.sign.MD5;
import com.varela.utils.NumberFormat;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import java.util.HashMap;
import java.util.Map;

/**
 * 支付宝相关
 * 生成及时到账链接
 * 生成手机网站链接
 * 生产退款链接
 */
public class AliPayUtils {


    /**
     * 网站支付
     */
    public static String directPay(AliPay aliPay) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("_input_charset", AlipayConfig.INPUT_CHARSET);
        params.put("service", AlipayConfig.CREATE_DIRECT_PAY_BY_USER);
        params.put("partner", AlipayConfig.PARTNER);
        params.put("seller_id", AlipayConfig.PARTNER);
        params.put("payment_type", "1");//支付类型
        params.put("notify_url", AlipayConfig.NOTIFY_URL);//默认通知链接
        if (StringUtils.isNotBlank(aliPay.getNotifyUrl())) {
            params.put("notify_url", aliPay.getNotifyUrl());
        }
        if (StringUtils.isNotBlank(aliPay.getReturnUrl())) {
            params.put("return_url", aliPay.getReturnUrl());
        }
        params.put("out_trade_no", aliPay.getOutTradeNo());
        params.put("subject", aliPay.getSubject());
        params.put("total_fee", NumberFormat.decimalFormat(aliPay.getPrice()));//交易金额

        Map<String, String> sPara = AlipayCore.paraFilter(params);
        String content = AlipayCore.createLinkString(sPara);

        String sign = MD5.sign(content, AlipayConfig.MD5_KEY,
                AlipayConfig.INPUT_CHARSET);
        StringBuilder builder = new StringBuilder(300);
        builder.append(AlipayConfig.ALIPAY_GATEWAY_NEW);
        builder.append(content);
        builder.append("&sign=" + sign);
        builder.append("&sign_type=" + AlipayConfig.MD5);

        return builder.toString();
    }


    /**
     * 手机网站支付
     */
    public static String mobilePay(AliPay mobilePay) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("_input_charset", AlipayConfig.INPUT_CHARSET);
        params.put("service", AlipayConfig.MOBILE_SERVICE);
        params.put("partner", AlipayConfig.PARTNER);
        params.put("seller_id", AlipayConfig.PARTNER);
        params.put("payment_type", "1");//支付类型
        params.put("notify_url", AlipayConfig.NOTIFY_URL);//默认通知链接
        if (StringUtils.isNotBlank(mobilePay.getNotifyUrl())) {
            params.put("notify_url", mobilePay.getNotifyUrl());
        }
        if (StringUtils.isNotBlank(mobilePay.getReturnUrl())) {
            params.put("return_url", mobilePay.getReturnUrl());
        }
        params.put("out_trade_no", mobilePay.getOutTradeNo());
        params.put("subject", mobilePay.getSubject());
        params.put("total_fee", NumberFormat.decimalFormat(mobilePay.getPrice()));//交易金额

        Map<String, String> sPara = AlipayCore.paraFilter(params);
        String content = AlipayCore.createLinkString(sPara);

        String sign = MD5.sign(content, AlipayConfig.MD5_KEY,
                AlipayConfig.INPUT_CHARSET);
        StringBuilder builder = new StringBuilder(300);
        builder.append(AlipayConfig.ALIPAY_GATEWAY_NEW);
        builder.append(content);
        builder.append("&sign=" + sign);
        builder.append("&sign_type=" + AlipayConfig.MD5);

        return builder.toString();
    }


    /**
     * 退款链接
     */
    public static String refund(AliRefund aliRefund) {
        FastDateFormat format=FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");

        String tradeNo = aliRefund.getTradeNo();
        double price = aliRefund.getPrice();
        String reason = aliRefund.getReason();

        Map<String, String> params = new HashMap<String, String>();
        params.put("_input_charset", AlipayConfig.INPUT_CHARSET);
        params.put("service", AlipayConfig.REFUND_FASTPAY_BY_PLATFORM_PWD);
        params.put("partner", AlipayConfig.PARTNER);
        params.put("seller_email", AlipayConfig.SELLER_EMAIL);
        params.put("seller_user_id", AlipayConfig.PARTNER);
        params.put("refund_date", format.format(System.currentTimeMillis()));
        params.put("batch_no", aliRefund.getBatchNo());
        /**
         * 即参数 detail_data 的值 中，“#”字符出现的数量 加 1，最大支持 1000 笔（即 “#”字符出现的最大数量 为 999 个）。
         */
        params.put("batch_num", String.valueOf(1));
        // 原付款支付宝交易号^退款总金额^退款理由
        params.put("detail_data", tradeNo + "^" + price + "^" + reason);
        params.put("notify_url", AlipayConfig.NOTIFY_URL);//默认通知链接
        if (StringUtils.isNotBlank(aliRefund.getNotifyUrl())) {
            params.put("notify_url", aliRefund.getNotifyUrl());
        }
        Map<String, String> sPara = AlipayCore.paraFilter(params);
        String content = AlipayCore.createLinkString(sPara);

        String sign = MD5.sign(content, AlipayConfig.MD5_KEY,
                AlipayConfig.INPUT_CHARSET);
        StringBuilder builder = new StringBuilder(300);
        builder.append(AlipayConfig.ALIPAY_GATEWAY_NEW);
        builder.append(content);
        builder.append("&sign=" + sign);
        builder.append("&sign_type=" + AlipayConfig.MD5);

        return builder.toString();
    }

}
