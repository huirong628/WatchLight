package com.qihoo360.antilostwatch.light.api;

import com.google.gson.JsonSyntaxException;

import java.net.SocketTimeoutException;
import java.util.concurrent.TimeoutException;

import rx.Subscriber;

/**
 * Created by HuirongZhang
 * on 2016/11/7.
 * <p>
 * 用来扩展Observer
 */

public abstract class ApiObserver<T> extends Subscriber<T> {

    /**
     * subscriber.onStart();
     */
    @Override
    public void onStart() {
        System.out.println("开始之前检查是否有网络，无网络直接抛出异常");
        //开始之前检查是否有网络，无网络直接抛出异常。
        this.onError(new TimeoutException("timeout"));
        super.onStart();
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onCompleted() {
        onStop();
    }

    @Override
    public void onError(Throwable e) {
        onStop();
        if (e instanceof SocketTimeoutException) {
            System.out.println("SocketTimeoutException");
        } else if (e instanceof JsonSyntaxException) {
            System.out.println("JsonSyntaxException");
        }
        onFail(e);
    }

    /**
     * 结束时调用，无论成功与否。
     */
    public void onStop() {

    }

    /**
     * 处理成功时调用
     *
     * @param t
     */
    public abstract void onSuccess(T t);

    /**
     * 处理失败时调用
     *
     * @param throwable
     */
    public abstract void onFail(Throwable throwable);

}
