package com.varela.unionpay.util;

import com.alibaba.fastjson.JSONObject;
import com.varela.unionpay.conf.UpmpConfig;
import com.varela.unionpay.pojo.RefundRequest;
import com.varela.unionpay.pojo.RefundResponse;
import com.varela.unionpay.pojo.UnionPayRequest;
import com.varela.unionpay.pojo.UnionPayResponse;
import com.varela.unionpay.service.UpmpService;
import org.apache.commons.lang3.time.FastDateFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by guangping.lance on 2015-06-16.
 */
public class UnionPayUtils {

    /**
     * 退款
     */
    public static RefundResponse refund(RefundRequest refundRequest) {

        Map<String, String> req = new HashMap<String, String>();
        req.put("version", UpmpConfig.VERSION);// 版本号
        req.put("charset", UpmpConfig.CHARSET);// 字符编码
        req.put("transType", "04");// 交易类型
        req.put("merId", UpmpConfig.MER_ID);// 商户代码
        req.put("backEndUrl", UpmpConfig.MER_BACK_END_URL);// 通知URL
        // 交易开始日期时间yyyyMMddHHmmss（退货交易新交易日期，非原交易日期）
        req.put("orderTime", FastDateFormat.getInstance("yyyyMMddHHmmss").format(new Date()));
        req.put("orderNumber", refundRequest.getRefundNo());// 订单号（退货交易新订单号，非原交易订单号）
        req.put("orderAmount", String.valueOf(refundRequest.getOrderAmount()));// 订单金额
        req.put("orderCurrency", "156");// 交易币种(可选)
        req.put("qn", refundRequest.getQn());// 查询流水号（原订单支付成功后获取的流水号）

        // 请求方保留域(可选，用于透传商户信息)
        req.put("reqReserved", JSONObject.toJSONString(refundRequest.getExtraParam()));
        // 保留域填充方法
/*        Map<String, String> merReservedMap = new HashMap<String, String>();
        merReservedMap.put("test", "test");
        req.put("merReserved", UpmpService.buildReserved(merReservedMap));// 商户保留域(可选)*/

        Map<String, String> resp = new HashMap<String, String>();
        boolean validResp = UpmpService.trade(req, resp);
        RefundResponse response = new RefundResponse();
        response.setSuccess(validResp);
        response.setResp(resp);
        return response;
    }


    /**
     * 获取支付流水
     */
    public static UnionPayResponse getTradNumber(UnionPayRequest request) {

        UnionPayResponse response = new UnionPayResponse();

        // 银联请求参数
        Map<String, String> req = new HashMap<String, String>();
        req.put("version", UpmpConfig.VERSION); // 版本号
        req.put("charset", UpmpConfig.CHARSET); // 字符编码
        req.put("signMethod", UpmpConfig.SIGN_METHOD); // 签名方法
        req.put("signature", UpmpConfig.SIGNATURE); // 签名信息
        req.put("transType", "01"); // 交易类型
        req.put("merId", UpmpConfig.MER_ID); // 商户代码
        req.put("backEndUrl", UpmpConfig.MER_BACK_END_URL); // 通知URL
        req.put("orderDescription", request.getOrderDescription()); // 订单描述(可选)
        // 交易开始日期时间yyyyMMddHHmmss
        req.put("orderTime", FastDateFormat.getInstance("yyyyMMddHHmmss").format(new Date()));
        req.put("orderNumber", request.getOrderNumber()); // 订单号(商户根据自己需要生成订单号)
        req.put("orderAmount", String.valueOf(request.getOrderAmount())); // 订单金额
        // (分)
        req.put("reqReserved", JSONObject.toJSONString(request.getExtraParam()));// 保留域
        Map<String, String> resp = new HashMap<String, String>();
        boolean validResp = UpmpService.trade(req, resp);
        if (validResp) {
            //交易号
            String tn = resp.get("tn");
            //银联返回码
            String respCode = resp.get(UpmpConfig.RESPONSE_CODE);
            //描述
            String respMsg = resp.get(UpmpConfig.RESPONSE_MSG);
            if (respCode.equals(UpmpConfig.RESPONSE_CODE_SUCCESS)) {
                response.setSuccess(true);
                response.setTn(tn);
            }
            response.setRespCode(respCode);
            response.setRespMsg(respMsg);
        }
        return response;
    }

}
