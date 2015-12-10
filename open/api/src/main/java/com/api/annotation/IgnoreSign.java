package com.api.annotation;


import java.lang.annotation.*;

/**
 * 签名
 **/
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreSign {
}
