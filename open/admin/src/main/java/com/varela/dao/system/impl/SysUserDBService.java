package com.varela.dao.system.impl;

import com.varela.dao.BaseDaoImpl;
import com.varela.dao.pojo.DataTables;
import com.varela.dao.pojo.PageDataTables;
import com.varela.entity.sys.SysUser;
import org.springframework.stereotype.Repository;

/**
 * Created by lance on 11/6/2015.
 */
@Repository("sysUserDBService")
public class SysUserDBService extends BaseDaoImpl<SysUser> {


    public PageDataTables<SysUser> query(DataTables dataTables, SysUser params) {
        return this.queryDataTables(dataTables, "SysUser.queryList", params);
    }


    public SysUser queryObject(SysUser sysUser) {
        return this.getSqlSession().selectOne("SysUser.queryList", sysUser);
    }


}
