package com.api.view;

import com.api.pojo.User;
import com.varela.enumerate.Msg;
import com.varela.pojo.APIResult;
import com.varela.utils.ValidatorResult;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
    @ApiOperation(value = "获取session",httpMethod = "GET",)
    public Object getSession() {
        APIResult apiResult = new APIResult();
        apiResult.setResult(UUID.randomUUID());
        apiResult.setMsg(Msg.Success);
        return apiResult;
    }


    @ResponseBody
    @RequestMapping(value = "/user", method = {RequestMethod.GET, RequestMethod.POST})
    public Object user(@Validated User user, BindingResult bindingResult) {
        APIResult apiResult = ValidatorResult.handle(bindingResult);
      
        return apiResult;
    }

}
