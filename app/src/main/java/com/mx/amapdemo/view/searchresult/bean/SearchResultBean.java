package com.mx.amapdemo.view.searchresult.bean;

import com.amap.api.services.route.WalkRouteResult;
import com.mx.amapdemo.base.ObserveValue;

public class SearchResultBean {

    private ObserveValue<WalkRouteResult> walkRouteResult = new ObserveValue<>(null);

    public void updateRegeocodeResult(WalkRouteResult walkRouteResult) {
        this.walkRouteResult.update(walkRouteResult);
    }

    public WalkRouteResult getWalkRouteResult() {
        return this.walkRouteResult.pop();
    }

    public boolean isWalkRouteResultUpdated() {
        return this.walkRouteResult.hasUpdated();
    }
}
