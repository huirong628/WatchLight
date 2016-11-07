package com.qihoo360.antilostwatch.light.api;

import rx.Subscriber;

/**
 * Created by HuirongZhang
 * on 2016/11/7.
 */

public abstract class ApiObserver<T> extends Subscriber<T> {

    public abstract void onError(Throwable e, int errorCode, String errorMsg);

    public abstract void onSuccess(T t);

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }
}
