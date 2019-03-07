package com.mx.amapdemo.maphome.view;

import com.mx.amapdemo.R;
import com.mx.amapdemo.base.BaseMvpFragment;
import com.mx.amapdemo.base.IView;
import com.mx.amapdemo.maphome.bean.HomeBean;
import com.mx.amapdemo.maphome.presenter.HomePresenter;
import com.mx.amapdemo.maphome.presenter.IHomePresenter;

/**
 */
public class HomeFragment extends BaseMvpFragment<IHomePresenter> implements IView<HomeBean> {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected IHomePresenter onCreatePresenter() {
        return new HomePresenter(this);
    }

    @Override
    public void onRefresh(HomeBean data) {

    }
}
