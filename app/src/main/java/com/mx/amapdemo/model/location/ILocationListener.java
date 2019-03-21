package com.mx.amapdemo.model.location;

import com.amap.api.location.AMapLocation; /**
 * @author: lhe
 * @date: 2019/03/21
 * @desctiption: xxxx
 */
public interface ILocationListener {


    void onLocationChanged(AMapLocation aMapLocation);
}
