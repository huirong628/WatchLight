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

                    /**
                     * 处理成功时调用
                     *
                     * @param topicList
                     */
                    @Override
                    public void onSuccess(TopicList topicList) {

                    }

                    /**
                     * 处理失败时调用
                     *
                     * @param throwable
                     */
                    @Override
                    public void onFail(Throwable throwable) {

                    }

                });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void unSubscribe() {

    }
}
