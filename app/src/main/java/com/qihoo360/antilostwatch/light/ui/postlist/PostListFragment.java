package com.qihoo360.antilostwatch.light.ui.postlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.qihoo360.antilostwatch.light.R;
import com.qihoo360.antilostwatch.light.base.BaseFragment;
import com.qihoo360.antilostwatch.light.data.model.Post;

import java.util.List;

/**
 * Created by HuirongZhang on 2016/10/26.
 */

public class PostListFragment extends BaseFragment<PostListContract.Presenter> implements PostListContract.View {
    private ListView mPostLV;
    private PostListAdapter mAdapter;

    public static PostListFragment newInstance() {
        return new PostListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.post_fragment, container, false);
        mPostLV = (ListView) root.findViewById(R.id.post_lv);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAdapter = new PostListAdapter();
        mPostLV.setAdapter(mAdapter);
        mPresenter.loadPostList();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onPostListLoaded(List<Post> postList) {
        mAdapter.notifyDataSetChanged();
    }
}
