package com.qihoo360.antilostwatch.light.api;

/**
 * Created by HuirongZhang on 2016/10/25.
 * <p>
 * 发送请求后的回调接口
 */

public interface ApiCallBack<T> {
    void onCompleted();

    void onNext(T t);
}
