package com.varela.open.event;

import com.varela.open.RopContext;


public class PreCloseRopEvent extends RopEvent {
    public PreCloseRopEvent(Object source, RopContext ropContext) {
        super(source, ropContext);
    }
}

