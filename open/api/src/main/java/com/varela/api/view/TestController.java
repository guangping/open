package com.varela.api.view;

import com.varela.api.entity.API;
import com.varela.api.service.APIService;
import com.varela.enumerate.Msg;
import com.varela.pojo.APIResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lance on 2016/3/15.
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private APIService apiService;

    @ResponseBody
    @RequestMapping(value = "/save/api", method = RequestMethod.POST)
    public APIResult saveApi(API api) {
        APIResult apiResult = new APIResult();

        try {
            this.apiService.save(api);
            apiResult.setData(api.getId());
            apiResult.setMsg(Msg.SUCCESS);
        } catch (Exception e) {
            apiResult.setMsg(Msg.ERROR);
            e.printStackTrace();
        }
        return apiResult;
    }

}
