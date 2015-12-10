package com.varela.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by lance on 12/10/2015.
 */
public class StringCommonUtils {

    /**
     * 判断当前字符是否为纯数字
     *
     * @param str
     * @return
     */
    public static boolean isDigit(String str) {
        String pattern = "-{0,1}\\d+";
        return str.matches(pattern);
    }

    public static boolean isFloat(String str) {
        String pattern = "-{0,1}\\d+(\\.\\d+)?";
        return str.matches(pattern);
    }

    public static int getSafeInt(String value) {
        if (StringUtils.isBlank(value)) {
            return 0;
        }
        if (!isDigit(value)) {
            return 0;
        }
        return Integer.valueOf(value);
    }

    public static long getSafeLong(String value) {
        if (StringUtils.isBlank(value)) {
            return 0;
        }
        if (!isDigit(value)) {
            return 0;
        }
        return Long.valueOf(value);
    }

    public static boolean isBool(String value) {
        if ("true".equalsIgnoreCase(value) || "false".equalsIgnoreCase(value)) {
            return Boolean.valueOf(value);
        }
        return false;
    }

    public static boolean getSafeBoolean(String value) {
        if (StringUtils.isBlank(value)) {
            return false;
        }
        if (!isBool(value)) {
            return false;
        }
        return Boolean.valueOf(value);
    }

    public static double getSafeDouble(String value) {
        if (StringUtils.isBlank(value)) {
            return 0;
        }
        if (!isFloat(value)) {
            return 0;
        }
        return Double.valueOf(value);
    }

    public static Integer getSafeInterger(String value) {
        if (StringUtils.isBlank(value)) {
            return 0;
        }
        if (!isDigit(value)) {
            return 0;
        }
        return Integer.valueOf(value);
    }

    public static short getSafeShort(String value) {
        if (StringUtils.isBlank(value)) {
            return 0;
        }
        if (!isDigit(value)) {
            return 0;
        }
        return Short.valueOf(value);
    }

    public static float getSafeFloat(String value) {
        if (StringUtils.isBlank(value)) {
            return 0.0f;
        }
        if (!isFloat(value)) {
            return 0;
        }
        return Float.valueOf(value);
    }

    public static String getSafeString(String value) {
        if (StringUtils.isBlank(value)) {
            return "";
        }
        return value;
    }


}
