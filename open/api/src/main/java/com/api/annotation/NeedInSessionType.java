package com.api.annotation;

/**
 * session
 * */
public enum NeedInSessionType {
    YES, NO, DEFAULT;

    public static boolean isNeedInSession(NeedInSessionType type) {
        if (YES == type || DEFAULT == type) {
            return true;
        } else {
            return false;
        }
    }
}

