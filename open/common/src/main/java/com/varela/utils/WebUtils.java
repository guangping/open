package com.varela.utils;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created with IntelliJ IDEA. User: guangping Date: 2014-04-24 21:51 To change
 * this template use File | Settings | File Templates.
 */
public class WebUtils {
    /**
     * 获取URI的路径,如路径为http://www.babasport.com/action/post.htm?method=add,
     * 得到的值为"/action/post.htm"
     *
     * @param request
     * @return
     */
    public static String getRequestURI(HttpServletRequest request) {
        return request.getRequestURI();
    }

    /**
     * 获取完整请求路径(含内容路径及请求参数)
     *
     * @param request
     * @return
     */
    public static String getRequestURIWithParam(HttpServletRequest request) {
        return getRequestURI(request)
                + (StringUtils.isBlank(request.getQueryString()) ? ""
                : "?" + request.getQueryString());
    }

    /**
     * 添加cookie
     *
     * @param response
     * @param name     cookie的名称
     * @param value    cookie的值
     * @param maxAge   cookie存放的时间(以秒为单位,假如存放三天,即3*24*60*60; 如果值为0,cookie将随浏览器关闭而清除)
     */
    public static void addCookie(HttpServletResponse response, String name,
                                 String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    /**
     * 获取cookie的值
     *
     * @param request
     * @param name    cookie的名称
     * @return
     */
    public static String getCookieByName(HttpServletRequest request,
                                         String name) {
        Map<String, Cookie> cookieMap = readCookieMap(request);
        if (cookieMap.containsKey(name)) {
            Cookie cookie = (Cookie) cookieMap.get(name);
            return cookie.getValue();
        }
        return null;
    }

    private static Map<String, Cookie> readCookieMap(
            HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (int i = 0; i < cookies.length; i++) {
                cookieMap.put(cookies[i].getName(), cookies[i]);
            }
        }
        return cookieMap;
    }

    /**
     * 获取ip
     */
    public static String getIpAddr(HttpServletRequest request) {
        if (request == null) {
            return "unknown";
        }
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (org.apache.commons.lang3.StringUtils.isNotBlank(ip)) {
            String items[] = ip.split("\\,");
            if (items.length > 0) {
                ip = items[0];
            }
        }
        return ip;
    }

    /**
     * 获取请求头信息
     */
    @SuppressWarnings("unchecked")
    public static Map<String, List<String>> getHeaders(
            HttpServletRequest request) {
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        Enumeration<String> namesEnumeration = request.getHeaderNames();
        while (namesEnumeration.hasMoreElements()) {
            String name = namesEnumeration.nextElement();
            Enumeration<String> valueEnumeration = request.getHeaders(name);
            List<String> values = new ArrayList<String>();
            while (valueEnumeration.hasMoreElements()) {
                values.add(valueEnumeration.nextElement());
            }
            headers.put(name, values);
        }
        return headers;
    }

    /**
     * 获取所有参数
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String[]> getParamsArray(
            HttpServletRequest request) {
        Map<String, String[]> params = request.getParameterMap();
        return params;
    }

    /**
     * 获取所有参数
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> getParams(HttpServletRequest request) {
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter
                .hasNext(); ) {
            String name = iter.next();
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }
        return params;
    }

    /**
     * 设置值
     */
    @SuppressWarnings("unchecked")
    public static HttpServletRequest setHttpServletRequestValues(
            HttpServletRequest request) {
        HttpServletRequest val = request;
        Map<String, String[]> params = request.getParameterMap();
        for (Map.Entry<String, String[]> obj : params.entrySet()) {
            val.setAttribute(obj.getKey(), (obj.getValue().length == 1)
                    ? obj.getValue()[0] : obj.getValue());
        }
        return val;
    }

    public static String getParams(HttpServletRequest request, String key,
                                   String defaultValue) {
        return StringUtils.isNotBlank(request.getParameter(key))
                ? request.getParameter(key) : defaultValue;
    }

    public static long getParams(HttpServletRequest request, String key) {
        return StringCommonUtils.getSafeLong(request.getParameter(key));
    }

}
