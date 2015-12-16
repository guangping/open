package com.varela.wechat.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.varela.enumerate.Msg;
import com.varela.pojo.APIResult;
import com.varela.utils.http.HttpClientUtils;
import com.varela.utils.http.HttpResponse;
import com.varela.utils.properties.ResourceUtils;
import com.varela.wechat.pojo.*;
import com.varela.wechat.util.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Map;
import java.util.Set;
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
     * 生成加密参数
     */
    public TreeMap<String, String> getPaySignParam(String prepayId) {
        String pack = "prepay_id=" + prepayId;
        String timeStamp = String.valueOf(System.currentTimeMillis() / 1000);
        String nonceStr = RandomUtil.getRandomStr();

        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("appId", this.resourceUtils.getStringValue(WeChatConstKey.WECHAT_PUBLIC_APPID));
        params.put("timeStamp", timeStamp);
        params.put("nonceStr", nonceStr);
        params.put("package", pack);
        params.put("signType", WeChatConstKey.MD5);

        return params;
    }

    /**
     * 微信支付加密请求参数,返回paySign
     *
     * @param map
     * @return
     */
    public String encrpty(TreeMap<String, String> map) throws Exception {
        Set<Map.Entry<String, String>> entrySet = map.entrySet();
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : entrySet) {
            String key = entry.getKey();
            String val = entry.getValue();
            sb.append(key).append("=").append(val).append("&");
        }
        String secret = this.resourceUtils.getStringValue(WeChatConstKey.WECHAT_PUBLIC_API_SECRET);
        sb.append("key=").append(secret);

        logger.info("微信支付请求参数:{}", sb.toString());
        String sign = MD5Util.MD5Encode(sb.toString()).toUpperCase();
        logger.info("微信支付paySign签名:{}", sign);

        return sign;
    }


    /**
     * 生成微信授权地址
     * redirect_uri 回掉地址
     * scope根据需要进行调整
     */
    public String getWechatAuth(String no) {
        StringBuffer buffer = new StringBuffer(300);
        buffer.append(this.resourceUtils.getStringValue(WeChatConstKey.WECHAT_AUTHORIZE));
        buffer.append("appid=" + this.resourceUtils.getStringValue(WeChatConstKey.WECHAT_PUBLIC_APPID));
        buffer.append("&redirect_uri=");
        buffer.append(MessageFormat.format(this.resourceUtils.getStringValue(WeChatConstKey.WECHAT_REDIRECT_URL), no));
        buffer.append("&response_type=code&scope="+WeChatConstKey.SNSAPI_USERINFO);
        buffer.append("&state=STATE#wechat_redirect");

        return buffer.toString();
    }

    /**
     * 用户授权之后,微信服务器返回的用户标识,用户获取用户openid
     *
     * @param code 用户授权之后返回的code值
     */
    public APIResult getAuthAccessToken(String code) {
        APIResult apiResult = new APIResult();

        String url = this.resourceUtils.getStringValue(WeChatConstKey.WECHAT_AUTH);
        String appid = this.resourceUtils.getStringValue(WeChatConstKey.WECHAT_PUBLIC_APPID);
        String secret = this.resourceUtils.getStringValue(WeChatConstKey.WECHAT_PUBLIC_SECRET);
        String grant_type = WeChatConstKey.WX_GRANT_TYPE;

        StringBuffer sb = new StringBuffer();
        sb.append(url).append("appid=").append(appid);
        sb.append("&secret=").append(secret);
        sb.append("&code=").append(code);
        sb.append("&grant_type=").append(grant_type);

        logger.info("微信获取网页授权信息URL:｛｝", url);
        HttpResponse post = HttpClientUtils.postForm(sb.toString(), "");
        logger.info("微信获取网页授权信息,请求返回报文:{}", post.getResult());

        if (post.isSuccess()) {
            JSONObject jo = JSONObject.parseObject(post.getResult());
            apiResult.setResult(jo.getString("openid"));
            apiResult.setMsg(Msg.Success);
            return apiResult;
        }
        apiResult.setMsg(Msg.ERROR);
        return apiResult;
    }

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
    public UnifiedOrderResponse unifiedOrder(WeChatPay order) throws Exception {
        UnifiedOrderResponse response = new UnifiedOrderResponse();
        this.initSomeParam(order);
        PayResult result = new PayResult();
        ParamCheckUtil.checkUnifiedOrder(result, order);
        if (result.getErrorCode() != 0) {
            response.setErrCodeDes(result.getMessage());
            return response;
        }
        ResponseResult responseResult;
        this.execRequest(result, order, this.resourceUtils.getStringValue(WeChatConstKey.SCAN_QRCODE_UNIFIEDORDER), false);
        if (result.getErrorCode() == 0) {
            responseResult = (ResponseResult) result.getResult();
            String err_code = responseResult.getErr_code();
            String err_code_des = responseResult.getErr_code_des();
            if (responseResult.isSuccess()) {
                String codeUrl = responseResult.getCode_url();//二维码链接
                String tradeType = responseResult.getTrade_type();//交易类型
                String prepayId = responseResult.getPrepay_id();//预支付交易会话标识
                logger.info("二维码地址:{},交易类型:{},预支付交易会话标识:{}", codeUrl, tradeType, prepayId);

                response.setCodeUrl(codeUrl);
                response.setTradeType(tradeType);
                response.setPrepayId(prepayId);
            }
            response.setErrCode(err_code);
            response.setErrCodeDes(err_code_des);
        }
        return response;
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
