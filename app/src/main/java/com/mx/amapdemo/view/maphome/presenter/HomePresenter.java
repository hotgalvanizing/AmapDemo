package com.mx.amapdemo.view.maphome.presenter;

import com.mx.amapdemo.base.BasePresenter;
import com.mx.amapdemo.base.IView;
import com.mx.amapdemo.model.IMapControlModel;
import com.mx.amapdemo.model.MapControlModel;
import com.mx.amapdemo.view.maphome.bean.HomeBean;

/**
 * 进入homefragment 地图定位到自身位置，持续定位，并且可以拖动地图
 */

public class HomePresenter extends BasePresenter<HomeBean> implements IHomePresenter {


    IMapControlModel mMapControlModel;

    public HomePresenter(IView<HomeBean> view) {
        super(view, new HomeBean());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mMapControlModel = MapControlModel.getInstance();
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();

    }

    @Override
    public void initSetMap() {
        mMapControlModel.initSetMap();
    }

    @Override
    public void backMe() {
        mMapControlModel.backMe();
    }

    @Override
    public void zoomIn() {
        mMapControlModel.zoomIn();
    }

    @Override
    public void zoomOut() {
        mMapControlModel.zoomOut();
    }
}
