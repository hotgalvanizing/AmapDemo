package com.mx.amapdemo.base;


import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.mx.amapdemo.MainActivity;
import com.mx.amapdemo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * fragment栈管理
 * Created by peix on 2018/4/17.
 */

public class StackFragmentManager implements IFragmentStackManager<BaseFragment> {

    FragmentManager fragmentManager;
    Handler mHandler = new Handler(Looper.getMainLooper());
    HashMap<Class, Bundle> mBundleMap = new HashMap<>();

    MainActivity mainActivity;
    List<OnStackChangedListener<BaseFragment>> mStackChangedListeners = new LinkedList<>();

    /**
     * 栈的查找结果
     */
    private static final class FindStatckResult {
        /**
         * 需要查找的目标
         */
        public final BaseFragment fragment;
        /**
         * 是否找到，其实就是fragment 是否为null
         */
        public final boolean isFinded;

        public FindStatckResult(BaseFragment fragment, boolean isFinded) {
            this.fragment = fragment;
            this.isFinded = isFinded;
        }
    }

    /**
     * 栈管理
     */
    private List<BaseFragment> fragments = new ArrayList();

    public StackFragmentManager(FragmentManager fragmentManager, MainActivity mainActivity) {
        this.fragmentManager = fragmentManager;
        this.mainActivity = mainActivity;
    }

    @Override
    public void go(BaseFragment fragment) {
        push(fragment);
    }

    /**
     * 移除某个界面，但是不会影响当前的显示
     *
     * @param fragment
     */
    @Override
    public void remove(BaseFragment fragment) {
        FindStatckResult result = find(fragment.getClass());
        if (result.isFinded) {
            FragmentTransaction trans = fragmentManager.beginTransaction();
            remove(trans, result.fragment);
            trans.commitNowAllowingStateLoss();
        }
    }

    /**
     * back to specific fragment,current fragment will be removed
     *
     * @param clazz
     * @param data  the data you wanna pass to fragment
     */
    @Override
    public void backTo(Class<? extends BaseFragment> clazz, Bundle data) {

        if (clazz == null) {
            back();
            return;
        }

        FindStatckResult result = find(clazz);

        if (!result.isFinded) {
            back();
            return;
        }

        if (data != null) {
            setMessageToFragment(result.fragment.getClass(), data);
        }

        pop(result.fragment, false);
    }

    /**
     * 返回上一页
     */
    @Override
    public void back() {
        if (fragments.size() < 1) {
            return;
        }
        //如果只剩一个FU页 也不回退了
//        if (fragments.size() == 1 && fragments.get(0) instanceof FuFragment) {
//            return;
//        }

        BaseFragment last = fragments.get(fragments.size() - 1);
        pop(last, true);
    }

    /**
     * 获取顶层的fragment
     *
     * @return
     */
    @Override
    public BaseFragment top() {
        if (!fragments.isEmpty()) {
            return fragments.get(fragments.size() - 1);
        }
        return null;
    }

    /**
     * 获取当前栈中的对象数量
     *
     * @return
     */
    @Override
    public int size() {
        return fragments.size();
    }

    @Override
    public void addStackChangeListener(OnStackChangedListener listener) {
        if (listener != null) {
            mStackChangedListeners.add(listener);
        }
    }

    @Override
    public void removeStackChangeListener(OnStackChangedListener listener) {
        if (listener != null) {
            mStackChangedListeners.remove(listener);
        }
    }

    /**
     * 出栈
     *
     * @param fragment     要出栈的fragment
     * @param isRemoveSelf 是否将自己也删除 如果是false 则仅将自己上面的全部出栈
     * @return
     */
    private int pop(BaseFragment fragment, boolean isRemoveSelf) {

        if (fragments.isEmpty() || fragment == null || !fragments.contains(fragment)) {
            return 0;
        }

        int count = 0;
        FragmentTransaction trans = fragmentManager.beginTransaction();
        for (int i = fragments.size() - 1; i >= 0; i--) {
            if (fragments.get(i) != fragment) {
                remove(trans, fragments.get(i));
                count++;
            } else {
                if (isRemoveSelf) {
                    remove(trans, fragments.get(i));
                    count++;
                }
                break;
            }
        }
        commitShow(trans);
        return count;
    }

