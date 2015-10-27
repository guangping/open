package com.varela;

import com.varela.alipay.pojo.AliPay;
import com.varela.alipay.pojo.AliRefund;
import com.varela.alipay.util.AliPayUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * Created by lance on 10/27/2015.
 */
@ContextConfiguration("classpath:applicationContext.xml")
public class AliPayTest extends AbstractTestNGSpringContextTests {


    /**
     * 及时到账
     * */
    @Test
    public void pcPay() {
        String outTradeNo = String.valueOf(System.currentTimeMillis());
        AliPay pay = new AliPay();
        pay.setPrice(1);
        pay.setSubject("订单支付");
        pay.setOutTradeNo(outTradeNo);

        String url = AliPayUtils.directPay(pay);
        System.out.println(url);
    }

    /**
     * wap支付
     *
     * **/
    @Test
    public void mobilePay() {
        String outTradeNo = String.valueOf(System.currentTimeMillis());
        com.varela.alipay.pojo.AliPay pay = new AliPay();
        pay.setPrice(1);
        pay.setSubject("订单支付");
        pay.setOutTradeNo(outTradeNo);

        String url = AliPayUtils.mobilePay(pay);
        System.out.println(url);
    }

    /**
     * 退款
     * */
    @Test
    public void refund() {
        FastDateFormat format=FastDateFormat.getInstance("yyyyMMddHHmmss");
        String batchBo = format.format(System.currentTimeMillis());
        String tradeNo = "2015090800001000960059396348";

        AliRefund refund = new AliRefund();
        refund.setBatchNo(batchBo);
        refund.setReason("取消退款");
        refund.setPrice(0.04);
        refund.setTradeNo(tradeNo);
        String url = AliPayUtils.refund(refund);
        System.out.printf("退款链接:" + url);

    }
}
