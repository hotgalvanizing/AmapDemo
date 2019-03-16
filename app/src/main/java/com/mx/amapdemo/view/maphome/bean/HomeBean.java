package com.mx.amapdemo.view.maphome.bean;

import com.amap.api.services.geocoder.RegeocodeResult;
import com.mx.amapdemo.base.ObserveValue;

public class HomeBean {

    private ObserveValue<RegeocodeResult> regeocodeResult = new ObserveValue<>(null);

    public void updateRegeocodeResult(RegeocodeResult regeocodeResult) {
        this.regeocodeResult.update(regeocodeResult);
    }

    public RegeocodeResult getRegeocodeResult() {
        return this.regeocodeResult.pop();
    }

    public boolean isRegeocodeResultUpdated() {
        return this.regeocodeResult.hasUpdated();
    }

}
