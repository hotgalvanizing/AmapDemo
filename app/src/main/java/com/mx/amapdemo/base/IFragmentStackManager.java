package com.mx.amapdemo.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;


/**
 * Fragment堆栈的抽象接口
 *
 * @author PG.Xie
 * created on 2018/9/6
 */
public interface IFragmentStackManager<T extends Fragment> {

    /**
     * 返回到Focus页面的tag
     */
    String KEY_RETURN_FOCUS = "IFragmentStackManager.focus";

    interface OnStackChangedListener<T> {
        /**
         * 新的Fragment 进入回调
         *
         * @param fragment 新进入的Fragment
         */
        void onNewIn(T fragment);

        /**
         * 移除fragment 时回调
         *
         * @param fragment 移除的fragment
         */
        void onRemove(T fragment);
    }

    /**
     * 跳转到Fragment，如果已经存在，则直接返回
     *
     * @param fragment 新的Fragment
     */
    void go(T fragment);


    /**
     * 移除某个界面，如果移除的是非顶部的Fragment，不会影响当前目标的显示
     *
     * @param fragment
     */
    void remove(T fragment);

    /**
     * 回退到指定的Fragment
     *
     * @param clazz 目标
     */
    void backTo(Class<? extends T> clazz, Bundle data);

    /**
     * 添加回退栈的监听器
     *
     * @param listener
     */
    void addStackChangeListener(OnStackChangedListener listener);

    /**
     * 移除回退栈的监听器
     *
     * @param listener
     */
    void removeStackChangeListener(OnStackChangedListener listener);

    /**
     * 返回上一页
     */
    void back();

    /**
     * 获取顶层的fragment
     *
     * @return
     */
    T top();

    /**
     * 当前栈中的对象数量
     *
     * @return 当前fragment栈的数量
     */
    int size();
}
