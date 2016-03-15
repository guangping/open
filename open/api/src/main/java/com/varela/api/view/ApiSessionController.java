package com.varela.api.view;

import com.varela.api.pojo.User;
import com.varela.api.utils.HttpMethodConst;
import com.varela.enumerate.Msg;
import com.varela.pojo.APIResult;
import com.varela.utils.ValidatorResult;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
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
    @ApiOperation(value = "获取session", httpMethod = HttpMethodConst.GET, notes = "获取session")
    public Object getSession() {
        APIResult apiResult = new APIResult();
        apiResult.setData(UUID.randomUUID());
        apiResult.setMsg(Msg.SUCCESS);
        return apiResult;
    }


    @ResponseBody
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ApiOperation(value = "用户", httpMethod = HttpMethodConst.POST, notes = "用户", response = User.class)
    public Object user(@Validated User user, BindingResult bindingResult) {
        APIResult apiResult = ValidatorResult.handle(bindingResult);
        if (!apiResult.isSuccess()) {
            return apiResult;
        }
        apiResult = new APIResult();

        user.setEmail("test@163.com");
        user.setMobile("11111111111");
        user.setCreateTime(new Date());
        apiResult.setMsg(Msg.SUCCESS);
        apiResult.setData(user);
        return apiResult;
    }

}
