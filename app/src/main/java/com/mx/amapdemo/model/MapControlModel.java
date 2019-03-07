package com.mx.amapdemo.model;

import com.amap.api.maps.AMap;
import com.amap.api.maps.model.MyLocationStyle;
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


    @Override
    public void focusMe() {
        MyLocationStyle myLocationStyle;

        //初始化定位蓝点样式类
        myLocationStyle = new MyLocationStyle();
        //定位一次，且将视角移动到地图中心点。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);
        //设置定位蓝点的Style
        aMap.setMyLocationStyle(myLocationStyle);
        //设置默认定位按钮是否显示，非必需设置。
        aMap.getUiSettings().setMyLocationButtonEnabled(true);
        // 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        aMap.setMyLocationEnabled(true);

//        //连续定位、蓝点不会移动到地图中心点，定位点依照设备方向旋转，并且蓝点会跟随设备移动。
//        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE_NO_CENTER);
//        //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
//        myLocationStyle.interval(2000);
//        //设置定位蓝点的Style
//        aMap.setMyLocationStyle(myLocationStyle);
    }
}
