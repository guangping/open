package com.varela.open.session;

import com.varela.open.Constants;

import java.util.HashMap;
import java.util.Map;


public abstract class AbstractSession implements Session {

    private Map<String, Object> attributes = new HashMap<String, Object>();


    public void setAttribute(String name, Object obj) {
        markChanged();
        attributes.put(name, obj);
    }


    public Object getAttribute(String name) {
        markChanged();
        return attributes.get(name);
    }


    public Map<String, Object> getAllAttributes() {
        Map<String, Object> tempAttributes = new HashMap<String, Object>(attributes.size());
        for (Map.Entry<String, Object> entry : attributes.entrySet()) {
            if (!Constants.SESSION_CHANGED.equals(entry.getKey())) {
                tempAttributes.put(entry.getKey(), entry.getValue());
            }
        }
        return tempAttributes;
    }


    public void removeAttribute(String name) {
        markChanged();
        attributes.remove(name);
    }


    public boolean isChanged() {
        return attributes.containsKey(Constants.SESSION_CHANGED);
    }

    private void markChanged() {
        attributes.put(Constants.SESSION_CHANGED, Boolean.TRUE);
    }
}

