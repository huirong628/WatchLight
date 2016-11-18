package com.qihoo360.antilostwatch.light.ui.postlist;

import com.qihoo360.antilostwatch.light.api.ApiObserver;
import com.qihoo360.antilostwatch.light.base.BaseCommonPresenter;
import com.qihoo360.antilostwatch.light.mode.bean.PostList;
import com.qihoo360.antilostwatch.light.mode.biz.TalkBiz;

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
    public void loadPostList() {
        System.out.println("loadPostList() " + Thread.currentThread().getName());
        Subscription subscription = mTalkBiz.loadPostList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ApiObserver<PostList>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        System.out.println("onStart()");
                    }

                    @Override
                    public void onNext(PostList postList) {
                        System.out.println("onNext(), " + Thread.currentThread().getName());
                        mView.onPostListLoaded(postList);
                    }

                    @Override
                    public void onCompleted() {
                        System.out.println("onCompleted()");
                        mView.onRefreshComplete();
                    }

                    @Override
                    public void onError(Throwable e, int errorCode, String errorMsg) {
                        System.out.println("onError(),e.getMessage()1 = " + e.getMessage());
                        mView.onRefreshComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("onError().e.getMessage()2 = " + e.getMessage());
                        mView.onRefreshComplete();
                        super.onError(e);

                    }
                });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void loadMorePostList(long id) {
        System.out.println("loadMorePostList() " + Thread.currentThread().getName());
        Subscription subscription = mTalkBiz.loadMorePostList(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ApiObserver<PostList>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        System.out.println("onStart()");
                    }

                    @Override
                    public void onNext(PostList postList) {
                        System.out.println("onNext(), " + Thread.currentThread().getName());
                        mView.onMorePostListLoaded(postList);
                    }

                    @Override
                    public void onCompleted() {
                        System.out.println("onCompleted()");
                        mView.onLoadMoreComplete();
                    }

                    @Override
                    public void onError(Throwable e, int errorCode, String errorMsg) {
                        System.out.println("onError()");
                        mView.onLoadMoreComplete();
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void unSubscribe() {
        System.out.println("unSubscribe()");
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }
}