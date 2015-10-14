package com.varela.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 获取bean
 */
public class SpringApplicationContext implements ApplicationContextAware {

    private static ConfigurableApplicationContext applicationContext;

    /**
     *
     */
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringApplicationContext.applicationContext = (ConfigurableApplicationContext) applicationContext;
    }

    /**
     * 获取bean
     *
     * @param name
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        checkApplicationContext();
        return (T) applicationContext.getBean(name);
    }

    /**
     * 获取bean
     *
     * @param clazz
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> clazz) {
        checkApplicationContext();
        return (T) applicationContext.getBean(clazz);
    }

    private static void checkApplicationContext() {
        if (applicationContext == null)
            throw new IllegalStateException(
                    "applicaitonContext未注入,请在spring配置文件中定义SpringContextHolder");
    }


}
