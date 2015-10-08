package com.varela.open.listener;

import com.varela.open.RopRequestContext;
import com.varela.open.event.PreDoServiceEvent;
import com.varela.open.event.RopEventListener;
import com.varela.open.marshaller.MessageMarshallerUtils;

import java.util.Map;


public class SamplePreDoServiceEventListener implements RopEventListener<PreDoServiceEvent> {


    public void onRopEvent(PreDoServiceEvent ropEvent) {
        RopRequestContext ropRequestContext = ropEvent.getRopRequestContext();
        if (ropRequestContext != null) {
            Map<String, String> allParams = ropRequestContext.getAllParams();
            String message = MessageMarshallerUtils.getMessage(allParams, ropRequestContext.getMessageFormat());
            System.out.println("message(" + ropEvent.getServiceBeginTime() + ")" + message);
        }
    }


    public int getOrder() {
        return 1;
    }
}

