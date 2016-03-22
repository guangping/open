package com.varela.api.service;

/**
 * Created by lance on 2016/3/18.
 * 定义缓存基础方法
 */
public interface CacheService<T> {

    /**
     * 设置缓存信息
     * */
    void setCache(T arg);
}
