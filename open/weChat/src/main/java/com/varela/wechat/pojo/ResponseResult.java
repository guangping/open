package com.varela.wechat.pojo;

import com.varela.wechat.util.WeChatConstKey;

public class ResponseResult extends WeChatResult {

    private boolean success = false;//结果

    // 公众账号ID appid 是 String(32) wx8888888888888888 微信分配的公众账号ID
    private String appid;
    // 商户号 mch_id 是 String(32) 1900000109 微信支付分配的商户号
    private String mch_id;
    // 签名 sign 是 String(32) C380BEC2BFD727A4B6845133519F3AD6 签名，详见签名生成算法
    private String sign;
    // 随机字符串 是 String(32) 5K8264ILTKCH16CQ2502SI8ZNMTM67VS
    // 随机字符串，不长于32位。推荐随机数生成算法
    private String nonce_str;
    // 错误代码 err_code 否 String(32) SYSTEMERROR 详细参见第6节错误列表
    private String err_code;
    // 错误代码描述 err_code_des 否 String(128) 系统错误 结果信息描述
    private String err_code_des;

    // 设备号 device_info 否 String(32) 013467007045764 微信支付分配的终端设备号，
    private String device_info;
    // 用户标识 openid 是 String(128) wxd930ea5d5a258f4f 用户在商户appid下的唯一标识
    private String openid;
    // 是否关注公众账号 is_subscribe 是 String(1) Y 用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效
    private String is_subscribe;

    //现金退款金额
    private int cash_refund_fee=0;
    /*
     * 交易类型 trade_type 是 String(16) JSAPI,
	 * 调用接口提交的交易类型，取值如下：JSAPI，NATIVE，APP，MICROPAY，详细说明见参数规定
	 */

    private String trade_type;
    /*
     * 交易状态 trade_state 是 String(32) SUCCESS ; SUCCESS—支付成功 ,NOTPAY—未支付
     * CLOSED—已关闭, REVOKED—已撤销（刷卡支付）, USERPAYING--用户支付中 REFUND—转入退款,
     * PAYERROR--支付失败(其他原因，如银行返回失败);
     */
    private String trade_state;

    // 付款银行 bank_type 是 String(16) CMC 银行类型，采用字符串类型的银行标识
    private String bank_type;
    // 总金额 total_fee 是 Int 100 订单总金额，单位为分
    private String total_fee;

    /*
     * 货币种类 fee_type 否 String(8) CNY 货币类型，符合ISO
     * 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
     */
    private String fee_type;
    // 现金支付金额 cash_fee 是 Int 100 现金支付金额订单现金支付金额，详见支付金额
    private String cash_fee;
    /*
     * 现金支付货币类型 cash_fee_type 否 String(16) CNY 货币类型，符合ISO
     * 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
     */
    private String cash_fee_type;
    /*
	 * 代金券或立减优惠金额 coupon_fee 否 Int 100,
	 * “代金券或立减优惠”金额<=订单总金额，订单总金额-“代金券或立减优惠”金额=现金支付金额，详见支付金额
	 */

    private String coupon_fee;
    // 代金券或立减优惠使用数量 coupon_count 否 Int 1 代金券或立减优惠使用数量
    private String coupon_count;
    /*
     * 代金券或立减优惠批次ID coupon_batch_id_$n 否 String(20) 100 代金券或立减优惠批次ID
     * ,$n为下标，从0开始编号
     */
    private String coupon_batch_id_$n;
    private String coupon_batch_id;
    // 代金券或立减优惠ID coupon_id_$n 否 String(20) 10000 代金券或立减优惠ID, $n为下标，从0开始编号
    private String coupon_id_$n;
    private String coupon_id;
    // 单个代金券或立减优惠支付金额 coupon_fee_$n 否 Int 100 单个代金券或立减优惠支付金额, $n为下标，从0开始编号
    private String coupon_fee_$n;
    // 微信支付订单号 transaction_id 是 String(32) 1217752501201407033233368018 微信支付订单号
    private String transaction_id;
    // 商户订单号 out_trade_no 是 String(32) 1217752501201407033233368018
    private String out_trade_no;
    /*
     * 商户系统的订单号，与请求一致。 商家数据包 attach 否 String(128) 123456 商家数据包，原样返回
     */
    private String attach;

    /*
     * 支付完成时间 time_end 是 String(14) 20141030133525
     * 订单支付时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
     */
    private String time_end;

    // 交易状态描述 trade_state_desc 是 String(256) 支付失败，请重新下单支付 对当前查询订单状态的描述和下一步操作的指引
    private String trade_state_desc;

    // 退款笔数 refund_count 是 Int 1 退款记录数
    private Integer refund_count;
    // 商户退款单号 out_refund_no_$n 是 String(32) 1217752501201407033233368018 商户退款单号
    private String out_refund_no_$n;
    private String out_refund_no;
    // 微信退款单号 refund_id_$n 是 String(28) 1217752501201407033233368018 微信退款单号
    private String refund_id_$n;
    private String refund_id;
    // 退款渠道 refund_channel_$n 否 String(16) ORIGINAL—原路退款 ,BALANCE—退回到余额
    private String refund_channel_$n;
    private String refund_channel;
    // 退款金额 refund_fee_$n 是 Int 100 退款总金额,单位为分,可以做部分退款
    private Integer refund_fee_$n;
    private Integer refund_fee;

