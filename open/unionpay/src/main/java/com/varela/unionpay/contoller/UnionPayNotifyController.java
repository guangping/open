package com.varela.unionpay.contoller;

import com.alibaba.fastjson.JSONObject;
import com.varela.controller.BaseController;
import com.varela.unionpay.service.UpmpService;
import com.varela.utils.WebUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * Created by lance on 10/28/2015.
 */
@Controller
@Scope("prototype")
public class UnionPayNotifyController extends BaseController {

    @RequestMapping(value = "", method = {RequestMethod.POST,
            RequestMethod.GET})
    public void execute() {
        Map<String, String> params = WebUtils.getParams(request);

        logger.info("银联回调参数:{}", JSONObject.toJSONString(params));

        if (!UpmpService.verifySignature(params)) {
            logger.info("签名验证失败!");
        }

        Map<String, String> reqReserved = JSONObject.parseObject(params.get("reqReserved"), Map.class);
        //额外参数
        String extraCommonParam = reqReserved.get("extra_common_param");
        // 交易状态
        String transStatus = request.getParameter("transStatus");
        String qn = request.getParameter("qn");


        //充值
        String orderId = reqReserved.get("orderId");
        String userId = reqReserved.get("userId");
        String respCode = request.getParameter("respCode");// 响应码
        String respMsg = request.getParameter("respMsg");// 响应信息
        if ("00".equals(transStatus)) {
            logger.info("交易处理成功");
        }

    }
}
