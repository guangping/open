package com.varela.wechat.view;

import com.varela.controller.BaseController;
import com.varela.pojo.APIResult;
import com.varela.wechat.service.WeChatService;
import com.varela.wechat.util.WeChatConstKey;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.TreeMap;

/**
 * Created by lance on 12/8/2015.
 */

@Controller
public class WeChatController extends BaseController {

    @Autowired
    private WeChatService weChatService;


    /**
     * 授权返回地址
     */
    @RequestMapping(value = "/wechat/callback", method = RequestMethod.GET)
    public String wechatAuthCallBack(String state, String code)throws Exception {
        if (StringUtils.isBlank(code) || StringUtils.isBlank(state)) {
            request.setAttribute("msg", "授权回调出错");
            return null;
        }
        if (code.equals(WeChatConstKey.AUTHDENY)) {
            request.setAttribute("msg", "授权未通过");
            return null;
        }

        //获取openId
        APIResult openResult=this.weChatService.getAuthAccessToken(code);

        //获取预支付prepayId  调用统一下单接口
        String prepayId=null;

        //生成支付参数
        TreeMap<String,String> paySignParam=this.weChatService.getPaySignParam(prepayId);
        String paySign=this.weChatService.encrpty(paySignParam);

        this.request.setAttribute("signType", WeChatConstKey.MD5);
        this.request.setAttribute("paySign", paySign);
        this.request.setAttribute("paySignParam", paySignParam);


        return null;
    }
    /**
     * 页面支付调用
     * <script type="text/javascript" charset="UTF-8" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
     * <script type="text/javascript">
     wx.config({
     debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
     });
     wx.ready(function () {
     WeixinJSBridge.invoke('getBrandWCPayRequest', {
     "appId": '${paySignParam.appId}',
     "timeStamp": '${paySignParam.timeStamp}',
     "nonceStr": '${paySignParam.nonceStr}',
     "package": '${paySignParam.package}',
     "signType": '${signType}',
     "paySign": '${paySign}'
     }, function (res) {
     if (res.err_msg == 'get_brand_wcpay_request:ok') {
     // alert('支付成功!');
     window.location.href = '${ctx}/order/success?no=${orderNo}';
     } else {
     //alert('支付失败!');
     window.location.href = '${ctx}/order/error';
     }
     }
     );
     });
     wx.error(function (res) {
     //alert("error:" + res);
     });
     </script>
     *
     * */


}
