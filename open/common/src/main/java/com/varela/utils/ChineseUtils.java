package com.varela.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by 51offer on 2016/2/24.
 * <p/>
 * 中文辅助类
 */
public class ChineseUtils {

    /**
     * 获取长度
     */
    public static int getLength(String str) {
        if (StringUtils.isNotBlank(str)) {
            int length = 0;
            for (int i = 0; i < str.length(); i++) {
                int ascii = Character.codePointAt(str, i);
                if (ascii >= 0 && ascii <= 255)
                    length++;
                else
                    length += 2;

            }
            return length;
        }
        return 0;
    }

    public static int getLengthRegex(String s) {
        s = s.replaceAll("[^\\x00-\\xff]", "**");
        int length = s.length();
        return length;
    }

    /**
     * 截取字符串
     */
    public static String getSubString(String str, int length) {
        int count = 0;
        int offset = 0;
        char[] c = str.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] > 256) {
                offset = 2;
                count += 2;
            } else {
                offset = 1;
                count++;
            }
            if (count == length) {
                return str.substring(0, i + 1);
            }
            if ((count == length + 1 && offset == 2)) {
                return str.substring(0, i);
            }
        }
        return "";
    }
}
