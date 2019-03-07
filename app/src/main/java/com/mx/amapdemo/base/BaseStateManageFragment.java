package com.mx.amapdemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

/**
 * @author: liumy
 * @date: 2018/08/28
 * @desctiption: 管理fragment状态的基类
 */
abstract class BaseStateManageFragment extends Fragment {
    /**
     * Tag
     */
    protected static String TAG;
    /**
     * 是否已销毁
     */
    boolean isDestroyed = false;
    /**
     * 是否为fm管理器设置显示, 其他使用者忽略
     */
    boolean isFmShow = false;
    /**
     * 是否为第一次进入
     */
    boolean isFirstEnter = true;
    /**
     * active true表示对用户当前可见，false表示不可见
     */
    private boolean isActive = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getName();
        Log.d(TAG, " onCreate");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, " onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, " onPause");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isDestroyed = true;
        Log.d(TAG, " onDestroy");
    }

    /**
     * 当fragment隐藏在后面
     */
    public void onHideBack() {
        Log.d(TAG, " onHideBack");
        isActive = false;
    }

    /**
     * 当再次进入，一般是前面fragment退栈
     */
    public void onReEnter(Bundle args) {
        Log.d(TAG, " onReEnter");
    }

    /**
     * 进入该fragment
     */
    public void onEnter() {
        Log.d(TAG, " onEnter");
        isFirstEnter = false;
        isActive = true;
    }

    /**
     * 退出该fragment
     */
    public void onExit() {
        Log.d(TAG, " onExit");
    }

    /**
     * 返回处理
     *
     * @return true:自己处理 false:自动处理
     */
    public boolean onBackPressed() {
        return false;
    }

    /**
     * 是否已经销毁了
     *
     * @return
     */
    public boolean isDestroyed() {
        return isDestroyed;
    }

    /**
     * 处于 onEnter与onHideBack之间
     *
     * @return
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * 配合StackManager使用,标记是否显示,其他使用者请忽略
     *
     * @return
     */
    public boolean isFmShow() {
        return isFmShow;
    }

    /**
     * 配合StackManager使用,标记是否显示,其他使用者请忽略
     *
     * @return
     */
    public void setFmShow(boolean fmShow) {
        isFmShow = fmShow;
    }

    /**
     * 是否为第一次进入
     *
     * @return
     */
    public boolean isFirstEnter() {
        return isFirstEnter;
    }

    /**
     * 获取TAG
     *
     * @return
     */
    public String getTAG() {
        return TAG;
    }

}
