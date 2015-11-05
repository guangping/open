package com.varela.open.client;


import com.varela.open.client.response.ErrorResponse;

/**
 * Created with IntelliJ IDEA.
 * User: guangping
 * Date: 2014-07-01 14:36
 * To change this template use File | Settings | File Templates.
 */
public interface CommonResponse<T> {
    /**
     * 获取错误的响应对象
     *
     * @return
     */
    ErrorResponse getErrorResponse();

    /**
     * 获取正确的响应对象
     */
    T getSuccessResponse();

    /**
     * 响应是否是正确的
     *
     * @return
     */
    boolean isSuccessful();
}
