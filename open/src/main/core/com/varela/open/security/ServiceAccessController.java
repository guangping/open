package com.varela.open.security;

import com.varela.open.session.Session;

/**
 * <pre>
 *    安全控制控制器，决定用户是否有。
 * </pre>
 */
public interface ServiceAccessController {

    /**
     * 服务方法是否向ISV开放
     *
     * @param appKey
     * @param method
     * @param version
     * @return boolean
     */
    boolean isAppGranted(String appKey, String method, String version);

    /**
     * 服务方法是否向当前用户开放
     *
     * @param session
     * @param method
     * @param version
     * @return
     */
    boolean isUserGranted(Session session, String method, String version);
}

