package com.qihoo360.antilostwatch.light.base;

import com.qihoo360.antilostwatch.light.api.ApiCallBack;
import com.qihoo360.antilostwatch.light.api.ApiWrapper;


import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by HuirongZhang on 2016/10/25.
 */

public class BaseCommonPresenter<T extends BaseView> {

    protected ApiWrapper mApiWrapper;

    protected CompositeSubscription mCompositeSubscription;

    public T mView;

    public BaseCommonPresenter(T view) {
        //创建 CompositeSubscription 对象 使用CompositeSubscription来持有所有的Subscriptions，然后在onDestroy()或者onDestroyView()里取消所有的订阅。
        mCompositeSubscription = new CompositeSubscription();
        // 构建 ApiWrapper 对象
        mApiWrapper = new ApiWrapper();
        this.mView = view;
    }

    /**
     * 创建观察者
     *
     * @param callBack
     * @param <E>
     * @return
     */
    protected <E> Subscriber createSubscriber(final ApiCallBack callBack) {
        return new Subscriber<E>() {
            @Override
            public void onCompleted() {
                callBack.onCompleted();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(E t) {
                if (!mCompositeSubscription.isUnsubscribed()) {
                    callBack.onNext(t);
                }
            }
        };
    }
}
