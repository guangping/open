package com.varela.open.impl;

import com.varela.open.security.ServiceAccessController;
import com.varela.open.session.Session;

/**
 * <pre>
 * 功能说明：对调用的方法进行安全性检查
 * </pre>
 */
public class DefaultServiceAccessController implements ServiceAccessController {


    public boolean isAppGranted(String appKey, String method, String version) {
        return true;
    }


    public boolean isUserGranted(Session session, String method, String version) {
        return true;
    }
}

