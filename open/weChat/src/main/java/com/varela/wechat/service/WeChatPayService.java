package com.varela.wechat.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.varela.wechat.pojo.PayResult;
import com.varela.wechat.pojo.ResponseResult;
import com.varela.wechat.pojo.WeChatPay;
import com.varela.wechat.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.TreeMap;

/**
 * Created by lance on 9/2/2015.
 */
@Service
public class WeChatPayService {

    private static final Logger logger = LoggerFactory
            .getLogger(WeChatPayService.class);

    /**
     * 统一下单
     *
     * @return
     * @throws Exception
     */
    public PayResult unifiedOrder(WeChatPay order) throws Exception {
        // 初始化appid,mch_id,nonce_str,sign
        this.initSomeParam(order);
        PayResult result = new PayResult();
        ParamCheckUtil.checkUnifiedOrder(result, order);
        if (result.getErrorCode() != 0) {
            return result;
        }
        this.execRequest(result, order, WeChatConstUtil.SCAN_QRCODE_UNIFIEDORDER, false);
        return result;
    }


    /**
     * 退款申请
     *
     * @param transaction_id 微信订单号
     * @param out_refund_no  商户退款单号
     * @param total_fee      总金额
     * @param refund_fee     退款金额
     */
    public PayResult refund(String transaction_id, String out_refund_no, int total_fee, int refund_fee) throws Exception {

        WeChatPay refund = new WeChatPay();
        refund.setTransaction_id(transaction_id);
        refund.setOut_refund_no(out_refund_no);
        refund.setTotal_fee(total_fee);
        refund.setRefund_fee(refund_fee);
        refund.setOp_user_id(WeChatConstUtil.WECHAT_PAY_MCH_ID);
        return this.refund(refund);
    }

    /**
     * 申请退款
     *
     * @throws Exception
     */
    private PayResult refund(WeChatPay order) throws Exception {
        this.initSomeParam(order);
        PayResult result = new PayResult();
        ParamCheckUtil.checkOrderRefund(result, order);
        if (result.getErrorCode() != 0) {
            return result;
        }
        this.execRequest(result, order, WeChatConstUtil.SCAN_QRCODE_REFUNDORDER, true);

        return result;
    }

    /**
     * 查询退款
     *
     * @param order
     * @return
     * @throws Exception
     */
    public PayResult queryRefund(WeChatPay order) throws Exception {
        this.initSomeParam(order);
        PayResult result = new PayResult();
        ParamCheckUtil.checkQueryRefundModel(result, order);
        if (result.getErrorCode() != 0) {
            return result;
        }
        this.execRequest(result, order, WeChatConstUtil.SCAN_QRCODE_QUERYREFUND, false);

        return result;
    }

    /**
     * 转换短链接
     *
     * @param model
     * @return
     * @throws Exception
     */
    public PayResult shortUrl(WeChatPay model) throws Exception {
        this.initSomeParam(model);
        PayResult result = new PayResult();
        ParamCheckUtil.check2ShortUrlModel(result, model);
        if (result.getErrorCode() != 0) {
            return result;
        }
        this.execRequest(result, model, WeChatConstUtil.SCAN_QRCODE_2SHORTURL, false);
        return result;
    }

    /**
     * 执行请求调用接口
     *
     * @param result
     * @param order
     * @param url    请求链接
     * @param isCert 是否携带安全证书
     * @throws Exception
     */
    private void execRequest(PayResult result, WeChatPay order, String url,
                             boolean isCert) throws Exception {
        logger.info("请求接口,{}", url);
        String xml = XStreamUtil.convertModel2Xml(order);
        logger.info("请求报文,{}", xml);
        String post = isCert ? HttpWeChatUtils.postWithCert(url, xml) : HttpWeChatUtils
                .post(url, xml);
        logger.info("请求返回报文,{}", post);

        ResponseResult orderresult = XStreamUtil.convetXml2Model(post,
                ResponseResult.class);
        result.setResult(orderresult);
    }

    /**
     * <p>
     *
     * @param order
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    private void initSomeParam(WeChatPay order)
            throws IllegalArgumentException, IllegalAccessException {
        TreeMap data = JSONObject.parseObject(JSON.toJSONString(order), TreeMap.class);
        // 生成签名
        order.setSign(SignUtil.generateSign(data));
    }
}
