package com.mx.amapdemo.view.searchresult;

import android.view.View;

import com.amap.api.location.AMapLocation;
import com.mx.amapdemo.R;
import com.mx.amapdemo.model.location.ILocationListener;
import com.mx.amapdemo.model.location.LocationManager;

public class SearchResultHaveNoListFragment extends SearchResultFragment {

    @Override
    protected void handleBundleData() {
        super.handleBundleData();
        //TODO 获得需要的数据
        lvData.setVisibility(View.GONE);
        viewDetail.setVisibility(View.VISIBLE);
        smartRefreshLayout.setEnableLoadMore(false);
        smartRefreshLayout.setEnableRefresh(false);



        textView.setText(mRegeocodeAddress.getBuilding() + ";"+mRegeocodeAddress.getFormatAddress());

    }

    @Override
    protected void processClick(View v) {
        super.processClick(v);

        switch (v.getId()){

            case R.id.btn_searchresult_go_here:
                getPresenter().getLocation();
                break;
        }
    }
}
