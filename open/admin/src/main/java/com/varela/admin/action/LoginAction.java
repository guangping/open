package com.varela.admin.action;

import com.varela.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by lance on 11/6/2015.
 */
@Controller
public class LoginAction extends BaseController {


    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login() {
        return null;
    }


    @RequestMapping(value = "/logOut", method = RequestMethod.GET)
    public String logOut() {
        this.session.invalidate();
        return "redirect:/";
    }
}
