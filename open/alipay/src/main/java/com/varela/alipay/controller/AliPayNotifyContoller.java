package com.varela.alipay.controller;

import com.alibaba.fastjson.JSONObject;
import com.varela.alipay.config.AlipayConfig;
import com.varela.alipay.util.AlipayNotify;
import com.varela.controller.BaseController;
import com.varela.utils.WebUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * Created by lance on 10/28/2015.
 * 支付宝回调
 */
@Controller
@Scope("prototype")
public class AliPayNotifyContoller extends BaseController {


    @RequestMapping(value = "", method = {RequestMethod.GET, RequestMethod.POST})
    public String execute() {
        Map<String, String> params = WebUtils.getParams(this.request);
        logger.info("充值支付宝回调:{}", JSONObject.toJSONString(params));

        //通知类型
        String notify_type = params.get("notify_type");

        //验证参数
        if (!AlipayNotify.verify(params)) {
            logger.info("支付宝验证失败!");
        }

        //交易状态
        if (notify_type.equals(AlipayConfig.TRADE_STATUS_SYNC)) {
            //商户订单号
            String outTradeNo = params.get("out_trade_no");
            //支付宝交易号
            String tradeNo = params.get("trade_no");
            //交易状态
            String tradeStatus = params.get("trade_status");
            //支付人账号
            String buyer_email = params.get("buyer_email");
            //支付金额
            double price = Double.valueOf(params.get("price"));
            //错误编码
            String errorCode=params.get("error_code");

            if (tradeStatus.equals(AlipayConfig.TradeStatus.TRADE_SUCCESS)) {

            }

        }
        //批量退款
        if (notify_type.equals(AlipayConfig.BATCH_REFUND_NOTIFY)) {
            String batch_no = params.get("batch_no");// 退款批次号
            String success_num = params.get("success_num");// 成功笔数
            String result_details = params.get("result_details");// 退款结果明细
        }
        return null;
    }
}
