package com.mx.amapdemo.view.multiroute.presenter;

import com.mx.amapdemo.base.BasePresenter;
import com.mx.amapdemo.base.IView;
import com.mx.amapdemo.view.multiroute.bean.Multiroutebean;

public class MultiroutePresenter extends BasePresenter<Multiroutebean> implements IMultiroutePresenter {

    public MultiroutePresenter(IView<Multiroutebean> view) {
        super(view, new Multiroutebean());
    }
}
