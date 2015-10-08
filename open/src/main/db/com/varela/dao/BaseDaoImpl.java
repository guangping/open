package com.varela.dao;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 *
 */
public abstract class BaseDaoImpl<T> extends SqlMapClientDaoSupport {

    private final int PAGE_SIZE = 20;

    @SuppressWarnings({"rawtypes", "unchecked"})
    protected PageInfo queryList(int pageIndex, int pageSize, String statement, Object arg) {
        if (pageIndex <= 0) {
            pageIndex = 1;
        }
        if (pageSize <= 1) {
            pageSize = PAGE_SIZE;
        }
        PageHelper.startPage(pageIndex, pageSize, true);
        List list = this.getSqlSession().selectList(statement, arg);
        PageInfo page = new PageInfo(list);
        return page;
    }
}
