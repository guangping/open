package com.varela.mp.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.varela.dao.SysResourcesDao;
import com.varela.entity.SysResources;
import com.varela.pojo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lance on 2016/3/24.
 */
@Service
public class SysResourcesService {

    @Autowired
    private SysResourcesDao sysResourcesDao;


    public Page<SysResources> queryList(SysResources sysResources,int pageIndex,int pageSize){
        PageBounds pageBounds = new PageBounds();
        pageBounds.setPage(pageIndex);
        pageBounds.setLimit(pageSize);
        pageBounds.setContainsTotalCount(true);
        PageList<SysResources> pageList = this.sysResourcesDao.queryList(sysResources, pageBounds);

        Page page=new Page();
        page.setCurrPage(pageIndex);
        page.setData(pageList);
        page.setPageSize(pageSize);
        page.setTotalCount(pageList.getPaginator().getTotalCount());
        page.setTotalPage(pageList.getPaginator().getTotalPages());

        return page;
    }






}
