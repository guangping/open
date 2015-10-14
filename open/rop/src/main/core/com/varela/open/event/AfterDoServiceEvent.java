package com.varela.open.event;

import com.varela.open.RopRequestContext;


public class AfterDoServiceEvent extends RopEvent {

    private RopRequestContext ropRequestContext;

    public AfterDoServiceEvent(Object source, RopRequestContext ropRequestContext) {
        super(source, ropRequestContext.getRopContext());
        this.ropRequestContext = ropRequestContext;
    }

    public long getServiceBeginTime() {
        return ropRequestContext.getServiceBeginTime();
    }

    public long getServiceEndTime() {
        return ropRequestContext.getServiceEndTime();
    }

    public RopRequestContext getRopRequestContext() {
        return ropRequestContext;
    }
}

