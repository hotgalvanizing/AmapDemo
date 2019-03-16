package com.mx.amapdemo.view.searchresult.presenter;

import com.mx.amapdemo.base.BasePresenter;
import com.mx.amapdemo.base.IView;
import com.mx.amapdemo.view.searchresult.bean.SearchResultBean;

public class SearchResultPresenter extends BasePresenter<SearchResultBean> implements ISearchResultPresenter {

    public SearchResultPresenter(IView<SearchResultBean> view) {
        super(view, new SearchResultBean());
    }

}
