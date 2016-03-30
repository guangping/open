package com.varela.mp.view;

import com.varela.entity.SysRole;
import com.varela.mp.pojo.MPMsg;
import com.varela.mp.service.SysRoleService;
import com.varela.pojo.APIResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @ResponseBody
    @RequestMapping(value = "/sys/role/save", method = RequestMethod.POST)
    public APIResult save(SysRole sysRole) {
        APIResult apiResult = new APIResult();
        if (StringUtils.isBlank(sysRole.getName())) {
            apiResult.setMsg(MPMsg.PARAM_IS_EMPTY);
            return apiResult;
        }
        try {
            this.sysRoleService.save(sysRole);
            apiResult.setMsg(MPMsg.SUCCESS);
            apiResult.setData(sysRole.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return apiResult;
    }

    @ResponseBody
    @RequestMapping(value = "/sys/role/edit", method = RequestMethod.POST)
    public APIResult edit(SysRole sysRole) {
        APIResult apiResult = new APIResult();
        if (StringUtils.isBlank(sysRole.getName()) || null == sysRole.getId()) {
            apiResult.setMsg(MPMsg.PARAM_IS_EMPTY);
            return apiResult;
        }
        try {
            this.sysRoleService.updateById(sysRole);
            apiResult.setMsg(MPMsg.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return apiResult;
    }

}
