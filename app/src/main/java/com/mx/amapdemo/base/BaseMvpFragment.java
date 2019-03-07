package com.mx.amapdemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * 基础的MVP Fragment架构。<br/>
 * 继承此Fragment获取对Presenter的支持。<br/>
 * 重写{@link #onCreatePresenter()}，返回当前页面支持的Presetener。<br/>
 * 调用{@link #getPresenter()}获取当前界面的Presenter。
 *
 * @author PG.Xie
 */
public abstract class BaseMvpFragment<P extends ILifeCycle> extends BaseFragment {

    /**
     * Presenter
     */
    P mPresenter;

    /**
     * 需要重写该方法来返回一个Presenter
     *
     * @return
     */
    protected abstract P onCreatePresenter();

    /**
     * 获取presenter
     *
     * @return
     */
    public P getPresenter() {
        return mPresenter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = onCreatePresenter();
        BasePresenter presenter = (BasePresenter) mPresenter;
        PresenterStackManager.add(presenter);
        if (mPresenter != null) {
            mPresenter.onCreate();
        }
    }

    @Override
    public void onEnter() {
        super.onEnter();
        if (mPresenter != null) {
            mPresenter.onEnter();
        }
    }

    @Override
    public void onReEnter(Bundle bundle) {
        super.onReEnter(bundle);
        if (mPresenter != null) {
            mPresenter.onReEnter(bundle);
        }
    }

    @Override
    public void onHideBack() {
        super.onHideBack();
        if (mPresenter != null) {
            mPresenter.onHideBack();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mPresenter != null) {
            mPresenter.onViewCreated();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.onViewDetroyed();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mPresenter != null) {
            mPresenter.onDestroy();
        }

        /**
         * clear presenter
         */
        BasePresenter presenter = (BasePresenter) mPresenter;
        PresenterStackManager.remove(presenter);
        mPresenter = null;
    }
}
