package com.varela.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by lance on 10/27/2015.
 * <p>
 * 配置文件读取
 */
public class ResourceUtils {


    private String files[];

    private Map<String, String> map = null;


    public ResourceUtils(String[] files) {
        this.files = files;
        map = new HashMap<String, String>();
        if (null != this.files && this.files.length > 0) {
            ResourceBundle resourceBundle = null;
            for (String item : this.files) {
                resourceBundle = ResourceBundle.getBundle(item);
                this.setValues(resourceBundle);
            }
        }
    }

    private void setValues(ResourceBundle resourceBundle) {
        for (String key : resourceBundle.keySet()) {
            this.map.put(key, resourceBundle.getString(key));
        }
    }


    public String getStringValue(String key) {
        return this.map.get(key);
    }

    public int getIntValue(String key) {
        String rval = this.map.get(key);
        return (StringUtils.isNotBlank(rval)) ? Integer.valueOf(rval) : 0;
    }

    public long getLongValue(String key) {
        String rval = this.map.get(key);
        return (StringUtils.isNotBlank(rval)) ? Long.valueOf(rval) : 0;
    }

    public double getDoubleValue(String key) {
        String rval = this.map.get(key);
        return (StringUtils.isNotBlank(rval)) ? Double.valueOf(rval) : 0;
    }

    public float getFloatValue(String key) {
        String rval = this.map.get(key);
        return (StringUtils.isNotBlank(rval)) ? Float.valueOf(rval) : 0;
    }

    public boolean getBooleanValue(String key) {
        String rval = this.map.get(key);
        return (StringUtils.isNotBlank(rval)) ? Boolean.valueOf(rval) : false;
    }


}
