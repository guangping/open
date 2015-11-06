package com.varela.service.sys;

import com.varela.dao.pojo.DataTables;
import com.varela.dao.pojo.PageDataTables;
import com.varela.dao.system.impl.SysUserDBService;
import com.varela.entity.sys.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * Created by lance on 11/6/2015.
 */
@Service
public class SysUserService {
    @Autowired
    private SysUserDBService sysUserDBService;

    private SysUser queryById(Serializable id) {
        SysUser param = new SysUser();
        param.setId(id);
        return this.sysUserDBService.queryObject(param);
    }

    public PageDataTables<SysUser> query(DataTables dataTables, SysUser params) {
        return this.sysUserDBService.query(dataTables, params);
    }

}
