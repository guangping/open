package com.varela.log.pojo;

import java.io.Serializable;

/**
 * Created by lance on 11/9/2015.
 * 注解参数
 */
public class MethodLogExplain implements Serializable {
    private String event;

    private Class[] paramClazz;


    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Class[] getParamClazz() {
        return paramClazz;
    }

    public void setParamClazz(Class[] paramClazz) {
        this.paramClazz = paramClazz;
    }
}
