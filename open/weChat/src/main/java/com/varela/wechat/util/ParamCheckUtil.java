package com.varela.wechat.util;

import com.varela.wechat.pojo.Msg;
import com.varela.wechat.pojo.PayResult;
import com.varela.wechat.pojo.WeChatPay;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Date;

/**
 *
 *
 */
public class ParamCheckUtil {

    /**
     * 统一下单检测
     */
    public static void checkUnifiedOrder(PayResult result, WeChatPay order)
            throws ParseException {
        baseCheck(result, order);// 基础校验
        if (result.getErrorCode() != 0) {
            return;
        }
        checklength(result, order.getBody(), 32, Msg.BodyLengthOver);
        if (result.getErrorCode() != 0) {
            return;
        }
        checklength(result, order.getDetail(), 8192, Msg.DetailLengthOver);
        if (result.getErrorCode() != 0) {
            return;
        }
        checklength(result, order.getAttach(), 127, Msg.AttachLengthOver);

        checkNull(result, order.getOut_trade_no(), Msg.OutTradeNoIsNull);
        if (result.getErrorCode() != 0) {
            return;
        }
        checklength(result, order.getOut_trade_no(), 32, Msg.OutTradeLengthOver);
        if (result.getErrorCode() != 0) {
            return;
        }
        checklength(result, order.getFee_type(), 16, Msg.FeeTypeLengthOver);
        if (result.getErrorCode() != 0) {
            return;
        }
        if (order.getTotal_fee() != null) {
            checkNumber(result, order.getTotal_fee().toString(),
                    Msg.TotalfeeIsIllegal);
            if (result.getErrorCode() != 0) {
                return;
            }
        }
        checkNull(result, order.getSpbill_create_ip(), Msg.SpbillCreateIpIsNUll);
        if (result.getErrorCode() != 0) {
            return;
        }
        checklength(result, order.getSpbill_create_ip(), 16,
                Msg.SpbillCreateIpLengthOver);
        if (result.getErrorCode() != 0) {
            return;
        }
        checkTimePattern(result, order.getTime_start(), Msg.TimePatternError);
        if (result.getErrorCode() != 0) {
            return;
        }
        checkTimePattern(result, order.getTime_expire(), Msg.TimePatternError);
        if (result.getErrorCode() != 0) {
            return;
        }
        checkMistiming(result, order.getTime_start(), order.getTime_expire(),
                Msg.MistimingLess5M);
        if (result.getErrorCode() != 0) {
            return;
        }
        checkNull(result, order.getNotify_url(), Msg.NotifyUrlIsNull);
        if (result.getErrorCode() != 0) {
            return;
        }
        checklength(result, order.getNotify_url(), 256, Msg.NotifyUrlLengthOver);
        if (result.getErrorCode() != 0) {
            return;
        }
        checkNull(result, order.getTrade_type(), Msg.TradeTypeIsNull);
        if (result.getErrorCode() != 0) {
            return;
        }
        checklength(result, order.getTrade_type(), 16, Msg.TradeTypeLengthOver);
    }

    /**
     * 校验OrderQuery参数
     *
     * @param result
     * @param
     */
    public static void checkOrderQuery(PayResult result, WeChatPay order) {
        baseCheck(result, order);// 基础校验
        if (result.getErrorCode() != 0) {
            return;
        }
        // 如果transaction_id 不为空校验其长度
        if (StringUtils.isNotBlank(order.getTransaction_id())) {
            checklength(result, order.getTransaction_id(), 32,
                    Msg.TransactionidLengthOver);
            if (result.getErrorCode() != 0) {
                return;
            }
        } else {// 如果transaction_id为空,OutTradeNo必须不能为空,校验其长度
            checkNull(result, order.getOut_trade_no(), Msg.OutTradeNoIsNull);
            if (result.getErrorCode() != 0) {
                return;
            }
            checklength(result, order.getOut_trade_no(), 32,
                    Msg.OutTradeLengthOver);
            if (result.getErrorCode() != 0) {
                return;
            }
        }
    }

    /**
     * 校验关闭订单
     *
     * @param result
     * @param order
     */
    public static void checkOrderClose(PayResult result, WeChatPay order) {
        baseCheck(result, order);// 基础校验
        if (result.getErrorCode() != 0) {
            return;
        }
    }

