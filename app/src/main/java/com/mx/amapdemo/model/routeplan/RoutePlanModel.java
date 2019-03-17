package com.mx.amapdemo.model.routeplan;

import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.*;
import com.mx.amapdemo.App;

public class RoutePlanModel implements IRoutePlanModel {


    @Override
    public void routePlan(LatLonPoint start, LatLonPoint dest) {
        RouteSearch routeSearch = new RouteSearch(App.getmContext());
        routeSearch.setRouteSearchListener(routePlanListener);
        //初始化query对象，fromAndTo是包含起终点信息，walkMode是步行路径规划的模式
        RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(start, dest);
        RouteSearch.WalkRouteQuery query = new RouteSearch.WalkRouteQuery(fromAndTo, RouteSearch.WALK_MULTI_PATH);
        routeSearch.calculateWalkRouteAsyn(query);//开始算路
    }

    RouteSearch.OnRouteSearchListener routePlanListener = new RouteSearch.OnRouteSearchListener() {
        @Override
        public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {

        }

        @Override
        public void onDriveRouteSearched(DriveRouteResult driveRouteResult, int i) {

        }

        @Override
        public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i) {

        }

        @Override
        public void onRideRouteSearched(RideRouteResult rideRouteResult, int i) {

        }
    };

}
