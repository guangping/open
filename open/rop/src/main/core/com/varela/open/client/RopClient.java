package com.varela.open.client;

import com.varela.open.request.RopConverter;


public interface RopClient {

    /**
     * 添加自定义的转换器
     *
     * @param ropConverter
     */
    void addRopConvertor(RopConverter ropConverter);

    /**
     * 设置method系统参数的参数名，下同
     *
     * @param paramName
     * @return
     */
    RopClient setAppKeyParamName(String paramName);

    /**
     * 设置sessionId的参数名
     *
     * @param paramName
     * @return
     */
    RopClient setSessionIdParamName(String paramName);

    /**
     * 设置method的参数名
     *
     * @param paramName
     * @return
     */
    RopClient setMethodParamName(String paramName);

    /**
     * 设置version的参数名
     *
     * @param paramName
     * @return
     */
    RopClient setVersionParamName(String paramName);

    /**
     * 设置format的参数名
     *
     * @param paramName
     * @return
     */
    RopClient setFormatParamName(String paramName);

    /**
     * 设置locale的参数名
     *
     * @param paramName
     * @return
     */
    RopClient setLocaleParamName(String paramName);

    /**
     * 设置sign的参数名
     *
     * @param paramName
     * @return
     */
    RopClient setSignParamName(String paramName);

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
    ClientRequest buildClientRequest();
}

