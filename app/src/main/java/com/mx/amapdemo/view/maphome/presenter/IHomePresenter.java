package com.mx.amapdemo.view.maphome.presenter;

import com.mx.amapdemo.base.ILifeCycle;

public interface IHomePresenter extends ILifeCycle {

    /**
     * 初始设置地图
     */
    void initSetMap();

    /**
     * 回自车
     */
    void backMe();
}
