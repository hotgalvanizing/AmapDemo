package com.mx.amapdemo.base;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;

/**
 * 基础的FragmentActivity
 *
 * @author: liumy
 * @date: 2018/10/25
 * @desctiption: xxxx
 */
public class BaseFragmentActivity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //添加如果进入时为横屏强制全程为横屏 如果进入时为竖屏 强制全程竖屏 禁止进入导航后再切换横竖屏
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        Fragment existFragment = getSupportFragmentManager().findFragmentById(R.id.container);
//        String Tag = "";
//        if (existFragment instanceof BaseFragment) {
//            Tag = ((BaseFragment) existFragment).getTAG();
//        }
//        StringBuffer buffer = new StringBuffer();
//        buffer.append("当前页面为：");
//        buffer.append(Tag);
//        buffer.append("用户触发事件：");
//        buffer.append(ev.getAction());
//        buffer.append("当前触摸的X点为:");
//        buffer.append(ev.getX());
//        buffer.append("当前触摸的Y点为:");
//        buffer.append(ev.getY());
//        LogUtil.d(buffer.toString());
        return super.dispatchTouchEvent(ev);
    }
}
