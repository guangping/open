package com.varela.api.security;

/**
 * 服务访问次数及频率的控制管理器
 */
public interface InvokeTimesController {

    /**
     * 应用调用权限
     */
    boolean checkMethodPermissions(String appKey, String method);

    /**
     * 计算应用、服务调用次数
     * */
    void caculateInvokeTimes(String appKey, String method);


    /**
     * 应用的服务访问次数是否超限
     * */
    boolean isAppInvokeLimitExceed(String appKey, String method);

    /**
     * 应用对服务的访问频率是否超限
     *
     * @param appKey
     * @return
     */
    boolean isAppInvokeFrequencyExceed(String appKey);

}
