package com.qihoo360.antilostwatch.light.ui.postlist;

import com.qihoo360.antilostwatch.light.api.ApiObserver;
import com.qihoo360.antilostwatch.light.base.BaseCommonPresenter;
import com.qihoo360.antilostwatch.light.mode.bean.PostList;
import com.qihoo360.antilostwatch.light.mode.biz.TalkBiz;

import rx.Observable;
import rx.Subscriber;
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
                        mView.onLoading();
                    }

                    @Override
                    public void onStop() {
                        super.onStop();
                        System.out.println("onStop()");
                        mView.onRefreshComplete();
                    }

                    @Override
                    public void onSuccess(PostList postList) {
                        System.out.println("onSuccess()");
                        mView.onPostListLoaded(postList);
                    }

                    @Override
                    public void onFail(Throwable throwable) {
                        System.out.println("onFail()");
                        System.out.println(throwable.getMessage());
                        //如果是网络问题，如：超时
                        mView.onNetWork();
                        //如果是别的错误，如：数据解析出错
                        mView.onError();
                    }
                });

        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                /**
                 * RxJavaHooks.onObservableStart(observable, observable.onSubscribe).call(subscriber);
                 *
                 * 该方法意味着事件开始发送
                 */
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
                    public void onStop() {
                        super.onStop();
                        mView.onLoadMoreComplete();
                    }

                    @Override
                    public void onSuccess(PostList postList) {
                        mView.onMorePostListLoaded(postList);
                    }

                    @Override
                    public void onFail(Throwable throwable) {
                        System.out.println(throwable.getMessage());
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