package com.mx.amapdemo.model.location;

import android.content.Context;

import com.amap.api.location.AMapLocation;

public interface ILocationManager {

    /**
     * 添加定位监听者
     *
     * @param listener
     */
    void addLocationListener(ILocationListener listener);

    /**
     * 移除定位监听者
     *
     * @param listener
     */
    void removeLocationListener(ILocationListener listener);

    /**
     * 初始化
     *
     * @param context
     */
    void init(Context context);

    /**
     * 销毁
     */
    void destory();
}
