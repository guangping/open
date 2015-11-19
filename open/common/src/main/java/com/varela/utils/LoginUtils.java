package com.varela.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 * User: lance
 * Date: 2014-12-12 11:22
 * To change this template use File | Settings | File Templates.
 * <p>
 * 登陆辅助
 */
public class LoginUtils {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * session中设置值
     *
     * @param key
     * @param value
     */
    public static void setSession(String key, Object value) {
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        session.setAttribute(key, value);
    }

    public static void remove(String key) {
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        session.removeAttribute(key);
    }

    /**
     * 获取session值
     */
    public static <T> T getSessionValue(String key, T t) {
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        Object rval = session.getAttribute(key);
        if (null == rval) {
            return null;
        }
        return (T) rval;
    }

    public static String getSessionValue(String key) {
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        Object rval = session.getAttribute(key);
        if (null == rval) {
            return null;
        }
        return rval.toString();
    }


}
