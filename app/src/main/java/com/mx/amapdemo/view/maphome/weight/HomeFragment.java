package com.mx.amapdemo.view.maphome.weight;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import com.amap.api.maps.model.LatLng;
import com.mx.amapdemo.R;
import com.mx.amapdemo.base.BaseMvpFragment;
import com.mx.amapdemo.base.IView;
import com.mx.amapdemo.model.map.MapControlModel;
import com.mx.amapdemo.view.maphome.bean.HomeBean;
import com.mx.amapdemo.view.maphome.presenter.HomePresenter;
import com.mx.amapdemo.view.maphome.presenter.IHomePresenter;

/**
 * TODO 检索入口，地图图层切换，地图拾取
 */
public class HomeFragment extends BaseMvpFragment<IHomePresenter> implements IView<HomeBean>,ZoomView.IZoomListener{

    ImageView mLocationIv;
    ZoomView mZoomView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected IHomePresenter onCreatePresenter() {
        return new HomePresenter(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getPresenter().initSetMap();
    }

    @Override
    public void onRefresh(HomeBean data) {

    }

    @Override
    protected void onBindView() {
        super.onBindView();
        mLocationIv = findView(R.id.btn_location);
        mZoomView = findView(R.id.btn_zoom);
    }

    @Override
    protected void onBindListener() {
        super.onBindListener();
        bindDefaultClickListener(mLocationIv);
        mZoomView.setZoomListener(this);
    }

    @Override
    protected void processClick(View v) {
        super.processClick(v);
        switch (v.getId()) {
            case R.id.btn_location:
                getPresenter().backMe();
                break;
            default:
                break;
        }
    }

    @Override
    public void onZoomIn(View view) {
        getPresenter().zoomIn();
    }

    @Override
    public void onZoomOut(View view) {
        getPresenter().zoomOut();
    }

}
