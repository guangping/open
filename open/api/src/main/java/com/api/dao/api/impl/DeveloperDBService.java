package com.api.dao.api.impl;


import com.api.entity.Developer;
import com.varela.dao.BaseDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lance on 9/29/2015.
 */
@Repository
public class DeveloperDBService extends BaseDaoImpl<Developer> {


    /**
     * 查询接入者
     */
    public List<Developer> queryList(Developer developer) {
        return this.getSqlSession().selectList("developer.queryList", developer);
    }

    /**
     * 添加
     */
    public long save(Developer developer) {
        this.getSqlSession().insert("developer.save", developer);
        return developer.getId();
    }


    @Override
    public Developer queryObj(long id) {
        Developer developer=new Developer();
        developer.setId(id);
        return this.getSqlSession().selectOne("developer.queryList", developer);
    }
}
