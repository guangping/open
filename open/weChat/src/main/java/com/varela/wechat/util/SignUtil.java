package com.varela.wechat.util;

import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.util.TreeMap;

/**
 * @author
 * @ClassName: SignUtil
 * @date 2015年8月18日 上午11:36:07
 * @Description: 生成签名
 */
public class SignUtil {

    private static Logger logger = Logger.getLogger(SignUtil.class);


    /**
     * 生成签名
     *
     * @return
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    public static String generateSign(TreeMap<String, Object> params, String secret)
            throws IllegalArgumentException, IllegalAccessException {
        StringBuffer sBuffer = new StringBuffer();
        for (String key : params.keySet()) {
            if (key.equals(WeChatConstKey.SIGN) || key.equals("success")) {
                continue;
            }
            sBuffer.append(key + "=" + params.get(key).toString() + "&");
        }
        String str = sBuffer.append("key=" + secret).toString();
        return EncryptUtils.MD5Encode(str).toUpperCase();
    }

    /**
     * 生成签名字符串 微信jsapi
     * SHA1
     *
     * @return
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    public static String generateSignSHA1(TreeMap<String, Object> params)
            throws IllegalArgumentException, IllegalAccessException, UnsupportedEncodingException {
        StringBuffer sBuffer = new StringBuffer();
        for (String key : params.keySet()) {
            if (key.equals(WeChatConstKey.SIGN)) {
                continue;
            }
            sBuffer.append(key.toLowerCase() + "=" + params.get(key).toString() + "&");
        }
        String suffix = sBuffer.substring(sBuffer.length() - 1, sBuffer.length());
        if (suffix.equals("&")) {
            sBuffer.deleteCharAt(sBuffer.length() - 1);
        }
        return EncryptUtils.SHA1Encode(sBuffer.toString());
    }

}