    /**
     * 校验申请退款
     *
     * @param order
     */
    public static void checkOrderRefund(PayResult result, WeChatPay order) {
        baseCheck(result, order);// 基础校验
        if (result.getErrorCode() != 0) {
            return;
        }

		/*
         * 商户订单号 out_trade_no 是 String(32) 1217752501201407033233368018,
		 * 商户系统内部的订单号
		 * ,transaction_id、out_trade_no二选一，如果同时存在优先级：transaction_id>out_trade_no
		 */
        if (StringUtils.isBlank(order.getTransaction_id())) {
            if (result.getErrorCode() != 0) {
                return;
            }
        }
		/*
		 * 商户退款单号 out_refund_no 是 String(32) 1217752501201407033233368018,
		 * 商户系统内部的退款单号，商户系统内部唯一，同一退款单号多次请求只退一笔
		 */
        checkOutRefundNo(result, order.getOut_refund_no());
        if (result.getErrorCode() != 0) {
            return;
        }
        // 总金额 total_fee 是 Int 100 订单总金额，单位为分，只能为整数，详见支付金额
        if (order.getTotal_fee() != null) {
            checkNumber(result, order.getTotal_fee().toString(),
                    Msg.TotalfeeIsIllegal);
            if (result.getErrorCode() != 0) {
                return;
            }
        }
        // 退款金额 refund_fee 是 Int 100 退款总金额，订单总金额，单位为分，只能为整数，详见支付金额
        if (order.getRefund_fee() != null) {
            checkNumber(result, order.getRefund_fee().toString(),
                    Msg.RefundfeeIsIllegal);
            if (result.getErrorCode() != 0) {
                return;
            }
        }
        // 操作员 op_user_id 是 String(32) 1900000109 操作员帐号, 默认为商户号
        checkOpUserId(result, order.getOp_user_id());
    }

    /**
     * 校验查询退款
     *
     * @param result
     * @param order
     */
    public static void checkQueryRefundModel(PayResult result, WeChatPay order) {
        baseCheck(result, order);// 基础校验
        if (result.getErrorCode() != 0) {
            return;
        }
        checklength(result, order.getDevice_info(), 32,
                Msg.DeviceInfoLengthOver);
        if (result.getErrorCode() != 0) {
            return;
        }
        checklength(result, order.getTransaction_id(), 28,
                Msg.TransactionidLengthOver);
        if (result.getErrorCode() != 0) {
            return;
        }
        checklength(result, order.getOut_trade_no(), 32, Msg.OutTradeLengthOver);
        if (result.getErrorCode() != 0) {
            return;
        }
        checklength(result, order.getOut_refund_no(), 32,
                Msg.OutRefundNoLengthOver);
        if (result.getErrorCode() != 0) {
            return;
        }

		/*
		 * 微信退款单号 refund_id 否 String(28) 1217752501201407033233368018,
		 * refund_id、out_refund_no、out_trade_no、transaction_id四个参数必填一个，
		 * 如果同时存在优先级为：refund_id>out_refund_no>transaction_id>out_trade_no
		 */
        if (StringUtils.isBlank(order.getRefund_id())
                && StringUtils.isBlank(order.getOut_refund_no())
                && StringUtils.isBlank(order.getOut_trade_no())
                && StringUtils.isBlank(order.getTransaction_id())) {
            result.setMsg(Msg.AllRefundIdIsNull);
        }
    }

    /**
     * 校验转换短连接
     *
     * @param result
     * @param model
     */
    public static void check2ShortUrlModel(PayResult result, WeChatPay model) {
        baseCheck(result, model);
        if (result.getErrorCode() != 0) {
            return;
        }
        checkLongUrl(result, model.getLong_url());
    }

    /**
     * 校验out_refund_no
     *
     * @param result
     * @param out_refund_no
     */
    private static void checkOutRefundNo(PayResult result, String out_refund_no) {
        checkNull(result, out_refund_no, Msg.OutRefundNoIsNull);
        checklength(result, out_refund_no, 32, Msg.OutRefundNoLengthOver);
    }

    /**
     * 校验out_trade_no
     *
     * @param result
     * @param out_trade_no
     */
    private static void checkOutTradeNo(PayResult result, String out_trade_no) {
        checkNull(result, out_trade_no, Msg.OutTradeNoIsNull);
        checklength(result, out_trade_no, 32, Msg.OutTradeLengthOver);
    }

    /**
     * 校验transaction_id
     *
     * @param result
     * @param transaction_id
     */
    private static void checkTransactionId(PayResult result,
                                           String transaction_id) {
        checkNull(result, transaction_id, Msg.TransactionidIsNull);
        checklength(result, transaction_id, 28, Msg.TransactionidLengthOver);
    }

    /**
     * 校验device_info
     *
     * @param result
     * @param device_info
     */
    private static void checkDeviceInfo(PayResult result, String device_info) {
        checkNull(result, device_info, Msg.DeviceInfoIsNull);
        checklength(result, device_info, 32, Msg.DeviceInfoLengthOver);
    }

