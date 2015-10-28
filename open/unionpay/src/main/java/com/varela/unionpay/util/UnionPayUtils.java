package com.varela.unionpay.util;

import com.alibaba.fastjson.JSONObject;
import com.varela.unionpay.conf.UpmpConfig;
import com.varela.unionpay.pojo.RefundRequest;
import com.varela.unionpay.pojo.RefundResponse;
import com.varela.unionpay.service.UpmpService;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by guangping.lance on 2015-06-16.
 */
public class UnionPayUtils {

    /**
    * 退款
    * */
    public static RefundResponse refund(RefundRequest refundRequest){
        Map<String, String> req = new HashMap<String, String>();
        req.put("version", UpmpConfig.VERSION);// 版本号
        req.put("charset", UpmpConfig.CHARSET);// 字符编码
        req.put("transType", "04");// 交易类型
        req.put("merId", UpmpConfig.MER_ID);// 商户代码
        req.put("backEndUrl", UpmpConfig.MER_BACK_END_URL);// 通知URL
        req.put("orderTime", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));// 交易开始日期时间yyyyMMddHHmmss（退货交易新交易日期，非原交易日期）
        req.put("orderNumber", generateOrdrNo());// 订单号（退货交易新订单号，非原交易订单号）
        req.put("orderAmount",String.valueOf(refundRequest.getOrderAmount()));// 订单金额
        req.put("orderCurrency", "156");// 交易币种(可选)
        req.put("qn", refundRequest.getQn());// 查询流水号（原订单支付成功后获取的流水号）

        // 保留域填充方法
        Map<String, String> reqReserved = new HashMap<String, String>();
        reqReserved.put("extra_common_param", "orderCancelRefund");
        reqReserved.put("qn", refundRequest.getQn());
        reqReserved.put("orderId", refundRequest.getOrderId());
        req.put("reqReserved", JSONObject.toJSONString(reqReserved));// 请求方保留域(可选，用于透传商户信息)
       // req.put("merReserved", UpmpService.buildReserved(reqReserved));// 商户保留域(可选)

        // 保留域填充方法
        Map<String, String> merReservedMap = new HashMap<String, String>();
        merReservedMap.put("test", "test");
        req.put("merReserved", UpmpService.buildReserved(merReservedMap));// 商户保留域(可选)

        Map<String, String> resp = new HashMap<String, String>();
        boolean validResp = UpmpService.trade(req, resp);
        RefundResponse response=new RefundResponse();
        response.setSuccess(validResp);
        response.setResp(resp);
        return response;
    }

    public static String generateOrdrNo() {
        DateFormat formater = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        StringBuilder sb = new StringBuilder(formater.format(new Date()));
        return sb.toString();
    }
}
