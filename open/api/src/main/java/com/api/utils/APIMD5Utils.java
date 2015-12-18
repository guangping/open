package com.api.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.SignatureException;
import java.util.TreeMap;

/**
 * Created by lance on 12/15/2015.
 */
public class APIMD5Utils {

    private static final String input_charset = "UTF-8";

    /**
     * 签名字符串
     *
     * @param params 需要签名参数
     * @param key    密钥
     * @return 签名结果
     */
    public static String sign(TreeMap<String, String> params, String key) {
        StringBuffer sBuffer = new StringBuffer();
        for (String obj : params.keySet()) {
            if (obj.equals("sign")) {
                continue;
            }
            if (StringUtils.isNotBlank(String.valueOf(params.get(obj))) && !String.valueOf(params.get(obj)).equals("null")) {
                sBuffer.append(obj + "=" + String.valueOf(params.get(obj)) + "&");
            }
        }
        return sign(sBuffer.toString(), key);
    }


    /**
     * 签名字符串
     *
     * @param text 需要签名的字符串
     * @param key  密钥
     * @return 签名结果
     */
    public static String sign(String text, String key) {
        StringBuffer buffer = new StringBuffer(200);
        buffer.append(key);
        buffer.append(text);
        buffer.append(key);
        return DigestUtils.md5Hex(getContentBytes(buffer.toString(), input_charset));
    }

    /**
     * 签名字符串
     *
     * @param text 需要签名的字符串
     * @return 签名结果
     */
    public static String sign(String text) {
        StringBuffer buffer = new StringBuffer(200);
        buffer.append(text);
        return DigestUtils.md5Hex(getContentBytes(buffer.toString(), input_charset));
    }

    /**
     * @param content
     * @param charset
     * @return
     * @throws SignatureException
     * @throws UnsupportedEncodingException
     */
    private static byte[] getContentBytes(String content, String charset) {
        if (StringUtils.isBlank(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }


}
