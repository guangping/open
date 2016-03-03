package com.varela.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.varela.pojo.AMapAddress;
import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;

/**
 * Created by 51offer on 2016/2/24.
 * <p/>
 * 高德地图
 */
public class AMapUtils {
    /**
     * 经纬度获取地址
     */
    private static String aMapUrl = "http://restapi.amap.com/v3/geocode/regeo?output=json&location={0},{1}&key=19677bd2a7375b7015925554269d2095&radius=1000&extensions=all";

    /**
     * 根据地址获取省份信息
     */
    private static String aMapGeoUrl = "http://restapi.amap.com/v3/geocode/geo?key=19677bd2a7375b7015925554269d2095&address={0}&output=json";

    /**
     * 根据经纬度获取地址
     */
    public static AMapAddress getAddress(double longitude, double latitude) {
        AMapAddress address = null;
        String url = MessageFormat.format(aMapUrl, String.valueOf(longitude), String.valueOf(latitude));
        String str = HttpUtils.get(url);
        try {
            if (StringUtils.isNotBlank(url)) {
                JSONObject json = JSONObject.parseObject(str);
                if (null != json) {
                    int status = json.getIntValue("status");
                    if (status == 1) {
                        JSONObject regeocode = json.getJSONObject("regeocode");
                        if (null != regeocode) {
                            JSONObject addressComponent = regeocode.getJSONObject("addressComponent");
                            if (null != addressComponent) {
                                //city 值不固定
                                String province = addressComponent.getString("province");
                                String citycode = addressComponent.getString("citycode");
                                String city = addressComponent.getString("city");
                                if (StringUtils.isNotBlank(city) && city.equals("[]")) {
                                    city = "";
                                }
                                address = new AMapAddress();
                                address.setCityCode(citycode);
                                address.setProvince(province);
                                address.setCity(city);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
        return address;
    }

    public static AMapAddress getAddress(String addressStr) {
        AMapAddress address = null;
        String url = MessageFormat.format(aMapGeoUrl, addressStr);
        String str = HttpUtils.get(url);
        try {
            if (StringUtils.isNotBlank(str)) {
                JSONObject json = JSONObject.parseObject(str);
                if (null != json) {
                    int status = json.getIntValue("status");
                    if (status == 1) {
                        JSONArray geocodes = json.getJSONArray("geocodes");
                        if(null!=geocodes && geocodes.size()>0){
                            JSONObject jsonAddress=geocodes.getJSONObject(0);
                            address=new AMapAddress();
                            address.setCity(jsonAddress.getString("city"));
                            address.setCityCode(jsonAddress.getString("citycode"));
                            address.setProvince(jsonAddress.getString("province"));
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return address;
    }
}
