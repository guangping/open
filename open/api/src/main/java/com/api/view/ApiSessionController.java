package com.api.view;

import com.varela.enumerate.APIMsg;
import com.varela.pojo.APIResult;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

/**
 * Created by lance on 12/8/2015.
 */
@Controller
@Scope("prototype")
public class ApiSessionController extends ApiController {

    /**
     * 获取session
     */
    @ResponseBody
    @RequestMapping(value = "/session/get", method = {RequestMethod.GET, RequestMethod.POST})
    public Object getSession() {
        APIResult apiResult = new APIResult();
        apiResult.setResult(UUID.randomUUID());
        apiResult.setMsg(APIMsg.Success);
        return apiResult;
    }


}
