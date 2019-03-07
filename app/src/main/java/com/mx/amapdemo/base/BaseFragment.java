package com.mx.amapdemo.base;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mx.amapdemo.MainActivity;


/**
 * 基类 Fragment
 *
 * @author peix
 * @date 2017/11/24
 */

public abstract class BaseFragment extends BaseStateManageFragment implements  View.OnClickListener, IFragmentStackManager<BaseFragment> {
    /**
     * 根布局
     */
    protected View mView = null;
    /**
     * 长期持有的Activity
     */
    private MainActivity mAttachActivity;
    /**
     * 通用handler
     */
    private Handler mUIHandler;
    /**
     * 所有注册的view
     */
    private SparseArray<View> mViewCache;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            mAttachActivity = (MainActivity) context;
        } else {
            throw new RuntimeException("You Use me in activity that not include in MainActivity");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewCache = new SparseArray<>();
        initData(getArguments());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onBindView();
        onViewBindValue();
        onStyleUpdated();
        onBindListener();
    }

    private void onStyleUpdated() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 优化 View 的 inflate 过程，保证只执行一次。
        if (mView == null) {
            isFirstEnter = true;
            //by liumy@meixing.com 一定要用三个参数的方法来构造view 不然布局文件的根节点所设置宽高属性将无效！！！
            View view = inflater.inflate(getLayoutId(), container, false);
            if (view != null) {
                mView = view;
            }
        }
        // 如果 mView == null 并且 onCreateFragmentContent 方法也返回 null
        mView = mView == null ? super.onCreateView(inflater, container, savedInstanceState) : mView;
        return mView;
    }

    @Override
    public void onReEnter(Bundle args) {
        super.onReEnter(args);
        onStyleUpdated();
    }

    /**
     * 初始化数据
     */
    protected void initData(Bundle data) {
    }

    /**
     * 设置布局文件
     *
     * @return 布局资源id
     */
    protected abstract @LayoutRes
    int getLayoutId();

    /**
     * 关联控件
     */
    protected void onBindView() {
    }

    /**
     * 关联控件的监听器事件
     */
    protected void onBindListener() {
    }

    /**
     * 关联控件的属性设置
     */
    protected void onViewBindValue() {

    }

    /**
     * 点击事件回调处理
     *
     * @param v view
     */
    protected void processClick(View v) {
    }

    @Override
    public void onClick(View view) {
        processClick(view);
    }

    /**
     * 绑定默认的点击事件，点击事件的处理交给{@link #processClick(View)}进行处理
     *
     * @param view view
     * @param <E>  类型
     */
    public <E extends View> void bindDefaultClickListener(E view) {
        view.setOnClickListener(this);
    }

    /**
     * 获取基础的handler
     */
    Handler getHandler() {
        if (mUIHandler == null) {
            mUIHandler = new Handler(Looper.getMainLooper());
        }
        return mUIHandler;
    }

    /**
     * 获取Activity避免空指针
     *
     * @return
     */
    public MainActivity getMainActivity() {
        return mAttachActivity;
    }

    /**
     * 根据id查找绑定view
     *
     * @param viewId 控件id
     * @param <E>    类型
     * @return view
     */
    public <E extends View> E findView(int viewId) {
        if (mView != null) {
            E view = (E) mViewCache.get(viewId);
            if (view == null) {
                view = mView.findViewById(viewId);
                mViewCache.put(viewId, view);
            }
            return view;
        }
        return null;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public void go(BaseFragment fragment) {
        getMainActivity().getStackManager().go(fragment);
    }

    @Override
    public void remove(BaseFragment fragment) {
        getMainActivity().getStackManager().remove(fragment);
    }

    @Override
    public void backTo(Class<? extends BaseFragment> clazz, Bundle data) {
        getMainActivity().getStackManager().backTo(clazz, data);
    }

    @Override
    public void addStackChangeListener(OnStackChangedListener listener) {
        getMainActivity().getStackManager().addStackChangeListener(listener);
    }

    @Override
    public void removeStackChangeListener(OnStackChangedListener listener) {
        getMainActivity().getStackManager().removeStackChangeListener(listener);
    }

    @Override
    public void back() {
        if (top() == this) {
            getMainActivity().getStackManager().back();
        }
    }

    @Override
    public BaseFragment top() {
        return getMainActivity().getStackManager().top();
    }

    @Override
    public int size() {
        return getMainActivity().getStackManager().size();
    }

}
