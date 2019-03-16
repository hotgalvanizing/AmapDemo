package com.mx.amapdemo.model.map;

import android.view.MotionEvent;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MyLocationStyle;

import java.util.ArrayList;
import java.util.List;
/**
 * map设置为单例的，mapcontrolmodel也可以设置为单例的,观察者
 */
public class MapControlModel implements IMapControlModel {

    AMap aMap;
    public List<IMapControlListener> mapControlListeners = new ArrayList<>();

    MyLocationStyle myLocationStyle = new MyLocationStyle();

    private MapControlModel() {
    }

    public static MapControlModel getInstance() {
        return MapControlModel.a.a;
    }

    static class a {
        static final MapControlModel a = new MapControlModel();
    }

    public void setmAMap(AMap mAMap) {
        this.aMap = mAMap;
    }

    public AMap getmAMap() {
        return aMap;
    }

    @Override
    public void registerListener(IMapControlListener listener) {
        if (listener != null && !mapControlListeners.contains(listener)) {
            mapControlListeners.add(listener);
        }
    }

    @Override
    public void unregisterListener(IMapControlListener listener) {
        if (listener != null) {
            mapControlListeners.remove(listener);
        }
    }

    @Override
    public void initSetMap() {

        backMe();

        //控制比例尺控件是否显示
        aMap.getUiSettings().setScaleControlsEnabled(true);
        aMap.getUiSettings().setZoomControlsEnabled(false);

        // 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        aMap.setMyLocationEnabled(true);

        aMap.setOnMapTouchListener(new AMap.OnMapTouchListener() {
            @Override
            public void onTouch(MotionEvent motionEvent) {
                //滑动地图，地图就不要跟随了
                //地图拾取，地图也不要跟随了

                //用户拖动地图后，不再跟随移动，直到用户点击定位按钮
                //连续定位、蓝点不会移动到地图中心点，并且蓝点会跟随设备移动。
                myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW_NO_CENTER);
                //设置定位蓝点的Style
                aMap.setMyLocationStyle(myLocationStyle);
            }
        });

        aMap.setOnMapLongClickListener(new AMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                if (mapControlListeners != null && mapControlListeners.size() != 0) {
                    for (IMapControlListener item : mapControlListeners) {
                        item.onMapLongClick(latLng);
                    }
                }
            }
        });
    }

    @Override
    public void backMe() {
        ///连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);
        myLocationStyle.interval(2000);
        //设置定位蓝点的Style
        aMap.setMyLocationStyle(myLocationStyle);
    }

    @Override
    public void zoomIn() {

        CameraUpdate zoomIn = CameraUpdateFactory.zoomIn();
        aMap.animateCamera(zoomIn, new AMap.CancelableCallback() {
            @Override
            public void onFinish() {
                if (mapControlListeners != null && mapControlListeners.size() != 0) {
                    for (IMapControlListener item : mapControlListeners) {
                        item.onZoomChanged();
                    }
                }
            }

            @Override
            public void onCancel() {

            }
        });
    }

    @Override
    public void zoomOut() {

        CameraUpdate zoomOut = CameraUpdateFactory.zoomOut();
        aMap.animateCamera(zoomOut, new AMap.CancelableCallback() {
            @Override
            public void onFinish() {
                if (mapControlListeners != null && mapControlListeners.size() != 0) {
                    for (IMapControlListener item : mapControlListeners) {
                        item.onZoomChanged();
                    }
                }

            }

            @Override
            public void onCancel() {

            }
        });

    }

}
