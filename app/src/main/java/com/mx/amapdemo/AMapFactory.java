package com.mx.amapdemo;

import com.amap.api.maps.AMap;

public class AMapFactory {

    AMap mAMap;

    public AMapFactory() {
    }

    public static AMapFactory getInstance() {
        return AMapFactory.a.a;
    }

    static class a {
        static final AMapFactory a = new AMapFactory();
    }

    public void setmAMap(AMap mAMap) {
        this.mAMap = mAMap;
    }

    public AMap getmAMap() {
        return mAMap;
    }
}
