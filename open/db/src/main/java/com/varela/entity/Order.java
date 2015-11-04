package com.varela.entity;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * Created by lance on 11/4/2015.
 */
@Alias("Order")
public class Order implements Serializable {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
