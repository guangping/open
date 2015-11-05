jQuery.validator.addMethod("mobile", function (value, element) {
    var tel = /^(13[0-9]|14[0-9]|15[0-9]|18[0-9]|17[0-9])\d{8}$/;
    return this.optional(element) || (tel.test(value));
}, "请正确填写您的手机号码.");

jQuery.validator.addMethod("china1-15", function (value, element) {
    var tel =  /^[\u4E00-\u9FA5\uf900-\ufa2d]{1,15}$/;
    return this.optional(element) || (tel.test(value));
}, "请输入1-15个中文字符.");

jQuery.validator.addMethod("checkCardNoET",function(value,element){
	     var tel =/(^\d{15}$)|(^\d{17}([0-9]|X)$)/;
	     return this.optional(element) ||(tel.test(value)) ;
},"请输入正确的身份证号!");

jQuery.validator.addMethod("checkCardNoFF",function(value,element){
    
		var tel = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/;
	    return this.optional(element) ||(tel.test(value)) ;
},"请输入正确的身份证号!");

jQuery.validator.addMethod("checkPwd",function(value,element){
	
	var tel = /^\s*[.A-Za-z0-9_-]{5,9}\s*$/;
	return this.optional(element) ||(tel.test(value)) ;
},"	密码长度不能小于5,不得超过9!");

jQuery.validator.addMethod("checkBankCardID",function(value,element){
	
	var tel = /^\d{19}$/g;
	return this.optional(element) ||(tel.test(value)) ;
},"	银行卡号格式不正确!");

jQuery.validator.addMethod("checkNumber",function(value,element){
	
	var tel =/^[0-9]*$/;//满足数字
	return this.optional(element) ||(tel.test(value)) ;
},"	请输入合法的数字!");

jQuery.validator.addMethod("checkNumberZ_N",function(value,element){
	
    var tel =/^\d{1,9}$/ ;///^\d+(?=\.{0,1}\d+$|$)/
	return this.optional(element) ||(tel.test(value)) ;
},"	请输入有效的数字!");

jQuery.validator.addMethod("checkInterger",function(value,element){

	var tel =/^\d+(?=\.{0,1}\d+$|$)/;//正数
	return this.optional(element) ||(tel.test(value)) ;
},"	请输入合法数字！");

jQuery.validator.addMethod("checkEasyURL",function(value,element){
	
	var tel =/^http:\/\/(?:[\w-\.]{0,255})(?:(?:\/?[^\s]{0,255}){0,255})/g;
	return this.optional(element) ||(tel.test(value)) ;
},"	请输入合法URL！如:http://image.baidu.com");

jQuery.validator.addMethod("checkEasyCHAR",function(value,element){
	
	var tel =/^[A-Z]{1,4}$/;
	return this.optional(element) ||(tel.test(value)) ;
},"	请输入1-4个合法大写英文字母！");

jQuery.validator.addMethod("checkTellPhone",function(value,element){
	
	var tel =/^0\d{2,3}-?\d{7,8}$/;
	return this.optional(element) ||(tel.test(value)) ;
},"	请输入合法的电话号码！");

jQuery.validator.addMethod("checkIsMoney",function(value,element){
	
	var tel =  /^\d+(?=\.{0,1}\d+$|$)/ ; 
	return this.optional(element) ||(tel.test(value)) ;
},"	请输入合法的金额！");

jQuery.validator.addMethod("checkIsMaxMoney",function(value,element){
	//保留小数点2位,最多5位整数。
	var tel = /^([1-9][\d]{0,4}|0)(\.[\d]{1,2})?$/ ; 
	return this.optional(element) ||(tel.test(value)) ;
},"	请输入合法的金额!最多5位！");