    /*
     * 代金券或立减优惠退款金额 coupon_refund_fee_$n 否 Int 100,
     * 代金券或立减优惠退款金额<=退款金额，退款金额-代金券或立减优惠退款金额为现金，说明详见代金券或立减优惠
     */
    private Integer coupon_refund_fee_$n;
    private Integer coupon_refund_fee;
    // 代金券或立减优惠使用数量 coupon_refund_count_$n 否 Int 1 代金券或立减优惠使用数量 ,$n为下标,从0开始编号
    private Integer coupon_refund_count_$n;
    private Integer coupon_refund_count;
    // 代金券或立减优惠批次ID coupon_refund_batch_id_$n_$m 否 String(20) 100 批次ID
    // ,$n为下标，$m为下标，从0开始编号
    private String coupon_refund_batch_id_$n_$m;
    private String coupon_refund_batch_id;
    // 代金券或立减优惠ID coupon_refund_id_$n_$m 否 String(20) 10000 代金券或立减优惠ID,
    // $n为下标，$m为下标，从0开始编号
    private String coupon_refund_id_$n_$m;
    private String coupon_refund_id;
    // 单个代金券或立减优惠支付金额 coupon_refund_fee_$n_$m 否 Int 100 单个代金券或立减优惠支付金额,
    // $n为下标，$m为下标，从0开始编号
    private Integer coupon_refund_fee_$n_$m;

    // 退款状态 refund_status_$n
    private String refund_status_$n;
    private String refund_status;

    // URL链接 short_url 是 String(64) weixin：//wxpay/s/XXXXXX 转换后的URL
    private String short_url;

    // 预支付交易会话标识 prepay_id 是 String(64) wx201410272009395522657a690389285100
    // 微信生成的预支付回话标识，用于后续接口调用中使用，该值有效期为2小时
    private String prepay_id;
    // 二维码链接 code_url 否 String(64) URl：weixin：//wxpay/s/An4baqw
    // trade_type为NATIVE是有返回，可将该参数值生成二维码展示出来进行扫码支付
    private String code_url;

    private String sub_mch_id;


    public String getCoupon_batch_id() {
        return coupon_batch_id;
    }

    public void setCoupon_batch_id(String coupon_batch_id) {
        this.coupon_batch_id = coupon_batch_id;
    }

    public String getCoupon_id() {
        return coupon_id;
    }

    public void setCoupon_id(String coupon_id) {
        this.coupon_id = coupon_id;
    }

    public Integer getCoupon_refund_fee() {
        return coupon_refund_fee;
    }

    public void setCoupon_refund_fee(Integer coupon_refund_fee) {
        this.coupon_refund_fee = coupon_refund_fee;
    }

    public Integer getCoupon_refund_count() {
        return coupon_refund_count;
    }

    public void setCoupon_refund_count(Integer coupon_refund_count) {
        this.coupon_refund_count = coupon_refund_count;
    }

    public String getCoupon_refund_batch_id() {
        return coupon_refund_batch_id;
    }

    public void setCoupon_refund_batch_id(String coupon_refund_batch_id) {
        this.coupon_refund_batch_id = coupon_refund_batch_id;
    }

    public String getCoupon_refund_id() {
        return coupon_refund_id;
    }

    public void setCoupon_refund_id(String coupon_refund_id) {
        this.coupon_refund_id = coupon_refund_id;
    }

    public String getRefund_status() {
        return refund_status;
    }

    public void setRefund_status(String refund_status) {
        this.refund_status = refund_status;
    }

    public String getRefund_channel() {
        return refund_channel;
    }

    public void setRefund_channel(String refund_channel) {
        this.refund_channel = refund_channel;
    }

    public Integer getRefund_fee() {
        return refund_fee;
    }

    public void setRefund_fee(Integer refund_fee) {
        this.refund_fee = refund_fee;
    }

    public String getOut_refund_no() {
        return out_refund_no;
    }

    public void setOut_refund_no(String out_refund_no) {
        this.out_refund_no = out_refund_no;
    }

    public String getRefund_id() {
        return refund_id;
    }

