package com.varela.lock.zk;


/**
 * @author lance
 *         2015-08-14 13:51
 */
public class ZKUtils {

    public static final String SEPARATOR = "/";

    /**
     * 转换path为zk的标准路径 以/开头,最后不带/
     */
    public static String normalize(String path) {
        String temp = path;
        if(!path.startsWith(SEPARATOR)) {
            temp = SEPARATOR + path;
        }
        if(path.endsWith(SEPARATOR)) {
            temp = temp.substring(0, temp.length()-1);
            return normalize(temp);
        }else {
            return temp;
        }
    }

    /**
     * 链接两个path,并转化为zk的标准路径
     */
    public static String contact(String path1,String path2){
        if(path2.startsWith(SEPARATOR)) {
            path2 = path2.substring(1);
        }
        if(path1.endsWith(SEPARATOR)) {
            return normalize(path1 + path2);
        } else {
            return normalize(path1 + SEPARATOR + path2);
        }
    }

    /**
     * 字符串转化成byte类型
     */
    public static byte[] toBytes(String data) {
        if(data == null || data.trim().equals("")) return null;
        return data.getBytes();
    }
}
