package com.varela.open.id.impl;


import com.varela.open.id.StateService;


public class DefaultStateService implements StateService {

    @Override
    public long getState() {
        return Thread.currentThread().getId();
    }

}
