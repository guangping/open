package com.varela.mp.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by lance on 2016/3/30.
 */
@Controller
public class SysUserController extends AdminController {

    @RequestMapping(value = "/sys/user/list", method = RequestMethod.GET)
    public String list() {
        return "sys/user/list";
    }
}
