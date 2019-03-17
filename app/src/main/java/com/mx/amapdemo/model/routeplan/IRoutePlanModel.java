package com.mx.amapdemo.model.routeplan;

import com.amap.api.services.core.LatLonPoint;

public interface IRoutePlanModel {

    void routePlan(LatLonPoint start, LatLonPoint dest);
}
