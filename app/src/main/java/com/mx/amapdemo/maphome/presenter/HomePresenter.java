package com.mx.amapdemo.maphome.presenter;

import com.mx.amapdemo.base.BasePresenter;
import com.mx.amapdemo.base.IView;
import com.mx.amapdemo.maphome.bean.HomeBean;

public class HomePresenter extends BasePresenter<HomeBean> implements IHomePresenter {

    public HomePresenter(IView<HomeBean> view) {
        super(view, new HomeBean());
    }
}
