package com.qihoo360.antilostwatch.light.ui.postlist;

import com.qihoo360.antilostwatch.light.api.ApiObserver;
import com.qihoo360.antilostwatch.light.base.BaseCommonPresenter;
import com.qihoo360.antilostwatch.light.mode.bean.PostList;
import com.qihoo360.antilostwatch.light.mode.biz.TalkBiz;

import rx.Observer;


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
        mTalkBiz.loadPostList(new ApiObserver<PostList>(){

            @Override
            public void onStart() {
                super.onStart();
                System.out.println("onStart()");
            }


            @Override
            public void onError(Throwable e, int errorCode, String errorMsg) {
                System.out.println("onError()");
            }

            @Override
            public void onSuccess(PostList postList) {
                System.out.println("onSuccess()");
            }
        });
    }

    @Override
    public void loadMorePostList() {
        //mTalkBiz.loadMorePostList();
    }
}