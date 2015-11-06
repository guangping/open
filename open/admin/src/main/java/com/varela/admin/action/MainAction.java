package com.varela.admin.action;

import com.varela.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by lance on 11/6/2015.
 */
@Controller
public class MainAction extends BaseController {

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String execute() {
        return "index";
    }


    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public String test1() {
        return "test1";
    }

    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public String test2() {
        return "test2";
    }

}
