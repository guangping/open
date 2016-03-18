package com.varela.dao;

import com.varela.api.entity.Developer;

import java.util.List;

/**
 * Created by lance on 2016/3/15.
 */
public interface DeveloperDao  {

    void save(Developer developer);

    List<Developer> queryList(Developer developer);

    Developer queryByAppKey(String appKey);

    Developer queryById(Long id);

}
