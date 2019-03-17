package com.mx.amapdemo.view.searchresult;

import android.view.View;

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
}
