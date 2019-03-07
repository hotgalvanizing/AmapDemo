package com.mx.amapdemo.base;

import android.os.Bundle;

/**
 * 生命周期回调，这个生命周期的依据主要是为了与我们美行的Fragment相匹配
 *
 * author PG.Xie
 */
public interface ILifeCycle {
    /**
     * 创建
     */
    void onCreate();

    /**
     * 销毁
     */
    void onDestroy();

    /**
     * View创建
     */
    void onViewCreated();

    /**
     * View销毁
     */
    void onViewDetroyed();

    /**
     * 当前界面对用户可见
     */
    void onEnter();

    /**
     * 当前界面对用户不可见
     */
    void onHideBack();

    /**
     * 当前界面重新对用户可见（至少会走一次onStart）
     *
     * @param bundle current restart bundle
     */
    void onReEnter(Bundle bundle);
}
