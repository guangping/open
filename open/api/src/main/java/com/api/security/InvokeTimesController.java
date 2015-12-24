package com.api.security;

/**
 * 服务访问次数及频率的控制管理器
 */
public interface InvokeTimesController {

    /**
     * 应用调用权限
     */
    boolean checkMethod(String appKey, String method);


}
