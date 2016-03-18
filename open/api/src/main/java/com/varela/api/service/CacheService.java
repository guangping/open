package com.varela.api.service;

/**
 * Created by lance on 2016/3/18.
 * 定义缓存基础方法
 */
public interface CacheService<T> {

    void setCache(T arg);
}
