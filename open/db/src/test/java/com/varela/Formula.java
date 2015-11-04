package com.varela;

/**
 * Created by lance on 11/4/2015.
 */
public interface Formula {

    default int add(int x){
        return x*x;
    }

}
