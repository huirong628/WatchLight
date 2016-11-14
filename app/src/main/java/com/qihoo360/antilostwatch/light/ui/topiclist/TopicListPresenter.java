package com.qihoo360.antilostwatch.light.ui.topiclist;

import com.qihoo360.antilostwatch.light.api.ApiObserver;
import com.qihoo360.antilostwatch.light.base.BaseCommonPresenter;
import com.qihoo360.antilostwatch.light.mode.bean.PostList;
import com.qihoo360.antilostwatch.light.mode.bean.TopicList;
import com.qihoo360.antilostwatch.light.mode.biz.TalkBiz;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by HuirongZhang on 2016/11/2.
 */

public class TopicListPresenter extends BaseCommonPresenter<TopicListFragment> implements TopicListContract.Presenter {

    private TalkBiz mTalkBiz;

    public TopicListPresenter(TopicListFragment view) {
        super(view);
        mTalkBiz = new TalkBiz();
    }

    @Override
    public void loadTopicList() {
        Subscription subscription = mTalkBiz.loadTopicList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ApiObserver<TopicList>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        System.out.println("onStart()");
                    }

                    @Override
                    public void onNext(TopicList topicList) {
                        System.out.println("onNext(), " + Thread.currentThread().getName());
                        mView.onTopicListLoaded(topicList);
                    }

                    @Override
                    public void onCompleted() {
                        System.out.println("onCompleted()");
                        mView.onRefreshComplete();
                    }

                    @Override
                    public void onError(Throwable e, int errorCode, String errorMsg) {
                        System.out.println("onError()");
                        mView.onRefreshComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("onError(),e.getMessage()" + e.getMessage());
                        mView.onRefreshComplete();
                        super.onError(e);

                    }
                });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void unSubscribe() {

    }
}
