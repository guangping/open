package com.varela.open.event;

import com.varela.open.RopContext;

import java.util.EventObject;


public abstract class RopEvent extends EventObject {

    private RopContext ropContext;

    public RopEvent(Object source, RopContext ropContext) {
        super(source);
        this.ropContext = ropContext;
    }

    public RopContext getRopContext() {
        return ropContext;
    }
}

