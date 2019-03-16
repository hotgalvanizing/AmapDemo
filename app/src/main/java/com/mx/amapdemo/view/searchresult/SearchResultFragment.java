package com.mx.amapdemo.view.searchresult;

import com.mx.amapdemo.R;
import com.mx.amapdemo.base.BaseMvpFragment;
import com.mx.amapdemo.base.IView;
import com.mx.amapdemo.view.searchresult.bean.SearchResultBean;
import com.mx.amapdemo.view.searchresult.presenter.ISearchResultPresenter;
import com.mx.amapdemo.view.searchresult.presenter.SearchResultPresenter;

public abstract class SearchResultFragment extends BaseMvpFragment<ISearchResultPresenter> implements IView<SearchResultBean> {

    @Override
    protected ISearchResultPresenter onCreatePresenter() {
        return new SearchResultPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search_result_layout;
    }

    @Override
    public void onRefresh(SearchResultBean data) {

    }


}
