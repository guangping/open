package com.varela.dao;

import com.varela.api.entity.DeveloperApi;

import java.util.List;

/**
 * Created by lance on 2016/3/15.
 */
public interface DeveloperApiDao {

    List<DeveloperApi> queryList(DeveloperApi developerApi);

    void save(DeveloperApi developerApi);
}
