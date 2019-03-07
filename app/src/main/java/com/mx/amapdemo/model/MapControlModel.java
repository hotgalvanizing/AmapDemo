package com.mx.amapdemo.model;

import com.amap.api.maps.AMap;
import com.mx.amapdemo.AMapFactory;

public class MapControlModel implements IMapControlModel {
//myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_SHOW);
//    //只定位一次。

//myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE) ;
////定位一次，且将视角移动到地图中心点。

//myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW) ;
////连续定位、且将视角移动到地图中心点，定位蓝点跟随设备移动。（1秒1次定位）

//myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_MAP_ROTATE);
////连续定位、且将视角移动到地图中心点，地图依照设备方向旋转，定位点会跟随设备移动。（1秒1次定位）

//myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);
////连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）默认执行此种模式。

////以下三种模式从5.1.0版本开始提供
//myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE_NO_CENTER);
////连续定位、蓝点不会移动到地图中心点，定位点依照设备方向旋转，并且蓝点会跟随设备移动。
//myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW_NO_CENTER);
////连续定位、蓝点不会移动到地图中心点，并且蓝点会跟随设备移动。
//myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_MAP_ROTATE_NO_CENTER);
///连续定位、蓝点不会移动到地图中心点，地图依照设备方向旋转，并且蓝点会跟随设备移动。

    AMap aMap;

    public MapControlModel() {
        aMap = AMapFactory.getInstance().getmAMap();
    }


}
