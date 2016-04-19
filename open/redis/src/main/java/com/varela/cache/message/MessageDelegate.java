package com.varela.cache.message;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by lance on 2016/4/19.
 */
public interface MessageDelegate {

    void handleMessage(String message);
    void handleMessage(Map message);
    void handleMessage(byte[] message);
    void handleMessage(Serializable message);
    // pass the channel/pattern as well
    void handleMessage(Serializable message, String channel);

}
