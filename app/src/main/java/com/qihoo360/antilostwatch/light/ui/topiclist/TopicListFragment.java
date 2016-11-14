package com.qihoo360.antilostwatch.light.ui.topiclist;

import android.view.View;
import android.widget.ListView;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.qihoo360.antilostwatch.light.R;
import com.qihoo360.antilostwatch.light.base.BaseFragment;
import com.qihoo360.antilostwatch.light.mode.bean.TopicBean;
import com.qihoo360.antilostwatch.light.mode.bean.TopicList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuirongZhang on 2016/11/2.
 */

public class TopicListFragment extends BaseFragment<TopicListContract.Presenter> implements TopicListContract.View, OnRefreshListener, OnLoadMoreListener {
    private static TopicListFragment mInstance = null;
    private SwipeToLoadLayout mSwipeToLoadLayout;
    private TopicListAdapter mAdapter;
    private List<TopicBean> mTopicList = new ArrayList<>();

    public static TopicListFragment getInstance() {
        if (mInstance == null) {
            mInstance = new TopicListFragment();
        }
        return mInstance;
    }

    @Override
    public int getLayoutId() {
        return R.layout.topic_fragment;
    }

    @Override
    public void initView(View view) {
        mSwipeToLoadLayout = (SwipeToLoadLayout) view.findViewById(R.id.swipeToLoadLayout);
        ListView listView = (ListView) view.findViewById(R.id.swipe_target);

        mSwipeToLoadLayout.setOnRefreshListener(this);

        mSwipeToLoadLayout.setOnLoadMoreListener(this);

        mAdapter = new TopicListAdapter(mContext);

        listView.setAdapter(mAdapter);

        mSwipeToLoadLayout.setRefreshing(true);

        mPresenter.loadTopicList();
    }

    @Override
    public void onTopicListLoaded(TopicList topicList) {
        mTopicList.clear();
        mTopicList.addAll(topicList.getHotTopicList());
        mAdapter.setData(mTopicList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRefreshComplete() {
        mSwipeToLoadLayout.setRefreshing(false);
    }

    @Override
    public void onLoadMoreComplete() {

    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onRefresh() {
        mPresenter.loadTopicList();
    }
}