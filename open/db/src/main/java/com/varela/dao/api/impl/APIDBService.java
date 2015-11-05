package com.varela.dao.api.impl;

import com.varela.dao.BaseDaoImpl;
import com.varela.entity.API;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lance on 9/29/2015.
 */
@Repository
public class APIDBService extends BaseDaoImpl<API> {

    public List<API> queryList(API api) {
        return this.getSqlSession().selectList("api.queryList", api);
    }

    public API queryObject(API api) {
        return this.getSqlSession().selectOne("api.queryList", api);
    }


    @Transactional
    public String save(API api) {
        this.getSqlSession().insert("api.save", api);
        return api.getId();
    }
}
