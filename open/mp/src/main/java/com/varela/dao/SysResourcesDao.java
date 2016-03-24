package com.varela.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.varela.entity.SysResources;

public interface SysResourcesDao {
    int deleteByPrimaryKey(Long id);

    int insert(SysResources record);

    int insertSelective(SysResources record);

    SysResources selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysResources record);

    int updateByPrimaryKey(SysResources record);

    PageList<SysResources> queryList(SysResources sysResources, PageBounds pageBounds);
}