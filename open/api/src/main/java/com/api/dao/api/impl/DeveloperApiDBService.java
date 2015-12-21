package com.api.dao.api.impl;

import com.api.entity.DeveloperApi;
import com.varela.dao.BaseDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * Created by lance on 12/21/2015.
 */
@Repository
public class DeveloperApiDBService extends BaseDaoImpl<DeveloperApi> {
    @Override
    public long save(DeveloperApi arg) {
        this.getSqlSession().insert("DeveloperApi.save", arg);
        return arg.getId();
    }

    @Override
    public DeveloperApi queryObj(DeveloperApi arg) {
        return  this.getSqlSession().selectOne("DeveloperApi.queryList", arg);
    }
}
