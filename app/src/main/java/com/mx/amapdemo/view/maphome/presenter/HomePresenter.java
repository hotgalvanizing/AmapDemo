package com.mx.amapdemo.view.maphome.presenter;

import com.amap.api.maps.model.LatLng;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.mx.amapdemo.base.BasePresenter;
import com.mx.amapdemo.base.IView;
import com.mx.amapdemo.model.geo.GeoModel;
import com.mx.amapdemo.model.geo.IGeoModel;
import com.mx.amapdemo.model.geo.IGeoModelListener;
import com.mx.amapdemo.model.map.IMapControlListener;
import com.mx.amapdemo.model.map.IMapControlModel;
import com.mx.amapdemo.model.map.MapControlModel;
import com.mx.amapdemo.view.maphome.bean.HomeBean;

/**
 * 进入homefragment 地图定位到自身位置，持续定位，并且可以拖动地图
 */

public class HomePresenter extends BasePresenter<HomeBean> implements IHomePresenter, IMapControlListener, IGeoModelListener {


    IMapControlModel mMapControlModel;
    IGeoModel mGeoModel;

    public HomePresenter(IView<HomeBean> view) {
        super(view, new HomeBean());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mMapControlModel = MapControlModel.getInstance();
        mGeoModel = new GeoModel();
        mGeoModel.setGeoListener(this);
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

    @Override
    public void pickUp(LatLng latLng) {
        mGeoModel.reverseGeo(latLng);
    }


    @Override
    public void onZoomChanged() {

    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        pickUp(latLng);
    }

    @Override
    public void onRegeocodeSearched(RegeocodeResult var1, int var2) {

    }

    @Override
    public void onGeocodeSearched(GeocodeResult var1, int var2) {

    }
}
