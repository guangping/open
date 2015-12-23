package com.varela.pojo;

import java.io.Serializable;

/**
 * Created by lance on 12/23/2015.
 */
public class JVMemory implements Serializable {

    private long maxMemory=0;

    private long totalMemory=0;

    private long freeMemory=0;

    public long getMaxMemory() {
        return maxMemory;
    }

    public void setMaxMemory(long maxMemory) {
        this.maxMemory = maxMemory;
    }

    public long getTotalMemory() {
        return totalMemory;
    }

    public void setTotalMemory(long totalMemory) {
        this.totalMemory = totalMemory;
    }

    public long getFreeMemory() {
        return freeMemory;
    }

    public void setFreeMemory(long freeMemory) {
        this.freeMemory = freeMemory;
    }

    @Override
    public String toString() {
        return "JVMemory{" +
                "maxMemory=" + maxMemory +
                ", totalMemory=" + totalMemory +
                ", freeMemory=" + freeMemory +
                '}';
    }
}
