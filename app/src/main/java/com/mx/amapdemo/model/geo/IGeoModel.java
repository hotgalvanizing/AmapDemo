package com.mx.amapdemo.model.geo;

import com.amap.api.maps.model.LatLng;

/**
 * 逆地理编码，不是单例的，即插即用
 */
public interface IGeoModel {

    void setGeoListener(IGeoModelListener listener);

    /**
     * 通过经纬度逆地理
     *
     * @param latLng 需要逆地理的经纬度
     */
    void reverseGeo(LatLng latLng);

}
