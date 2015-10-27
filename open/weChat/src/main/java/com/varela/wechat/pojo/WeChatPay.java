package com.varela.wechat.pojo;

import com.varela.wechat.util.RandomUtil;
import com.varela.wechat.util.WeChatConstUtil;

import java.io.Serializable;

public class WeChatPay implements Serializable {

    private static final long serialVersionUID = 1L;

    /*
     * 公众账号ID appid 是 String(32) wx8888888888888888,
     * 微信分配的公众账号ID（企业号corpid即为此appId）
     */
    private String appid = WeChatConstUtil.WECHAT_APP_APPID;
    // 商户号 mch_id 是 String(32) 1900000109 微信支付分配的商户号
    private String mch_id = WeChatConstUtil.WECHAT_PAY_MCH_ID;
    // 签名 sign 是 String(32) 5K8264ILTKCH16CQ2502SI8ZNMTM67VS 签名
    private String sign;

    // 子商户公众账号ID sub_appid 否 String(32)
    private String sub_appid;
    // 子商户号 sub_mch_id 是 String(32)
    private String sub_mch_id;
    // 设备号 device_info 否 String(32)
    // 终端设备号(门店号或收银设备ID)，注意：PC网页或公众号内支付请传"WEB"
    private String device_info;
    // 随机字符串 nonce_str 是 String(32)
    private String nonce_str = RandomUtil.getRandomStr();
    // 商品描述 body 是 String(32)
    private String body;
    // 商品详情 detail 否 String(8192)
    private String detail;
    // 附加数据 attach 否 String(127)
    private String attach;
    // 商户订单号 out_trade_no 是 String(32)
    // 商户系统内部的订单号,32个字符内、可包含字母, 其他说明见商户订单号
    private String out_trade_no;
    // 货币类型 fee_type 否 String(16) CNY 符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
    private String fee_type;
    // 总金额 total_fee 是 Int 888 订单总金额，只能为整数，详见支付金额
    private Integer total_fee;
    // 终端IP spbill_create_ip 是 String(16) 8.8.8.8
    // APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
    private String spbill_create_ip;
    // 交易起始时间 time_start 否 String(14) 20091225091010
    // 订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
    private String time_start;
    // 交易结束时间 time_expire 否 String(14) 20091227091010
    // 订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。其他详见时间规则
    private String time_expire;
    // 商品标记 goods_tag 否 String(32) WXG 商品标记，代金券或立减优惠功能的参数，说明详见代金券或立减优惠
    private String goods_tag;
    // 通知地址 notify_url 是 String(256) http://www.baidu.com/ 接收微信支付异步通知回调地址
    private String notify_url;
    // 交易类型 trade_type 是 String(16) JSAPI 取值如下：JSAPI，NATIVE，APP，详细说明见参数规定
    private String trade_type;
    // 商品ID product_id 否 String(32) 12235413214070356458058
    // trade_type=NATIVE，此参数必传。此id为二维码中包含的商品ID，商户自行定义。
    private String product_id;
    // 指定支付方式 limit_pay 否 String(32) no_credit no_credit--指定不能使用信用卡支付
    private String limit_pay;
    ;
    // 用户标识 openid 否 String(128) oUpF8uMuAJO_M2pxb1Q9zNjWeS6o
    // trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识。下单前需要调用【网页授权获取用户信息】接口获取到用户的Openid。
    private String openid;
    // 用户子标识 sub_openid 否 String(128) oUpF8uMuAJO_M2pxb1Q9zNjWeS6o
    // trade_type=JSAPI，此参数必传，用户在子商户appid下的唯一标识。下单前需要调用【网页授权获取用户信息】接口获取到用户的Openid。
    private String sub_openid;

    // 微信订单号 transaction_id 否 String(32) 013467007045764 微信的订单号，优先使用
    private String transaction_id;

    /*
     * 商户退款单号 out_refund_no 是 String(32) 1217752501201407033233368018,
     * 商户系统内部的退款单号，商户系统内部唯一，同一退款单号多次请求只退一笔
     */
    private String out_refund_no;

    // 退款金额 refund_fee 是 Int 100 退款总金额，订单总金额，单位为分，只能为整数，详见支付金额
    private Integer refund_fee;

    /*
     * 货币种类 refund_fee_type 否 String(8) CNY 货币类型，符合ISO,
     * 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
     */
    private String refund_fee_type;

    // 操作员 op_user_id 是 String(32) 1900000109 操作员帐号, 默认为商户号
    private String op_user_id;

    /*
     * 微信退款单号 refund_id 否 String(28)
     * refund_id、out_refund_no、out_trade_no、transaction_id四个参数必填一个， 如果同时存在优先级为：
     * refund_id > out_refund_no > transaction_id > out_trade_no
     */
    private String refund_id;

    // 需要转换的URL，签名用原串，传输需URLencode
    private String long_url;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSub_appid() {
        return sub_appid;
    }

    public void setSub_appid(String sub_appid) {
        this.sub_appid = sub_appid;
    }

    public String getSub_mch_id() {
        return sub_mch_id;
    }

    public void setSub_mch_id(String sub_mch_id) {
        this.sub_mch_id = sub_mch_id;
    }

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public Integer getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(Integer total_fee) {
        this.total_fee = total_fee;
    }

    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }

    public String getTime_start() {
        return time_start;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    public String getTime_expire() {
        return time_expire;
    }

    public void setTime_expire(String time_expire) {
        this.time_expire = time_expire;
    }

    public String getGoods_tag() {
        return goods_tag;
    }

    public void setGoods_tag(String goods_tag) {
        this.goods_tag = goods_tag;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getLimit_pay() {
        return limit_pay;
    }

    public void setLimit_pay(String limit_pay) {
        this.limit_pay = limit_pay;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSub_openid() {
        return sub_openid;
    }

    public void setSub_openid(String sub_openid) {
        this.sub_openid = sub_openid;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getOut_refund_no() {
        return out_refund_no;
    }

    public void setOut_refund_no(String out_refund_no) {
        this.out_refund_no = out_refund_no;
    }

    public Integer getRefund_fee() {
        return refund_fee;
    }

    public void setRefund_fee(Integer refund_fee) {
        this.refund_fee = refund_fee;
    }

    public String getRefund_fee_type() {
        return refund_fee_type;
    }

    public void setRefund_fee_type(String refund_fee_type) {
        this.refund_fee_type = refund_fee_type;
    }

    public String getOp_user_id() {
        return op_user_id;
    }

    public void setOp_user_id(String op_user_id) {
        this.op_user_id = op_user_id;
    }

    public String getRefund_id() {
        return refund_id;
    }

    public void setRefund_id(String refund_id) {
        this.refund_id = refund_id;
    }

    public String getLong_url() {
        return long_url;
    }

    public void setLong_url(String long_url) {
        this.long_url = long_url;
    }

}
