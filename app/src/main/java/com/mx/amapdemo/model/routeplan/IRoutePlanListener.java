package com.mx.amapdemo.model.routeplan;

import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.WalkRouteResult;

/**
 * @author: lhe
 * @date: 2019/03/21
 * @desctiption: xxxx
 */
public interface IRoutePlanListener {

    public void onBusRouteSearched(BusRouteResult busRouteResult, int i) ;


    public void onDriveRouteSearched(DriveRouteResult driveRouteResult, int i) ;

    public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i) ;

    public void onRideRouteSearched(RideRouteResult rideRouteResult, int i) ;


}
