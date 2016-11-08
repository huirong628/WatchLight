package com.qihoo360.antilostwatch.light.ui.postlist;

import com.qihoo360.antilostwatch.light.api.ApiObserver;
import com.qihoo360.antilostwatch.light.base.BaseCommonPresenter;
import com.qihoo360.antilostwatch.light.mode.bean.PostList;
import com.qihoo360.antilostwatch.light.mode.biz.TalkBiz;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;


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
        Subscription subscription = mTalkBiz.loadPostList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PostList>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("onCompleted()");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("onError()  " + e.getMessage());
                    }

                    @Override
                    public void onNext(PostList postList) {
                        System.out.println("onNext()");
                        mView.onPostListLoaded(postList);
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void loadMorePostList() {
        //mTalkBiz.loadMorePostList();
    }
}