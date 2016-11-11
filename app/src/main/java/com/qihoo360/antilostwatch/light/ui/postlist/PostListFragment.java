package com.qihoo360.antilostwatch.light.ui.postlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new PostListAdapter(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.post_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSwipeToLoadLayout = (SwipeToLoadLayout) view.findViewById(R.id.swipeToLoadLayout);
        ListView listView = (ListView) view.findViewById(R.id.swipe_target);

        mSwipeToLoadLayout.setOnRefreshListener(this);

        mSwipeToLoadLayout.setOnLoadMoreListener(this);

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
