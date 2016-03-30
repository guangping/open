package com.varela.mp.view;

import com.varela.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lance on 2016/3/22.
 */
@Controller
public class DefaultController extends BaseController {

    /**
     * 默认页
     * */
    @RequestMapping(value = "/")
    public String execute() {
        return "index";
    }

}
