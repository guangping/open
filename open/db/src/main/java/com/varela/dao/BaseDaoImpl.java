package com.varela.dao;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.varela.dao.pojo.DataTables;
import com.varela.dao.pojo.PageDataTables;

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

    /**
     * bootstrap datatables 数据格式
     * @param dts 分页参数
     * @param statement mybatis
     * @param arg 查询条件
     */
    protected PageDataTables<T> queryDataTables(DataTables dts, String statement, Object arg) {
        int sEcho = dts.getsEcho(), iDisplayStart = dts.getiDisplayStart(), iDisplayLength = dts.getiDisplayLength();

        PageDataTables dataTables = new PageDataTables();
        dataTables.setsEcho(sEcho);
        dataTables.setiDisplayStart(iDisplayStart);
        dataTables.setiDisplayLength(iDisplayLength);
        iDisplayStart += 1;
        int pageIndex = (iDisplayStart % iDisplayLength) == 0 ? iDisplayStart / iDisplayLength : (iDisplayStart / iDisplayLength) + 1;
        PageInfo pageInfo = this.queryList(pageIndex, iDisplayLength, statement, arg);

        dataTables.setData(pageInfo.getList());
        dataTables.setiTotalRecords(pageInfo.getTotal());
        dataTables.setiTotalDisplayRecords(pageInfo.getTotal());
        return dataTables;
    }


}
