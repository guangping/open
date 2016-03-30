package com.varela.mp.view;

import com.varela.entity.SysRole;
import com.varela.mp.pojo.MPMsg;
import com.varela.mp.service.SysRoleService;
import com.varela.pojo.APIResult;
import com.varela.utils.ValidatorResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by lance on 2016/3/30.
 */
@Controller
public class SysRoleController extends AdminController {

    @Autowired
    private SysRoleService sysRoleService;

    @RequestMapping(value = "/sys/role/list", method = RequestMethod.GET)
    public String list() {
        return "sys/role/list";
    }

    @RequestMapping(value = "/sys/role/save", method = RequestMethod.POST)
    public APIResult save(@Valid SysRole sysRole, BindingResult bindingResult) {
        APIResult apiResult = ValidatorResult.handle(bindingResult);
        if (!apiResult.isSuccess()) {
            return apiResult;
        }
        apiResult = new APIResult();
        try {
            this.sysRoleService.save(sysRole);
            apiResult.setMsg(MPMsg.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return apiResult;
    }

    @RequestMapping(value = "/sys/role/edit", method = RequestMethod.POST)
    public void edit(@Valid SysRole sysRole, BindingResult bindingResult) {

    }

}
