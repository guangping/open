package com.varela.pojo;

import java.io.Serializable;

/**
 * Created by 51offer on 2016/2/24.
 */
public class AMapAddress implements Serializable {

    private String province;

    private String city;

    private String cityCode;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    @Override
    public String toString() {
        return "AMapAddress{" +
                "province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", cityCode='" + cityCode + '\'' +
                '}';
    }
}
