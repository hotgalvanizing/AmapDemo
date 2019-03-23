package com.mx.amapdemo.view.searchresult.presenter;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import com.amap.api.location.AMapLocation;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.*;
import com.mx.amapdemo.App;
import com.mx.amapdemo.base.BasePresenter;
import com.mx.amapdemo.base.IView;
import com.mx.amapdemo.model.location.ILocationListener;
import com.mx.amapdemo.model.location.LocationManager;
import com.mx.amapdemo.model.map.MapControlModel;
import com.mx.amapdemo.model.overlay.WalkRouteOverlay;
import com.mx.amapdemo.model.routeplan.IRoutePlanListener;
import com.mx.amapdemo.model.routeplan.IRoutePlanModel;
import com.mx.amapdemo.model.routeplan.RoutePlanModel;
import com.mx.amapdemo.view.searchresult.bean.SearchResultBean;

public class SearchResultPresenter extends BasePresenter<SearchResultBean> implements ISearchResultPresenter {

    IRoutePlanModel mRoutePlanModel;
    LatLonPoint mLatLonPoint;

    public SearchResultPresenter(IView<SearchResultBean> view) {
        super(view, new SearchResultBean());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mRoutePlanModel = new RoutePlanModel(mRoutePlanListener);
    }

    @Override
    public void getLocation(LatLonPoint latLonPoint) {
        LocationManager.get().addLocationListener(mLocationListener);
        mLatLonPoint = latLonPoint;
    }

    @Override
    public void routePlan(LatLonPoint start, LatLonPoint dest) {
        mRoutePlanModel.routePlan(start, dest);
    }


    ILocationListener mLocationListener = new ILocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            routePlan(mLatLonPoint,new LatLonPoint(aMapLocation.getLatitude(),aMapLocation.getLongitude()));
            LocationManager.get().removeLocationListener(mLocationListener);
        }
    };

    IRoutePlanListener mRoutePlanListener = new IRoutePlanListener() {
        @Override
        public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {
            Log.d("haha","haha");

            refresh();
        }

        @Override
        public void onDriveRouteSearched(DriveRouteResult driveRouteResult, int i) {
            Log.d("haha","haha");

            refresh();
        }

        @Override
        public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int errorCode) {

            if (errorCode == AMapException.CODE_AMAP_SUCCESS) {
                if (walkRouteResult != null && walkRouteResult.getPaths() != null) {
                    if (walkRouteResult.getPaths().size() > 0) {

                        getData().updateRegeocodeResult(walkRouteResult);

                    } else if (walkRouteResult != null && walkRouteResult.getPaths() == null) {
                        //TODO toast
                    }
                } else {
                    //TODO toast
                }
            } else {
                //TODO toast
            }
            refresh();
        }

        @Override
        public void onRideRouteSearched(RideRouteResult rideRouteResult, int i) {
            Log.d("haha","haha");

            refresh();
        }
    };
}
