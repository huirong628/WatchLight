package com.qihoo360.antilostwatch.light.ui.postlist;

import android.view.View;
import android.widget.ListView;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.qihoo360.antilostwatch.light.R;
import com.qihoo360.antilostwatch.light.base.BaseFragment;
import com.qihoo360.antilostwatch.light.mode.bean.PostBean;
import com.qihoo360.antilostwatch.light.mode.bean.PostList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuirongZhang on 2016/10/26.
 */

public class PostListFragment extends BaseFragment<PostListContract.Presenter> implements PostListContract.View, OnRefreshListener, OnLoadMoreListener {
    private SwipeToLoadLayout mSwipeToLoadLayout;
    private PostListAdapter mAdapter;
    private List<PostBean> mPostList = new ArrayList<>();

    public static PostListFragment newInstance() {
        return new PostListFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.post_fragment;
    }

    @Override
    public void initView() {
        mSwipeToLoadLayout = (SwipeToLoadLayout) mContentView.findViewById(R.id.swipeToLoadLayout);
        ListView listView = (ListView) mContentView.findViewById(R.id.swipe_target);

        mSwipeToLoadLayout.setOnRefreshListener(this);

        mSwipeToLoadLayout.setOnLoadMoreListener(this);

        mAdapter = new PostListAdapter(mContext);

        listView.setAdapter(mAdapter);

        mSwipeToLoadLayout.setRefreshing(true);

        mPresenter.loadPostList();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onPostListLoaded(PostList postList) {
        mPostList.clear();
        mPostList.addAll(postList.getPostList());
        mAdapter.setData(mPostList);
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 有异常时没有抛出来
     *
     * @param postList
     */
    @Override
    public void onMorePostListLoaded(PostList postList) {
        System.out.println("onMorePostListLoaded()");
        List<PostBean> postBeanList = postList.getPostList();
        if (postBeanList.size() > 0) {
            mPostList.addAll(postBeanList);
            mAdapter.setData(mPostList);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onRefresh() {
        System.out.println("onRefresh()");
        mPresenter.loadPostList();
    }

    @Override
    public void onRefreshComplete() {
        mSwipeToLoadLayout.setRefreshing(false);
    }

    @Override
    public void onLoadMore() {
        System.out.println("onLoadMore()");
        mPresenter.loadMorePostList(mPostList.get(mPostList.size() - 1).getId());
    }

    @Override
    public void onLoadMoreComplete() {
        mSwipeToLoadLayout.setLoadingMore(false);
    }
}
