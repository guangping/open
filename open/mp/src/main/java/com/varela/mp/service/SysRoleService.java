package com.varela.mp.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.varela.dao.SysRoleDao;
import com.varela.entity.SysRole;
import com.varela.pojo.Page;
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


    public Page<SysRole> queryList(SysRole sysRole, int pageIndex, int pageSize) {
        PageBounds pageBounds = new PageBounds();
        pageBounds.setPage(pageIndex);
        pageBounds.setLimit(pageSize);
        pageBounds.setContainsTotalCount(true);
        PageList<SysRole> pageList = this.sysRoleDao.queryList(sysRole, pageBounds);

        Page page = new Page();
        page.setCurrPage(pageIndex);
        page.setData(pageList);
        page.setPageSize(pageSize);
        page.setTotalCount(pageList.getPaginator().getTotalCount());
        page.setTotalPage(pageList.getPaginator().getTotalPages());

        return page;
    }

    @Transactional
    public void save(SysRole sysRole){
        this.sysRoleDao.insert(sysRole);
    }

    @Transactional
    public int updateById(SysRole sysRole){
        return this.sysRoleDao.updateByPrimaryKeySelective(sysRole);
    }
}
