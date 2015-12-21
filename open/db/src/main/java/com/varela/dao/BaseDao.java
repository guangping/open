package com.varela.dao;

import java.util.List;

/**
 * Created by lance on 12/21/2015.
 */
public interface BaseDao<T> {

    long save(T arg);

    List<T> queryList(T arg);

    T queryObj(long id);

    T queryObj(T arg);

    int update(T arg);

    int delete(long id);

    int delete(T arg);

}
