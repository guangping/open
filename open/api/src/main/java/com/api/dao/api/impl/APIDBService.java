package com.api.dao.api.impl;


import com.api.entity.API;
import com.varela.dao.BaseDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lance on 9/29/2015.
 */
@Repository
public class APIDBService extends BaseDaoImpl<API> {

    public List<API> queryList(API api) {
        return this.getSqlSession().selectList("api.queryList", api);
    }


    @Override
    public API queryObj(long id) {
        API api = new API();
        api.setId(id);
        return this.getSqlSession().selectOne("api.queryList", api);
    }

    @Override
    public API queryObj(API arg) {
        return this.getSqlSession().selectOne("api.queryList", arg);
    }

    public long save(API api) {
        this.getSqlSession().insert("api.save", api);
        return api.getId();
    }
}
