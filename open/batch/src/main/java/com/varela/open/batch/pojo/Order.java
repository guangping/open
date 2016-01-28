package com.varela.open.batch.pojo;


import java.io.Serializable;

/**
 * Created by lance on 2016/1/28.
 */
public class Order implements Serializable {

    //"sender_address","latitude","longitude","delivery_address","r_latitude","r_longitude","sender_mobileno","receiver_mobileno"
    private String senderAddress;

    private String latitude;

    private String longitude;

    private String deliveryAddress;

    private String rLatitude;

    private String rLongitude;

    private String senderMobile;

    private String receiverMobile;

    public Order() {
    }


    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getrLatitude() {
        return rLatitude;
    }

    public void setrLatitude(String rLatitude) {
        this.rLatitude = rLatitude;
    }

    public String getrLongitude() {
        return rLongitude;
    }

    public void setrLongitude(String rLongitude) {
        this.rLongitude = rLongitude;
    }

    public String getSenderMobile() {
        return senderMobile;
    }

    public void setSenderMobile(String senderMobile) {
        this.senderMobile = senderMobile;
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
    }

    @Override
    public String toString() {
        return "Order{" +
                "senderAddress='" + senderAddress + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", rLatitude=" + rLatitude +
                ", rLongitude=" + rLongitude +
                ", senderMobile='" + senderMobile + '\'' +
                ", receiverMobile='" + receiverMobile + '\'' +
                '}';
    }
}