    /**
     * 悄悄地从回退栈中，移除一个Fragment，但是不会改变此Fragment之前的所有Fragment
     *
     * @param trans
     * @param baseFragment
     */
    private void remove(FragmentTransaction trans, BaseFragment baseFragment) {
        baseFragment.onHideBack();
        baseFragment.onExit();
        trans.remove(baseFragment);
        fragments.remove(baseFragment);

        for (OnStackChangedListener item : mStackChangedListeners) {
            item.onRemove(baseFragment);
        }
    }

    /**
     * 将fragment入栈并显示
     *
     * @param fragment
     */
    private void push(BaseFragment fragment) {
        FragmentTransaction trans = fragmentManager.beginTransaction();
        fragments.add(fragment);
        trans.add(R.id.container, fragment);
        commitShow(trans);

        for (OnStackChangedListener item : mStackChangedListeners) {
            item.onNewIn(fragment);
        }
    }

    /**
     * 自动显示/隐藏栈内的fragment, 只有顶部的显示,其余隐藏
     *
     * @param trans
     * @return current shown fragment
     */
    private BaseFragment autoShow(FragmentTransaction trans) {

        BaseFragment shownFragment = null;

        for (int i = fragments.size() - 1; i >= 0; i--) {
            BaseFragment fm = fragments.get(i);
            if (i == fragments.size() - 1) {
                trans.show(fm);
//                fm.setFmShow(true);
                shownFragment = fm;
            } else {
                trans.hide(fm);
                if (fm.isFmShow()) {
                    fm.onHideBack();
                }
                fm.setFmShow(false);
            }
        }

        return shownFragment;
    }


    /**
     * 通过 类 来找fragment对象
     *
     * @param fragmentClazz
     * @return
     */
    private FindStatckResult find(Class fragmentClazz) {

        if (fragments.isEmpty()) {
            return new FindStatckResult(null, false);
        }

        BaseFragment foundFragment = null;

        boolean autoRemove = true;

        for (int i = fragments.size() - 1; i >= 0; i--) {

            BaseFragment currentStackFragment = fragments.get(i);

            if (fragmentClazz.equals(currentStackFragment.getClass())) {
                foundFragment = fragments.get(i);
                break;
            }
        }

        return new FindStatckResult(foundFragment, foundFragment != null);
    }

    /**
     * 提交显示，处理回调问题
     *
     * @param transaction
     */
    private void commitShow(FragmentTransaction transaction) {
        final BaseFragment currentShowFragment = autoShow(transaction);
        transaction.commitAllowingStateLoss();
        if (currentShowFragment == null) {
            return;
        }
        mHandler.post(new FragmentEnterTask(currentShowFragment, mBundleMap));
    }

    /**
     * 发送
     *
     * @param clazz
     * @param message
     */
    private void setMessageToFragment(Class clazz, Bundle message) {
        mBundleMap.put(clazz, message);
    }
    //endregion 栈管理

    private static class FragmentEnterTask implements Runnable {

        BaseFragment mShowFragment;
        HashMap<Class, Bundle> mDataMap;

        public FragmentEnterTask(BaseFragment showFragment, HashMap<Class, Bundle> dataMap) {
            this.mShowFragment = showFragment;
            this.mDataMap = dataMap;
        }

        @Override
        public void run() {

            boolean isFirstEnter = mShowFragment.isFirstEnter();

            if (mShowFragment != null) {
                if (mShowFragment.getContext() == null) {
                    return;
                }
                mShowFragment.onEnter();

//                if (!mShowFragment.isFmShow()) {
                if (!isFirstEnter) {
                    mShowFragment.onReEnter(mDataMap.get(mShowFragment.getClass()));
                    mDataMap.remove(mShowFragment.getClass());
                }
//                }

                mShowFragment.setFmShow(true);
            }
        }
    }
}
