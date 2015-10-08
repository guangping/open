package com.varela.dao.impl;

import com.varela.dao.BaseDaoImpl;
import com.varela.entity.Level;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lance on 9/29/2015.
 */
@Repository
public class LevelDBService extends BaseDaoImpl<Level> {

    public List<Level> queryList(Level level) {
        return this.getSqlSession().selectList("level.queryList", level);
    }

    public Level queryObject(Level level) {
        return this.getSqlSession().selectOne("level.queryList", level);
    }


    @Transactional
    public String save(Level level)throws RuntimeException{
        this.getSqlSession().insert("level.save", level);
        return level.getId();
    }
}
