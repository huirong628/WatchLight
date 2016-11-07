package com.qihoo360.antilostwatch.light.ui.postlist;

import com.qihoo360.antilostwatch.light.base.BaseCommonPresenter;
import com.qihoo360.antilostwatch.light.mode.biz.TalkBiz;


/**
 * Created by HuirongZhang on 2016/10/26.
 */

public class PostListPresenter extends BaseCommonPresenter<PostListFragment> implements PostListContract.Presenter {

    private TalkBiz mTalkBiz;

    public PostListPresenter(PostListFragment view) {
        super(view);
        mTalkBiz = new TalkBiz();
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public void loadPostList() {
        mTalkBiz.loadPostList();
    }

    @Override
    public void loadMorePostList() {
        mTalkBiz.loadMorePostList();
    }
}