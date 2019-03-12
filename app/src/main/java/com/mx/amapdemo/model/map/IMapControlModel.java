package com.mx.amapdemo.model.map;

public interface IMapControlModel {

//    切换地图图层

    /**
     * 初始设置地图
     */
    void initSetMap();

    /**
     * 返回自车位置
     */
    void backMe();

    /**
     * 放大地图
     */
    void zoomIn();

    /**
     * 缩小地图
     */
    void zoomOut();


}
