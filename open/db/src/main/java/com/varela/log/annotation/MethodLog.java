package com.varela.log.annotation;

import java.lang.annotation.*;

/**
 * Created by lance on 11/9/2015.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MethodLog {
    /**
     * 事件
     */
    String event() default "";

    /**
     * 参数类型
     */
    Class[] paramClazz() default {};

}
