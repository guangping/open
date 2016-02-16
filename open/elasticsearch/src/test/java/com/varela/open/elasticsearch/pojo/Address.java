package com.varela.open.elasticsearch.pojo;

import java.io.Serializable;

/**
 * Created by lance on 2016/2/14.
 */


public class Address implements Serializable {

    private String address;

    private String id;


    private double lat;

    private double lng;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Address{" +
                "address='" + address + '\'' +
                ", id=" + id +
                ", lat=" + lat +
                ", lng=" + lng +
                '}';
    }
}

