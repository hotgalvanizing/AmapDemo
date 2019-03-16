package com.mx.amapdemo.view.searchresult;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import com.mx.amapdemo.R;
import com.mx.amapdemo.base.BaseMvpFragment;
import com.mx.amapdemo.base.IView;
import com.mx.amapdemo.utils.Constants;
import com.mx.amapdemo.view.searchresult.bean.SearchResultBean;
import com.mx.amapdemo.view.searchresult.presenter.ISearchResultPresenter;
import com.mx.amapdemo.view.searchresult.presenter.SearchResultPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public abstract class SearchResultFragment extends BaseMvpFragment<ISearchResultPresenter> implements IView<SearchResultBean> {

    boolean isNeedList;

    /**
     * 返回
     */
    protected ImageView ivBack;

    /**
     * 去这里
     */
    protected Button btnGoHere;

    /**
     * 选中索引
     */
    protected int mSelectPosition = 0;

    /**
     * 搜索列表
     */
    protected ListView lvData;

    /**
     * 详情页
     */
    protected View viewDetail;

    /**
     * 传递过来的bundle
     */
    protected Bundle bundle;
    /**
     * 下拉刷新，和上拉加载
     */
    protected SmartRefreshLayout smartRefreshLayout;

    /**
     * 是否是第一页
     */
    protected boolean isFirstPage = true;

    /**
     * 是否是最后一页
     */
    protected boolean isLastPage;

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

    @Override
    protected void onBindView() {
        super.onBindView();
        ivBack = findView(R.id.iv_back);
        lvData = findView(R.id.lv_data);
        smartRefreshLayout = findView(R.id.smart_refresh_layout);
        viewDetail = findView(R.id.view_detail);
    }

    @Override
    protected void initData(Bundle data) {
        super.initData(data);
        getBundle(data);
    }

    @Override
    protected void onViewBindValue() {
        super.onViewBindValue();
        handleBundleData();
    }

    protected void handleBundleData(){

    }

    protected void getBundle(Bundle data) {
        if (data == null) {
            return;
        }
        isNeedList = data.getBoolean(Constants.SearchResult.BUNDLE_IS_NEED_LIST);
    }

    @Override
    public void onReEnter(Bundle bundle) {
        super.onReEnter(bundle);
        getBundle(bundle);
        handleBundleData();
    }

    /**
     * BaseSearchResultFragment内部类
     * 用builder代替new Instance（）避免
     * 1.因参数过多造成构造方法臃肿
     * 2.用不到的参数还要传null
     */
    public static class Builder {

        protected final Bundle bundle;

        public Builder() {
            bundle = new Bundle();
        }

        /**
         * 是否需要列表
         *
         * @param isNeedList
         * @return
         */
        public SearchResultFragment.Builder setIsNeedList(boolean isNeedList) {
            bundle.putBoolean(Constants.SearchResult.BUNDLE_IS_NEED_LIST, isNeedList);
            return this;
        }

        /**
         * poi列表
         *
         * @param
         * @return
         */
        public SearchResultFragment.Builder setList(/*List<MXPoi> list*/) {
//            bundle.putParcelableArrayList(BUNDLE_KEY_POI_LIST, Lists.newArrayList(list));
            return this;
        }

        /**
         * poi,表示点击的目标点
         *
         * @param
         * @return
         */
        public SearchResultFragment.Builder setPoi(/*MXPoi mxPoi*/) {
//            bundle.putParcelable(SearchResultConstants.BUNDLE_KEY_POI, mxPoi);
            return this;
        }

        /**
         * 是否是最后一页
         *
         * @param isLastPage
         * @return
         */
        public SearchResultFragment.Builder setIsLastPage(boolean isLastPage) {
//            bundle.putBoolean(SearchResultConstants.BUNDLE_RESULT_IS_LAST_PAGE, isLastPage);
            return this;
        }

        public SearchResultFragment newInstance() {
            SearchResultFragment fragment;
            if ((bundle.getBoolean(Constants.SearchResult.BUNDLE_IS_NEED_LIST))) {
                fragment = new SearchResultHaveListFragment();
            } else {
                fragment = new SearchResultHaveNoListFragment();
            }
            fragment.setArguments(bundle);
            return fragment;
        }
    }
}
