package com.mx.amapdemo.view.searchresult.presenter;

import com.amap.api.services.core.LatLonPoint;
import com.mx.amapdemo.base.ILifeCycle;

public interface ISearchResultPresenter extends ILifeCycle {

    void getLocation(LatLonPoint latLonPoint);

    void routePlan(LatLonPoint start, LatLonPoint dest);

}
