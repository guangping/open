package com.varela.wechat.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.varela.utils.properties.ResourceUtils;
import com.varela.wechat.pojo.*;
import com.varela.wechat.util.ParamCheckUtil;
import com.varela.wechat.util.SignUtil;
import com.varela.wechat.util.WeChatConstKey;
import com.varela.wechat.util.XStreamUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.TreeMap;

/**
 * Created by lance on 9/2/2015.
 */
@Service
@Scope("prototype")
public class WeChatService {
    private static final Logger logger = LoggerFactory
            .getLogger(WeChatService.class);

    @Autowired
    private ResourceUtils resourceUtils;

    @Autowired
    private WeChatSettingsService weChatSettingsService;

    @Autowired
    private HttpWeChatUtils httpWeChatUtils;

    /**
     * 获取token
     */
    public VoucherResult getAccessToken(String appId) {
        WeChatSettings settings = this.weChatSettingsService.getWeChatSettings(appId);
        VoucherResult result = new VoucherResult();
        StringBuilder builder = new StringBuilder(200);
        builder.append(this.resourceUtils.getStringValue(WeChatConstKey.ACCESS_TOKEN_URL));
        builder.append("&appid=" + appId);
        builder.append("&secret=" + settings.getAppSecret());

        try {
            String rval = this.httpWeChatUtils.get(builder.toString(), null);
            logger.info("获取微信token:" + rval);
            JSONObject json = JSONObject.parseObject(rval);

            String errcode = json.getString("errcode");
            String errmsg = json.getString("errmsg");
            String accessToken = json.getString("access_token");
            String expiresIn = json.getString("expires_in");

            result.setErrcode(errcode);
            result.setErrmsg(errmsg);
            if (StringUtils.isBlank(errcode)) {
                result.setSuccess(true);
                result.setAccessToken(accessToken);
                result.setExpiresIn(Integer.valueOf(expiresIn));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取 jsapi_ticket
     */
    public VoucherResult getJsapiTicket(String accessToken) {
        VoucherResult result = new VoucherResult();
        
        StringBuilder builder = new StringBuilder(200);
        builder.append(this.resourceUtils.getStringValue(WeChatConstKey.JSAPI_TICKET_URL));
        builder.append("&access_token=" + accessToken);
        builder.append("&type=" + this.resourceUtils.getStringValue(WeChatConstKey.TRADE_TYPE_JSAPI));

        try {
            String rval = this.httpWeChatUtils.get(builder.toString(), null);
            logger.info("获取微信jsapi_ticket:" + rval);
            JSONObject json = JSONObject.parseObject(rval);
            String errcode = json.getString("errcode");
            String errmsg = json.getString("errmsg");
            String ticket = json.getString("ticket");
            String expiresIn = json.getString("expires_in");

            result.setErrcode(errcode);
            result.setErrmsg(errmsg);
            if (StringUtils.isBlank(errcode) || errcode.equals("0")) {
                result.setSuccess(true);
                result.setTicket(ticket);
                result.setExpiresIn(Integer.valueOf(expiresIn));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 统一下单
     *
     * @return
     * @throws Exception
     */
    public PayResult unifiedOrder(WeChatPay order) throws Exception {
        this.initSomeParam(order);
        PayResult result = new PayResult();
        ParamCheckUtil.checkUnifiedOrder(result, order);
        if (result.getErrorCode() != 0) {
            return result;
        }
        this.execRequest(result, order, this.resourceUtils.getStringValue(WeChatConstKey.SCAN_QRCODE_UNIFIEDORDER), false);
        return result;
    }


    /**
     * 退款申请
     *
     * @param weChatRefund 微信订单号
     */
    public PayResult refund(WeChatRefund weChatRefund) throws Exception {

        WeChatPay refund = new WeChatPay();
        refund.setTransaction_id(weChatRefund.getTransactionId());
        refund.setOut_refund_no(weChatRefund.getOutRefundNo());
        refund.setTotal_fee(weChatRefund.getTotalFee());
        refund.setRefund_fee(weChatRefund.getRefundFee());
        refund.setOp_user_id(weChatRefund.getOpUserId());
        refund.setAppid(weChatRefund.getAppId());
        refund.setMch_id(weChatRefund.getMchId());

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
        this.execRequest(result, order, this.resourceUtils.getStringValue(WeChatConstKey.SCAN_QRCODE_REFUNDORDER), true);

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
        this.execRequest(result, order, this.resourceUtils.getStringValue(WeChatConstKey.SCAN_QRCODE_QUERYREFUND), false);

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
        this.execRequest(result, model, this.resourceUtils.getStringValue(WeChatConstKey.SCAN_QRCODE_2SHORTURL), false);
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
        String post = isCert ? this.httpWeChatUtils.postWithCert(url, xml, order.getAppid()) : this.httpWeChatUtils
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
        String apiSecret = this.weChatSettingsService.getWeChatSettings(order.getAppid()).getApiSecret();

        // 生成签名
        order.setSign(SignUtil.generateSign(data, apiSecret));
    }
}
