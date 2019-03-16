package com.mx.amapdemo.model.map;

/**
 * 地图控制单例类接口
 */
public interface IMapControlModel {

    /**
     * 注册的监听
     *
     * @param listener
     */
    void registerListener(IMapControlListener listener);

    /**
     * 反注册新的监听
     *
     * @param listener
     */
    void unregisterListener(IMapControlListener listener);

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
