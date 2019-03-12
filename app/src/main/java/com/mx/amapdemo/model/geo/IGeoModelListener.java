package com.mx.amapdemo.model.geo;

import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.RegeocodeResult;

public interface IGeoModelListener {

    void onRegeocodeSearched(RegeocodeResult var1, int var2);

    void onGeocodeSearched(GeocodeResult var1, int var2);
}
