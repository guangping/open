package com.varela.dao;

import com.varela.api.entity.API;

import java.util.List;

/**
 * Created by lance on 2016/3/15.
 */
public interface APIDao {

    List<API> queryList(API api);


    long save(API api);

    API queryByMethod(String method);


}