    /**
     * 校验op_user_id
     *
     * @param result
     * @param op_user_id
     */
    private static void checkOpUserId(PayResult result, String op_user_id) {
        checkNull(result, op_user_id, Msg.OpUserIdIsNull);
        checklength(result, op_user_id, 32, Msg.OpUserIdLengthOver);
    }

    /**
     * 校验nonce_str
     *
     * @param result
     * @param nonce_str
     */
    private static void checkNonceStr(PayResult result, String nonce_str) {
        checkNull(result, nonce_str, Msg.NoncestrIsNull);
        checklength(result, nonce_str, 32, Msg.NoncestrLengthOver);
    }

    /**
     * 校验appid
     *
     * @param result
     * @param appid
     */
    private static void checkAppid(PayResult result, String appid) {
        checkNull(result, appid, Msg.AppidIsNull);
        checklength(result, appid, 32, Msg.AppidLengthOver);
    }

    /**
     * 校验mch_id
     *
     * @param result
     * @param mch_id
     */
    private static void checkMchId(PayResult result, String mch_id) {
        checkNull(result, mch_id, Msg.MchidIsNull);
        checklength(result, mch_id, 32, Msg.MchidLengthOver);
    }

    /**
     * 校验sign
     *
     * @param result
     * @param sign
     */
    private static void checkSign(PayResult result, String sign) {
        checkNull(result, sign, Msg.SignIsNull);
        checklength(result, sign, 32, Msg.SignLengthOver);
    }

    /**
     * 校验string长度
     *
     * @param result
     * @param str
     * @param length
     * @param msg
     */
    public static void checklength(PayResult result, String str,
                                   Integer length, Msg msg) {
        if (StringUtils.isNotBlank(str)) {
            if (str.length() > length)
                result.setMsg(msg);
        }
    }

    /**
     * 校验字符串不为空
     *
     * @param result
     * @param str
     * @param msg
     */
    public static void checkNull(PayResult result, String str, Msg msg) {
        if (StringUtils.isBlank(str)) {
            result.setMsg(msg);
        }
    }

    /**
     * 校验数字类型(Integer)是否合法
     *
     * @param result
     * @param total_fee
     * @param msg
     */
    private static void checkNumber(PayResult result, String total_fee, Msg msg) {
        try {
            Integer.valueOf(total_fee);
        } catch (Exception e) {
            result.setMsg(msg);
        }
    }

    /**
     * 校验时间格式是否合法
     *
     * @param result
     * @param time
     * @param msg
     */
    private static void checkTimePattern(PayResult result, String time, Msg msg) {
        if (StringUtils.isNotBlank(time)) {
            try {
                DateUtils.parseDate(time, "yyyyMMddHHmmss");
            } catch (Exception e) {
                result.setMsg(msg);
            }
        }
    }

    /**
     * 校验订单有效时间
     *
     * @param result
     * @param time_start
     * @param time_expire
     * @param msg
     * @throws ParseException
     */
    private static void checkMistiming(PayResult result, String time_start,
                                       String time_expire, Msg msg) throws ParseException {
        if (StringUtils.isNotBlank(time_start)
                && StringUtils.isNotBlank(time_expire)) {
            Date start = DateUtils.parseDate(time_start, "yyyyMMddHHmmss");
            Date end = DateUtils.parseDate(time_expire, "yyyyMMddHHmmss");
            if (end.getTime() - start.getTime() < 300000) {// 小于5分钟
                result.setMsg(msg);
            }
        }
    }

    /**
     * 校验长连接
     *
     * @param result
     * @param long_url
     */
    private static void checkLongUrl(PayResult result, String long_url) {
        checkNull(result, long_url, Msg.LongUrlIsNull);
        checklength(result, long_url, 512, Msg.LongUrlLengthOver);
    }

    /**
     * <p>
     * 基础校验
     * </p>
     * <p>
     * appid, mch_id, nonce_str,sign
     * </p>
     *
     * @param result
     * @param order
     */
    private static void baseCheck(PayResult result, WeChatPay order) {
        // 校验 appid
        checkAppid(result, order.getAppid());
        if (result.getErrorCode() != 0) {
            return;
        }
        // 校验 mch_id
        checkMchId(result, order.getMch_id());
        if (result.getErrorCode() != 0) {
            return;
        }
        // 校验 nonce_str
        checkNonceStr(result, order.getNonce_str());
        if (result.getErrorCode() != 0) {
            return;
        }
        // 签名 sign
        checkSign(result, order.getSign());
    }
}
