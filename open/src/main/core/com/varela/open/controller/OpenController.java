package com.varela.open.controller;

import com.varela.open.ServiceRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by guangping.lance on 07/20/2015.
 */
@Scope("prototype")
@Controller
@RequestMapping("/gateway")
public class OpenController {

    @Autowired
    private ServiceRouter serviceRouter;


    @RequestMapping(value = "", method = RequestMethod.POST)
    public void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        serviceRouter.service(req, resp);
    }

}
