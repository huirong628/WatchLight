package com.qihoo360.antilostwatch.light.base;


import rx.subscriptions.CompositeSubscription;

/**
 * Created by HuirongZhang on 2016/10/25.
 */

public class BaseCommonPresenter<T extends BaseView> {

    protected CompositeSubscription mCompositeSubscription;

    public T mView;

    public BaseCommonPresenter(T view) {
        //创建 CompositeSubscription 对象 使用CompositeSubscription来持有所有的Subscriptions，然后在onDestroy()或者onDestroyView()里取消所有的订阅。
        mCompositeSubscription = new CompositeSubscription();
        // 构建 ApiWrapper 对象
        this.mView = view;
    }
}
