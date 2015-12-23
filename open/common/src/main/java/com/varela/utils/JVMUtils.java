package com.varela.utils;

import com.varela.pojo.JVMemory;

/**
 * Created by lance on 12/23/2015.
 */
public class JVMUtils {

    /**
     * jvm内存使用情况
     */
    public static JVMemory getJvMemory() {
        long maxMemory = Runtime.getRuntime().maxMemory() / 1024 / 1024;
        long totalMemory = Runtime.getRuntime().totalMemory() / 1024 / 1024;
        long freeMemory = Runtime.getRuntime().freeMemory() / 1024 / 1024;

        JVMemory memory = new JVMemory();
        memory.setFreeMemory(freeMemory);
        memory.setTotalMemory(totalMemory);
        memory.setMaxMemory(maxMemory);

        return memory;
    }
}
