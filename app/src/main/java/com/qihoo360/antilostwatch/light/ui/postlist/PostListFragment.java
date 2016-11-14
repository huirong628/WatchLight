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
    private static PostListFragment mInstance = null;
    private SwipeToLoadLayout mSwipeToLoadLayout;
    private PostListAdapter mAdapter;
    private List<PostBean> mPostList = new ArrayList<>();

    /**
     * 单例模式可以保证系统中一个类只有一个实例.
     * <p>
     * 实例存在多个会引起程序逻辑错误的时候
     * Do not place Android context classes in static fields (static reference to PostListFragment which has field mContext pointing to Context);
     * this is a memory leak (and also breaks Instant Run)
     * @return
     */
    public static PostListFragment newInstance() {
        if (mInstance == null) {
            mInstance = new PostListFragment();
        }
        return mInstance;
    }

    @Override
    public int getLayoutId() {
        return R.layout.post_fragment;
    }

    @Override
    public void initView(View view) {
        mSwipeToLoadLayout = (SwipeToLoadLayout) view.findViewById(R.id.swipeToLoadLayout);
        ListView listView = (ListView) view.findViewById(R.id.swipe_target);

        mSwipeToLoadLayout.setOnRefreshListener(this);

        mSwipeToLoadLayout.setOnLoadMoreListener(this);

        mAdapter = new PostListAdapter(mContext);

        listView.setAdapter(mAdapter);

        mSwipeToLoadLayout.setRefreshing(true);

        mPresenter.loadPostList();
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
