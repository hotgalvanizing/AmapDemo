package com.mx.amapdemo.base;

/**
 * View刷新的回调，在MVP中是Presenter的View回调角色
 *
 * @param <T> 数据泛型
 */
public interface IView<T> {
    /**
     * 数据刷新回调<br/>
     * 继承此函数，完成View界面元素的数据刷新
     *
     * @param data 当前的数据
     */
    void onRefresh(T data);

}
