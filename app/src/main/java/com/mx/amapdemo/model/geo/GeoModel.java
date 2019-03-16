package com.mx.amapdemo.model.geo;

import com.amap.api.maps.model.LatLng;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.mx.amapdemo.App;

public class GeoModel implements IGeoModel {

    IGeoModelListener mGeoModelListener = null;

    public GeoModel() {

    }

    @Override
    public void setGeoListener(IGeoModelListener listener) {
        mGeoModelListener = listener;
    }

    @Override
    public void reverseGeo(LatLng latLng) {
        GeocodeSearch geocoderSearch = new GeocodeSearch(App.getmContext());
        geocoderSearch.setOnGeocodeSearchListener(new GeocodeSearch.OnGeocodeSearchListener() {
            @Override
            public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
                mGeoModelListener.onRegeocodeSearched(regeocodeResult, i);
            }

            @Override
            public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
                mGeoModelListener.onGeocodeSearched(geocodeResult, i);
            }
        });
        LatLonPoint latLonPoint = new LatLonPoint(latLng.latitude, latLng.longitude);
        RegeocodeQuery query = new RegeocodeQuery(latLonPoint, 200, GeocodeSearch.AMAP);
        geocoderSearch.getFromLocationAsyn(query);
    }
}
