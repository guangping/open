package com.varela.open.client;

/**
 * Created with IntelliJ IDEA.
 * User: guangping
 * Date: 2014-06-30 16:30
 * To change this template use File | Settings | File Templates.
 */
public interface RopHttpClient {
    /**
     * 设置method系统参数的参数名，下同
     *
     * @param paramName
     * @return
     */
    RopHttpClient setAppKeyParamName(String paramName);

    /**
     * 设置sessionId的参数名
     *
     * @param paramName
     * @return
     */
    RopHttpClient setSessionIdParamName(String paramName);

    /**
     * 设置method的参数名
     *
     * @param paramName
     * @return
     */
    RopHttpClient setMethodParamName(String paramName);

    /**
     * 设置version的参数名
     *
     * @param paramName
     * @return
     */
    RopHttpClient setVersionParamName(String paramName);

    /**
     * 设置format的参数名
     *
     * @param paramName
     * @return
     */
    RopHttpClient setFormatParamName(String paramName);

    /**
     * 设置locale的参数名
     *
     * @param paramName
     * @return
     */
    RopHttpClient setLocaleParamName(String paramName);

    /**
     * 设置sign的参数名
     *
     * @param paramName
     * @return
     */
    RopHttpClient setSignParamName(String paramName);

    /**
     * 设置sessionId
     *
     * @param sessionId
     */
    void setSessionId(String sessionId);

    /**
     * 创建一个新的服务请求
     *
     * @return
     */
    ClientHttpRequest buildClientRequest();
}
