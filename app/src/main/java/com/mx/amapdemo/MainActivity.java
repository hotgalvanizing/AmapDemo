package com.mx.amapdemo;

import android.os.Bundle;
import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.mx.amapdemo.base.BaseFragment;
import com.mx.amapdemo.base.BaseFragmentActivity;
import com.mx.amapdemo.base.IFragmentStackManager;
import com.mx.amapdemo.base.StackFragmentManager;
import com.mx.amapdemo.view.maphome.weight.HomeFragment;

public class MainActivity extends BaseFragmentActivity {

    MapView mMapView = null;
    //初始化地图控制器对象
    AMap aMap;

    private IFragmentStackManager<BaseFragment> mStackFragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mStackFragmentManager = new StackFragmentManager(this.getSupportFragmentManager(), this);
        setContentView(R.layout.activity_main);
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);

        if (aMap == null) {
            aMap = mMapView.getMap();
            AMapFactory.getInstance().setmAMap(aMap);
        }

        goHomeFragment();
    }

    private void goHomeFragment() {
        mStackFragmentManager.go(new HomeFragment());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mStackFragmentManager.top() != null) {
            BaseFragment fragment = mStackFragmentManager.top();
            boolean isfirst = fragment.isFirstEnter();
            fragment.onEnter();
            if (!isfirst) {
                fragment.onReEnter(null);
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mStackFragmentManager.top() != null) {
            mStackFragmentManager.top().onHideBack();
        }
    }

    public IFragmentStackManager<BaseFragment> getStackManager() {
        return mStackFragmentManager;
    }
}