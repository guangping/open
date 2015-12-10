package com.api.annotation;


public enum IgnoreSignType {

    YES, NO, DEFAULT;

    public static boolean isIgnoreSign(IgnoreSignType type) {
        if (NO == type || DEFAULT == type) {
            return false;
        } else {
            return true;
        }
    }
}