    public void setRefund_id(String refund_id) {
        this.refund_id = refund_id;
    }

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

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getErr_code() {
        return err_code;
    }

    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }

    public String getErr_code_des() {
        return err_code_des;
    }

    public void setErr_code_des(String err_code_des) {
        this.err_code_des = err_code_des;
    }

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getIs_subscribe() {
        return is_subscribe;
    }

    public void setIs_subscribe(String is_subscribe) {
        this.is_subscribe = is_subscribe;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getTrade_state() {
        return trade_state;
    }

    public void setTrade_state(String trade_state) {
        this.trade_state = trade_state;
    }

    public String getBank_type() {
        return bank_type;
    }

    public void setBank_type(String bank_type) {
        this.bank_type = bank_type;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public String getCash_fee() {
        return cash_fee;
    }

    public void setCash_fee(String cash_fee) {
        this.cash_fee = cash_fee;
    }

    public String getCash_fee_type() {
        return cash_fee_type;
    }

    public void setCash_fee_type(String cash_fee_type) {
        this.cash_fee_type = cash_fee_type;
    }

    public String getCoupon_fee() {
        return coupon_fee;
    }

    public void setCoupon_fee(String coupon_fee) {
        this.coupon_fee = coupon_fee;
    }

    public String getCoupon_count() {
        return coupon_count;
    }

    public void setCoupon_count(String coupon_count) {
        this.coupon_count = coupon_count;
    }

    public String getCoupon_batch_id_$n() {
        return coupon_batch_id_$n;
    }

    public void setCoupon_batch_id_$n(String coupon_batch_id_$n) {
        this.coupon_batch_id_$n = coupon_batch_id_$n;
    }

    public String getCoupon_id_$n() {
        return coupon_id_$n;
    }

    public void setCoupon_id_$n(String coupon_id_$n) {
        this.coupon_id_$n = coupon_id_$n;
    }

    public String getCoupon_fee_$n() {
        return coupon_fee_$n;
    }

    public void setCoupon_fee_$n(String coupon_fee_$n) {
        this.coupon_fee_$n = coupon_fee_$n;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }

    public String getTrade_state_desc() {
        return trade_state_desc;
    }

    public void setTrade_state_desc(String trade_state_desc) {
        this.trade_state_desc = trade_state_desc;
    }

    public Integer getRefund_count() {
        return refund_count;
    }

    public void setRefund_count(Integer refund_count) {
        this.refund_count = refund_count;
    }

    public String getOut_refund_no_$n() {
        return out_refund_no_$n;
    }

    public void setOut_refund_no_$n(String out_refund_no_$n) {
        this.out_refund_no_$n = out_refund_no_$n;
    }

    public String getRefund_id_$n() {
        return refund_id_$n;
    }

    public void setRefund_id_$n(String refund_id_$n) {
        this.refund_id_$n = refund_id_$n;
    }

    public String getRefund_channel_$n() {
        return refund_channel_$n;
    }

    public void setRefund_channel_$n(String refund_channel_$n) {
        this.refund_channel_$n = refund_channel_$n;
    }

    public Integer getRefund_fee_$n() {
        return refund_fee_$n;
    }

    public void setRefund_fee_$n(Integer refund_fee_$n) {
        this.refund_fee_$n = refund_fee_$n;
    }

    public Integer getCoupon_refund_fee_$n() {
        return coupon_refund_fee_$n;
    }

    public void setCoupon_refund_fee_$n(Integer coupon_refund_fee_$n) {
        this.coupon_refund_fee_$n = coupon_refund_fee_$n;
    }

    public Integer getCoupon_refund_count_$n() {
        return coupon_refund_count_$n;
    }

    public void setCoupon_refund_count_$n(Integer coupon_refund_count_$n) {
        this.coupon_refund_count_$n = coupon_refund_count_$n;
    }

    public String getCoupon_refund_batch_id_$n_$m() {
        return coupon_refund_batch_id_$n_$m;
    }

    public void setCoupon_refund_batch_id_$n_$m(
            String coupon_refund_batch_id_$n_$m) {
        this.coupon_refund_batch_id_$n_$m = coupon_refund_batch_id_$n_$m;
    }

    public String getCoupon_refund_id_$n_$m() {
        return coupon_refund_id_$n_$m;
    }

    public void setCoupon_refund_id_$n_$m(String coupon_refund_id_$n_$m) {
        this.coupon_refund_id_$n_$m = coupon_refund_id_$n_$m;
    }

    public Integer getCoupon_refund_fee_$n_$m() {
        return coupon_refund_fee_$n_$m;
    }

    public void setCoupon_refund_fee_$n_$m(Integer coupon_refund_fee_$n_$m) {
        this.coupon_refund_fee_$n_$m = coupon_refund_fee_$n_$m;
    }

    public String getRefund_status_$n() {
        return refund_status_$n;
    }

    public void setRefund_status_$n(String refund_status_$n) {
        this.refund_status_$n = refund_status_$n;
    }

    public String getShort_url() {
        return short_url;
    }

    public void setShort_url(String short_url) {
        this.short_url = short_url;
    }

    public String getPrepay_id() {
        return prepay_id;
    }

    public void setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id;
    }

    public String getCode_url() {
        return code_url;
    }

    public void setCode_url(String code_url) {
        this.code_url = code_url;
    }

    public String getSub_mch_id() {
        return sub_mch_id;
    }

    public void setSub_mch_id(String sub_mch_id) {
        this.sub_mch_id = sub_mch_id;
    }

    public boolean isSuccess() {
        if (this.getReturn_code().equals(WeChatConstKey.SUCCESS) && this.getResult_code().equals(WeChatConstKey.SUCCESS)) {
            success = true;
        }
        return success;
    }
}
