package com.mx.amapdemo.model.map;

import com.amap.api.maps.model.LatLng;

public interface IMapControlListener {

    void onZoomChanged();

    void onMapLongClick(LatLng latLng);
}
