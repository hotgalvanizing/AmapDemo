package com.mx.amapdemo.view.multiroute.weight;

import android.os.Bundle;
import com.amap.api.services.route.WalkPath;
import com.amap.api.services.route.WalkRouteResult;
import com.mx.amapdemo.App;
import com.mx.amapdemo.R;
import com.mx.amapdemo.base.BaseMvpFragment;
import com.mx.amapdemo.base.IView;
import com.mx.amapdemo.model.map.MapControlModel;
import com.mx.amapdemo.model.overlay.WalkRouteOverlay;
import com.mx.amapdemo.utils.Constants;
import com.mx.amapdemo.view.multiroute.bean.Multiroutebean;
import com.mx.amapdemo.view.multiroute.presenter.IMultiroutePresenter;
import com.mx.amapdemo.view.multiroute.presenter.MultiroutePresenter;

public class MultiRouteFragment extends BaseMvpFragment<IMultiroutePresenter> implements IView<Multiroutebean> {

    WalkRouteResult mWalkRouteResult;

    public static MultiRouteFragment newInstance(WalkRouteResult walkRouteResult) {
        Bundle args = new Bundle();
        args.putParcelable(Constants.MultiRoute.BUNDLE_KEY_WALK_RESULT, walkRouteResult);
        MultiRouteFragment fragment = new MultiRouteFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initData(Bundle data) {
        super.initData(data);
        mWalkRouteResult = data.getParcelable(Constants.MultiRoute.BUNDLE_KEY_WALK_RESULT);
    }

    @Override
    protected IMultiroutePresenter onCreatePresenter() {
        return new MultiroutePresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mulri_route_layout;
    }

    @Override
    public void onBindView() {

    }

    @Override
    public void onBindListener() {

    }

    @Override
    public void onViewBindValue() {
        final WalkPath walkPath = mWalkRouteResult.getPaths().get(0);
        if (walkPath == null) {
            return;
        }
        WalkRouteOverlay walkRouteOverlay = new WalkRouteOverlay(
                App.getmContext(), MapControlModel.getInstance().getmAMap(), walkPath,
                mWalkRouteResult.getStartPos(),
                mWalkRouteResult.getTargetPos());
        walkRouteOverlay.removeFromMap();
        walkRouteOverlay.addToMap();
        walkRouteOverlay.zoomToSpan();

    }

    @Override
    public void onRefresh(Multiroutebean data) {

    }
}
