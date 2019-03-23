package com.mx.amapdemo.view.searchresult;

import android.view.View;
import com.mx.amapdemo.R;
import com.mx.amapdemo.view.multiroute.weight.MultiRouteFragment;
import com.mx.amapdemo.view.searchresult.bean.SearchResultBean;

public class SearchResultHaveNoListFragment extends SearchResultFragment {

    @Override
    protected void handleBundleData() {
        super.handleBundleData();
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
                getPresenter().getLocation(mLatLonPoint);
                break;
        }
    }

    @Override
    public void onRefresh(SearchResultBean data) {
        super.onRefresh(data);
        if (data.isWalkRouteResultUpdated()){
            go(MultiRouteFragment.newInstance(data.getWalkRouteResult()));
        }

    }
}
