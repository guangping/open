package com.varela.wechat.pojo;

import java.io.Serializable;

/**
 * Created by lance on 11/12/2015.
 */
public class UnifiedOrderResponse implements Serializable {

    /**
     * 二维码链接
     */
    private String codeUrl;

    /**
     * 交易类型
     */
    private String tradeType;

    /**
     * 预支付交易会话标识
     */
    private String prepayId;

    /**
     * 错误编码
     */
    private String errCode;

    /**
     * 错误描述
     */
    private String errCodeDes;

    private boolean success=false;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrCodeDes() {
        return errCodeDes;
    }

    public void setErrCodeDes(String errCodeDes) {
        this.errCodeDes = errCodeDes;
    }
}
