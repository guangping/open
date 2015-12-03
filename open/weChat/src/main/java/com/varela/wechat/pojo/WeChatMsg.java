package com.varela.wechat.pojo;

public enum WeChatMsg {

	Success(0, "成功"), 
	CodeException(1, "代码异常"), 
	ParameterError(2, "参数错误"),
	
	AppidIsNull(1000, "appid为空"),
	AppidLengthOver(1019, "appid长度大于32"),
	MchidIsNull(1001, "mch_id为空"),
	MchidLengthOver(1020, "mch_id长度大于32"),
	NoncestrIsNull(1002, "nonce_str为空"),
	NoncestrLengthOver(1003, "nonce_str长度大于32"),
	SignIsNull(1004, "sign为空"),
	SignLengthOver(1005, "sign长度大于32"),
	BodyIsNull(1006, "body为空"),
	BodyLengthOver(1007, "body长度大于32"),
	DetailIsNull(1008, "detail为空"),
	DetailLengthOver(1009, "detail长度大于8192"),
	TotalfeeIsIllegal(1010, "total_fee错误"),
	SpbillCreateIpIsNUll(1011, "spbill_create_ip为空"),
	SpbillCreateIpLengthOver(1012, "spbill_create_ip长度大于16"),
	NotifyUrlIsNull(1013, "notify_url为空"),
	NotifyUrlLengthOver(1014, "notify_url长度大于256"),
	OutTradeNoIsNull(1015, "out_trade_no为空"),
	OutTradeLengthOver(1016, "out_trade_no长度大于32"),
	TimePatternError(1017, "日期格式错误"), 
	MistimingLess5M(1018, "最短失效时间间隔必须小于5分钟"),
	TransactionidIsNull(1021, "transaction_id为空"),
	TransactionidLengthOver(1022, "transaction_id长度大于32位"),
	RefundfeeIsIllegal(1023, "refund_fee不合法"), 
	OpUserIdIsNull(1024, "op_user_id为空"),
	OpUserIdLengthOver(1025, "op_user_id长度大于32位"),
	DeviceInfoIsNull(1026, "device_info为空"),
	DeviceInfoLengthOver(1027, "device_info长度大于32位"),
	OutRefundNoIsNull(1028, "out_refund_no为空"),
	OutRefundNoLengthOver(1029, "out_refund_no长度大于32位"), 
	AllRefundIdIsNull(1030, "退款ID为空"),
	LongUrlIsNull(1031, "long_url为空"),
	LongUrlLengthOver(1032, "long_url长度大于512位"),
	FeeTypeLengthOver(1033, "long_url长度大于16位"),
	AttachIsNull(1034, "attach为空"),
	AttachLengthOver(1035, "attach长度大于127"),
	TradeTypeIsNull(1036, "trade_type为空"),
	TradeTypeLengthOver(1037, "trade_type长度大于16");

	WeChatMsg(int errorCode, String msg) {
		this.errorCode = errorCode;
		this.msg = msg;
	}

	private final int errorCode;

	private final String msg;

	public int getErrorCode() {
		return errorCode;
	}

	public String getMsg() {
		return msg;
	}
}
