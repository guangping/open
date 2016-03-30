package com.varela.mp.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.varela.dao.SysUserDao;
import com.varela.entity.SysUser;
import com.varela.pojo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lance on 2016/3/23.
 */
@Service
public class SysUserService {

    @Autowired
    private SysUserDao sysUserDao;

    public Page<SysUser> queryList(SysUser sysUser, int pageIndex, int pageSize) {
        PageBounds pageBounds = new PageBounds();
        pageBounds.setPage(pageIndex);
        pageBounds.setLimit(pageSize);
        pageBounds.setContainsTotalCount(true);
        PageList<SysUser> pageList = this.sysUserDao.queryList(sysUser, pageBounds);

        Page page = new Page();
        page.setCurrPage(pageIndex);
        page.setData(pageList);
        page.setPageSize(pageSize);
        page.setTotalCount(pageList.getPaginator().getTotalCount());
        page.setTotalPage(pageList.getPaginator().getTotalPages());

        return page;
    }

    @Transactional
    public int save(SysUser sysUser) {
        return this.sysUserDao.insert(sysUser);
    }

    @Transactional
    public int updateById(SysUser sysUser) {
        return this.sysUserDao.updateByPrimaryKeySelective(sysUser);
    }

    /**
     * 根据用户名 手机 email 查询用户信息
     */
    public SysUser queryUser(String userName, String email, String mobile) {
        SysUser sysUser = new SysUser();
        sysUser.setUserName(userName);
        sysUser.setEmail(email);
        sysUser.setPhone(mobile);
        return this.sysUserDao.queryUser(sysUser);
    }

}
