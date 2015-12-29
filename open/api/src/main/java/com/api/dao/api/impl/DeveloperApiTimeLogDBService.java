package com.api.dao.api.impl;

import com.api.entity.DeveloperApiTimeLog;
import com.varela.dao.BaseDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lance on 12/29/2015.
 */
@Repository
public class DeveloperApiTimeLogDBService extends BaseDaoImpl<DeveloperApiTimeLog> {

    @Override
    public long save(DeveloperApiTimeLog arg) {
        return this.getSqlSession().insert("DeveloperApiTimeLog.save", arg);
    }

    public void saveBatch(List<DeveloperApiTimeLog> logList) {
        this.getSqlSession().insert("DeveloperApiTimeLog.saveBatch", logList);
    }
}
