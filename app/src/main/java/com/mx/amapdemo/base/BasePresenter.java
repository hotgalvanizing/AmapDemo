package com.mx.amapdemo.base;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;

import java.lang.ref.WeakReference;


/**
 * 基本的Presenter，提供构造函数以及生命周期的默认实现
 * <p>
 * author PG.Xie
 */
public class BasePresenter<D> implements ILifeCycle {

    protected Handler mHandler = new Handler(Looper.getMainLooper());
    WeakReference<IView<D>> mViewHolder;
    /**
     * 当前的数据
     */
    D mData;

    private boolean visible = false;
    private boolean live = false;
    private boolean viewLive = false;

    public BasePresenter(IView<D> view, D data) {
        mViewHolder = new WeakReference<>(view);
        mData = data;
    }

    /**
     * 获取当前的数据
     *
     * @return 当前的数据
     */
    public D getData() {
        return mData;
    }

    @Override
    public void onCreate() {
        live = true;
    }

    @Override
    public void onDestroy() {
        live = false;
    }

    @Override
    public void onViewCreated() {
        viewLive = true;
    }

    @Override
    public void onViewDetroyed() {
        viewLive = false;
    }

    @Override
    public void onEnter() {
        visible = true;
    }

    @Override
    public void onHideBack() {
        visible = false;
    }

    @Override
    public void onReEnter(Bundle data) {
        refresh();
    }

    /**
     * 刷新数据 默认在页面隐藏时或销毁时不刷新页面
     */
    public void refresh() {
        refresh(false);
    }

    /**
     * 刷新数据
     *
     * @param isHiddenRefresh 是否需要在页面隐藏但未销毁时刷新页面
     */
    public void refresh(final boolean isHiddenRefresh) {
        if (Looper.getMainLooper().equals(Looper.myLooper())) {
            refreshInner(isHiddenRefresh);
        } else {
            mHandler.post(() -> refreshInner(isHiddenRefresh));
        }
    }

    /**
     * 刷新数据
     *
     * @param isHiddenRefresh 是否需要在页面隐藏但未销毁时刷新页面
     */
    private void refreshInner(boolean isHiddenRefresh) {
        if (!isViewDetached()) {
            if (mViewHolder.get() instanceof Fragment) {
                Fragment view = (Fragment) mViewHolder.get();
                if (view.isHidden() && !isHiddenRefresh) {
                    return;
                }
            }
            mViewHolder.get().onRefresh(getData());
        }
    }

    /**
     * 是否View已经分离了
     *
     * @return true表示已经分离了，false表示未分离
     */
    protected boolean isViewDetached() {
        return mViewHolder.get() == null;
    }

    public boolean isVisible() {
        return visible;
    }

    public boolean isLive() {
        return live;
    }

    public boolean isViewLive() {
        return viewLive;
    }
}
