package com.varela.utils;

import org.apache.commons.lang.math.RandomUtils;

import java.text.DecimalFormat;


public class NumberFormat {
    public static String decimalFormat(double value) {
        return new DecimalFormat("0.00").format(value);
    }


    public static String decimalFormat(double value, String pattern) {
        return new DecimalFormat(pattern).format(value);
    }

    public static String decimalBlankFormat(double value) {
        return new DecimalFormat("0").format(value);
    }


    public static String getRandom() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            builder.append(RandomUtils.nextInt(10));
        }
        return builder.toString();
    }
}
