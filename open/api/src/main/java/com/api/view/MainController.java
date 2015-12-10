package com.api.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by lance on 12/10/2015.
 */
@Controller
public class MainController {

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String execute(){
        return "index";
    }
}
