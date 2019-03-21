package com.mx.amapdemo.view.searchresult.presenter;

import com.amap.api.location.AMapLocation;
import com.mx.amapdemo.base.BasePresenter;
import com.mx.amapdemo.base.IView;
import com.mx.amapdemo.model.location.ILocationListener;
import com.mx.amapdemo.model.location.LocationManager;
import com.mx.amapdemo.model.routeplan.IRoutePlanModel;
import com.mx.amapdemo.model.routeplan.RoutePlanModel;
import com.mx.amapdemo.view.searchresult.bean.SearchResultBean;

public class SearchResultPresenter extends BasePresenter<SearchResultBean> implements ISearchResultPresenter {

    IRoutePlanModel mRoutePlanModel;

    public SearchResultPresenter(IView<SearchResultBean> view) {
        super(view, new SearchResultBean());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mRoutePlanModel = new RoutePlanModel();
    }

    @Override
    public void getLocation() {
        LocationManager.get().addLocationListener(mLocationListener);
    }

    @Override
    public void routePlan() {
        mRoutePlanModel.routePlan();
    }


    ILocationListener mLocationListener = new ILocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {

        }
    };
}
