package com.varela.annotation;

import java.lang.annotation.*;

/**
 * Created by lance on 2016-06-20.
 * 权限验证相关
 * 有此注解的不进行验证
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GuestPage {

}
