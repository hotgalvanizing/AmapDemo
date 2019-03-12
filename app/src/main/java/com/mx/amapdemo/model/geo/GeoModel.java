package com.mx.amapdemo.model.geo;

import com.amap.api.maps.model.LatLng;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.mx.amapdemo.App;

public class GeoModel implements IGeoModel, GeocodeSearch.OnGeocodeSearchListener {

    IGeoModelListener mGeoModelListener;

    @Override
    public void setGeoListener(IGeoModelListener listener) {
        mGeoModelListener = listener;
    }

    @Override
    public void reverseGeo(LatLng latLng) {

        GeocodeSearch geocoderSearch = new GeocodeSearch(App.getmContext());
        geocoderSearch.setOnGeocodeSearchListener(this);
        LatLonPoint latLonPoint = new LatLonPoint(latLng.latitude, latLng.longitude);
        RegeocodeQuery query = new RegeocodeQuery(latLonPoint, 200, GeocodeSearch.AMAP);
        geocoderSearch.getFromLocationAsyn(query);

    }

    @Override
    public void onRegeocodeSearched(RegeocodeResult var1, int var2) {

    }

    @Override
    public void onGeocodeSearched(GeocodeResult var1, int var2) {

    }
}
