package com.varela.mp.service;

import com.varela.dao.SysRoleDao;
import com.varela.entity.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lance on 2016/3/30.
 */
@Service
public class SysRoleService {

    @Autowired
    private SysRoleDao sysRoleDao;

    @Transactional
    public void save(SysRole sysRole){
        this.sysRoleDao.insert(sysRole);
    }
}
